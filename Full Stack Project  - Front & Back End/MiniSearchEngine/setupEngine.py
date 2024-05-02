import os
import requests
import pickle
from bs4 import BeautifulSoup
import nltk

# Download necessary NLTK data
nltk.download('punkt')

def get_wikipedia_html(country):
    """Fetches HTML content of a Wikipedia page for a given country."""
    base_url = 'https://en.wikipedia.org/wiki/'
    url = base_url + country.replace(' ', '_')
    
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.text
    except requests.exceptions.RequestException as e:
        print(f"Error fetching data for {country}: {e}")
        return None

def save_html_to_file(html, folder, country):
    """Saves HTML content to a file."""
    filename = f"{country.replace(' ', '_')}.html"
    filepath = os.path.join(folder, filename)
    
    with open(filepath, 'w', encoding='utf-8') as file:
        file.write(html)

def extract_text_from_html(html):
    """Extracts text content from HTML."""
    soup = BeautifulSoup(html, 'html.parser')
    paragraphs = soup.find_all('p')  # Extract paragraphs from HTML

    # Concatenate text from paragraphs
    text = ' '.join([paragraph.get_text() for paragraph in paragraphs])
    return text

def tokenize_text(text):
    """Tokenizes text into words."""
    tokens = nltk.word_tokenize(text)
    return tokens

def build_inverted_index(tokens):
    """Builds an inverted index from tokens."""
    index = {}
    for i, word in enumerate(tokens):
        if word not in index:
            index[word] = {'count': 1, 'positions': [i]}
        else:
            index[word]['count'] += 1
            index[word]['positions'].append(i)

    return index

def build_inverted_indices(html_folder):
    """Builds inverted indices for all HTML files in a folder."""
    inverted_indices = {}
    for filename in os.listdir(html_folder):
        if filename.endswith('.html'):
            file_path = os.path.join(html_folder, filename)
            with open(file_path, 'r', encoding='utf-8') as file:
                html_content = file.read()
            text = extract_text_from_html(html_content)
            tokens = tokenize_text(text)
            inverted_indices[filename] = build_inverted_index(tokens)

    # Save inverted indices to a pickle file
    with open('inverted_indices.pkl', 'wb') as pickle_file:
        pickle.dump(inverted_indices, pickle_file)

def fetch_and_save_htmls(countries, html_folder):
    """Fetches and saves HTML content for a list of countries."""
    if not os.path.exists(html_folder):
        os.makedirs(html_folder)

    for country in countries:
        html_content = get_wikipedia_html(country)
        if html_content:
            save_html_to_file(html_content, html_folder, country)
            print(f"HTML for {country} saved.")

if __name__ == "__main__":
    # Folder to save HTML files
    html_folder = "wikipedia_html"

    # List of countries
    countries_to_fetch = [
        "United States", "Canada", "United Kingdom", "Germany", "France",
        "China", "India", "Brazil", "Russia", "Australia",
        "Japan", "South Korea", "South Africa", "Mexico", "Italy",
        "Spain", "Argentina", "Turkey", "Saudi Arabia", "Nigeria",
    ]

    # Fetch and save HTML files
    fetch_and_save_htmls(countries_to_fetch, html_folder)

    # Build inverted indices
    build_inverted_indices(html_folder)
    
    print("Setup completed.")
