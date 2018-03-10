import math
from textblob import TextBlob as tb


def tf(word, blob):	#Calculates term frequency
    return blob.words.count(word) / len(blob.words)


def n_containing(word, bloblist):	#calculates the amount of documents containing a term
    return sum(1 for blob in bloblist if word in blob.words)


def idf(word, bloblist):	# calculates the inverse document frequency
    return math.log(len(bloblist)) / (1 + n_containing(word, bloblist))


def tfidf(word, blob, bloblist): # calculates the term frequency times the inverse document frequency
    return tf(word, blob) * idf(word, bloblist)