from nltk.corpus import wordnet as wn

x_type ='n'
synsets = wn.all_synsets(x_type)
lemmas = []
for synset in synsets:
    for lemma in synset.lemmas():
        lemmas.append(lemma.name())
lemmas = set(lemmas)
count = 0
for lemma in lemmas:
    count += len(wn.synsets(lemma, x_type))

print(f'Total distinct lemmas: {len(lemmas)}')
print(f'Total senses: {count}')
print(f'Average Polysemy of {x_type} : {count / len(lemmas)}')

