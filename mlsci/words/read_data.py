from nltk.corpus import CategorizedPlaintextCorpusReader
from random import randint

# Download http://www.cs.cornell.edu/people/pabo/movie-review-data/mix20_rand700_tokens_cleaned.zip and unzipp it
# in current folder
reader = CategorizedPlaintextCorpusReader(r'mix20_rand700_tokens_cleaned/tokens', r'.*\.txt', cat_pattern=r'(\w+)/*')

print(reader.categories())
print(reader.fileids())

posFiles = reader.fileids(categories='pos')
negFiles = reader.fileids(categories='neg')

fileP = posFiles[randint(0, len(posFiles))]
fileN = negFiles[randint(0, len(negFiles))]

print(fileP)
print(fileN)

for w in reader.words(fileP):
    print(w + ' ', end='')
    if w is '.':
        print()

for w in reader.words(fileN):
    print(w + ' ', end='')
    if w is '.':
        print()

