from bs4 import BeautifulSoup
import pandas as pd
import numpy as np
import requests
import re

# Open the file containing URLs of web pages to be scraped
file = open("link.txt", 'r')
scraped_info = []

# Inform the user that data scraping is in progress
print("Scraping data...")

# Iterate over each line in the file to extract country name and URL
for line in file:
    country_name, country_url = line.split(',')
    country_url = country_url.strip()  # Remove leading/trailing whitespaces from URL

    # Fetch the HTML content of the web page
    page_content = requests.get(country_url).text

    # Parse the HTML content using BeautifulSoup
    soup = BeautifulSoup(page_content, "html.parser")

    # Find the infobox containing relevant information
    info_box = soup.find(class_="infobox")

    # Extract the capital city of the country
    capital_soup = info_box.find(string=re.compile("Capital"))
    while capital_soup.name != "tr":  # Navigate upwards until reaching the table row
        capital_soup = capital_soup.parent
    capital_soup = capital_soup.find(class_="infobox-data")
    capital = capital_soup.a.text

    # Extract the languages spoken in the country
    languages = []
    lang_soups = info_box.find_all(string=re.compile("language"))
    for lang_soup in lang_soups:
        while lang_soup.name != "tr":
            lang_soup = lang_soup.parent
        lang_soup = lang_soup.find(class_="infobox-data")
        try:
            # Extract languages from list elements if available
            languages.extend(
                [li.a.text for li in lang_soup.find("ul").find_all("li")])
        except:
            # If no list elements, directly extract language
            languages.extend([lang_soup.a.text])
    # Filter and retain only proper nouns as languages
    languages = filter(lambda x: x[0].isupper(), languages)
    languages = list(set(languages))  # Remove duplicates if any

    # Extract the area of the country
    area_soup = info_box.find(string=re.compile("Area"))
    if area_soup is None:
        area = "N.A."
    else:
        while area_soup.name != "tr":
            area_soup = area_soup.parent
        area_soup = area_soup.find_next("tr")
        area = area_soup.find(class_="infobox-data").text
        area = int(area.replace('[', ' ').split()[
                   0].replace(',', ''))  # Convert to integer

    # Extract the population of the country
    pop_soup = info_box.find(string=re.compile("Population"))
    if pop_soup is None:
        population = "N.A."
    else:
        while pop_soup.name != "tr":
            pop_soup = pop_soup.parent
        pop_soup = pop_soup.find_next("tr")
        population = pop_soup.find(class_="infobox-data").text
        population = int(population[:population.find(
            '[')].replace(',', ''))  # Convert to integer

    # Extract the GDP of the country
    gdp_soup = info_box.find(string=re.compile("GDP"))
    if gdp_soup is None:
        gdp = "N.A."
    else:
        while gdp_soup.name != "tr":
            gdp_soup = gdp_soup.parent
        gdp_soup = gdp_soup.find_next("tr")
        gdp = gdp_soup.find(class_="infobox-data").text
        gdp = gdp[:gdp.find('[')].strip()  # Remove additional characters

    # Extract the Gini coefficient of the country (if available)
    gini_soup = info_box.find(string=re.compile("Gini"))
    if gini_soup is None:
        gini = "N.A."
    else:
        while gini_soup.name != "tr":
            gini_soup = gini_soup.parent
        gini_soup = gini_soup.find(class_="infobox-data")
        gini = gini_soup.text
        gini = gini[:gini.find('[')]  # Remove additional characters

    # Append the extracted information to the list
    scraped_info.append([country_name, capital, languages,
                        area, population, gdp, gini])

# Close the file after reading
file.close()

# Create a DataFrame from the scraped information
df = pd.DataFrame(scraped_info)
df.columns = ["Country", "Capital", "Languages",
              "Area", "Population", "GDP", "Gini"]

# Inform the user that data scraping is completed and display the DataFrame
print("Data successfully scraped.")
print(df)

# Save the DataFrame to a CSV file
df.to_csv("scraped_data.csv", index=False)

# Read the CSV file into a DataFrame
data = pd.read_csv('scraped_data.csv')

# Replace 'N.A.' values with NaN and convert GDP and Gini to float
data['GDP'] = data['GDP'].replace('N.A.', np.nan)
data['GDP'] = data['GDP'].str.extract(r'([\d.]+)').astype(float)
data['Gini'] = data['Gini'].replace('N.A.', np.nan)
data['Gini'] = data['Gini'].str.extract(r'([\d.]+)').astype(float)

# Find the country with the highest population, area, and GDP
country_max_pop = data.loc[data['Population'].idxmax()]['Country']
country_max_area = data.loc[data['Area'].idxmax()]['Country']
country_max_gdp = data.loc[data['GDP'].idxmax()]['Country']

# Display the countries with the highest population, area, and GDP
print(f"Country with highest population: {country_max_pop}")
print(f"Country with highest area: {country_max_area}")
print(f"Country with highest GDP: {country_max_gdp}")

# Compute the Pearson correlation coefficient for different pairs of variables
corr_area_pop = data['Area'].corr(data['Population'])
corr_area_gdp = data['Area'].corr(data['GDP'])
corr_area_gini = data['Area'].corr(data['Gini'])
corr_pop_gdp = data['Population'].corr(data['GDP'])
corr_pop_gini = data['Population'].corr(data['Gini'])
corr_gdp_gini = data['GDP'].corr(data['Gini'])

# Display the Pearson correlation coefficients
print(f"Pearson correlation coefficient between Area and Population: {corr_area_pop}")
print(f"Pearson correlation coefficient between Area and GDP: {corr_area_gdp}")
print(f"Pearson correlation coefficient between Area and Gini: {corr_area_gini}")
print(f"Pearson correlation coefficient between Population and GDP: {corr_pop_gdp}")
print(f"Pearson correlation coefficient between Population and Gini: {corr_pop_gini}")
print(f"Pearson correlation coefficient between GDP and Gini: {corr_gdp_gini}")
