from nltk.corpus import wordnet as wn
chair = 'chair'

chair_synsets = wn.synsets(chair)

print(f'Synsets/Senses of Chair : {chair_synsets}\n\n')

for synset in chair_synsets:
    print(f'{synset} :')
    print(f'Definition: {synset.definition()}:')
    print(f'Lemmas/Synonymous words : {synset.lemma_names()}:')
    print(f'Example: : {synset.examples()}\n')