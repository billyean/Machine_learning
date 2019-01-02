def word_count(dict, word):
    if word in dict: 
        return dict[word] 
    else:
        return 0
        
word_count = lambda d,w: d[w] if w in d else 0
awesome_count = lambda d: word_count(d, 'awesome')
great_count = lambda d: word_count(d, 'great')
fantastic_count = lambda d: word_count(d, 'fantastic')
amazing_count = lambda d: word_count(d, 'amazing')
love_count = lambda d: word_count(d, 'love')
horrible_count = lambda d: word_count(d, 'horrible')
bad_count = lambda d: word_count(d, 'bad')
terrible_count = lambda d: word_count(d, 'terrible')
awful_count = lambda d: word_count(d, 'awful')
wow_count = lambda d: word_count(d, 'wow')
hate_count = lambda d: word_count(d, 'hate')

products['awesome'] = products['word_count'].apply(awesome_count)
products['great'] = products['word_count'].apply(great_count)
products['fantastic'] = products['word_count'].apply(fantastic_count)
products['amazing'] = products['word_count'].apply(amazing_count)
products['love'] = products['word_count'].apply(love_count)
products['horrible'] = products['word_count'].apply(horrible_count)
products['bad'] = products['word_count'].apply(bad_count)
products['terrible'] = products['word_count'].apply(terrible_count)
products['awful'] = products['word_count'].apply(awful_count)
products['wow'] = products['word_count'].apply(wow_count)
products['hate'] = products['word_count'].apply(hate_count)

selected_words_model = graphlab.logistic_classifier.create(train_data,
                                                     target='sentiment',
                                                     features=selected_words,
                                                     validation_set=test_data)
                                                     
great
wow
love
terrible
0.8453551912568306
0.952
0.8400192169108815
The model learned using all words performed much better than the other two. The other two approaches performed about the same.
0.9584435808934214
0.796940851290673
None of the selected_words appeared in the text of this review.
