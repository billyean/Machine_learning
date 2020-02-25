from nltk import  PorterStemmer, LancasterStemmer, word_tokenize

raw = "My Name is Tritsan Yan, a software engineer in Walmart, I am studying machine learning in Claremont Graduate University."
tokens = word_tokenize(raw)
porter = PorterStemmer()
pStems = [porter.stem(t) for t in tokens]
print(pStems)

lancaster = LancasterStemmer()
pStems = [lancaster.stem(t) for t in tokens]
print(pStems)