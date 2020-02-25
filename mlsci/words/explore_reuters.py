from nltk.corpus import reuters

files = reuters.fileids()
print(f"{files}\n")

words14841 = reuters.words(['test/14841'])
print(f"# of words in 14841 is {len(words14841)}, contents is {words14841}\n")

print(f"First 65 words are {words14841[:65]}\n")

reutersGenes = reuters.categories()
print(f"All categories in reuters are {reutersGenes}\n")

cpu_coffee = reuters.words(categories = ['cpu', 'coffee'])
d = 0
for w in cpu_coffee:
    print(w + ' ', end='')
    if w is '.':
        print()