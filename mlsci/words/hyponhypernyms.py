from nltk.corpus import wordnet as wn
woman = wn.synset('woman.n.02')

print(woman.hypernyms())
woman_paths = woman.hypernym_paths()
for idx, path in enumerate(woman_paths):
    print('\n\nHypernym Path :', idx + 1)
    for s in path:
        print(s.name().split('.')[0], ', ', end='')

bed = wn.synset('bed.n.01')
types_of_beds = bed.hyponyms()
print('\n\nTypes of beds(Hyponyms): ')
for s in types_of_beds:
    print(s.name().split('.')[0], ', ', end='')