import os
import pickle
from bs4 import BeautifulSoup

def load_inverted_indices():
    """Load inverted indices from a pickle file."""
    with open('inverted_indices.pkl', 'rb') as pickle_file:
        inverted_indices = pickle.load(pickle_file)
    return inverted_indices

def country_search(keyword, inverted_indices):
    """Search for a keyword in the inverted indices and return matching results."""
    results = []
    for filename, index in inverted_indices.items():
        if keyword in index:
            results.append((filename, index[keyword]['count']))
    return sorted(results, key=lambda x: x[1], reverse=True)

def edit_distance(str1, str2):
    """Calculate the edit distance between two strings."""
    m, n = len(str1), len(str2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    for i in range(m + 1):
        for j in range(n + 1):
            if i == 0:
                dp[i][j] = j
            elif j == 0:
                dp[i][j] = i
            elif str1[i - 1] == str2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])

    return dp[m][n]

def fuzzy_search(query, inverted_indices):
    """Perform fuzzy search based on edit distance and return matching results."""
    results = []
    for filename, index in inverted_indices.items():
        count = sum(index[token]['count'] for token in index if edit_distance(query, token) <= 2)
        if count > 0:
            results.append((filename, count))
    return sorted(results, key=lambda x: x[1], reverse=True)

def print_search_results(results):
    """Print search results."""
    if not results:
        print("No matching results found.")
    else:
        print("Matching results:")
        for result, count in results:
            print(f"  - {result} (Matches: {count})")

if __name__ == "__main__":
    # Load inverted indices
    inverted_indices = load_inverted_indices()

    # Interactive user input
    while True:
        print("\n===== Search Engine =====")
        print("1. Country Search")
        print("2. Fuzzy Search")
        print("3. Exit")

        choice = input("Enter your choice (1/2/3): ")

        if choice == '1':
            keyword = input("Enter the keyword for country search: ")
            search_results = country_search(keyword, inverted_indices)
            print_search_results(search_results)

        elif choice == '2':
            fuzzy_query = input("Enter the query for fuzzy search: ")
            fuzzy_results = fuzzy_search(fuzzy_query, inverted_indices)
            print_search_results(fuzzy_results)

        elif choice == '3':
            print("Exiting the Search Engine.")
            break

        else:
            print("Invalid choice. Please enter a valid option.")
