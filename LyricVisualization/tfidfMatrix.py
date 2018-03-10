import GetLyrics
import MatrixUtils
import re
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from textblob import TextBlob as tb
import nltk
nltk.download('punkt')

artist_name = ""
song_title = ""

lyrics = GetLyrics.search_for_song(artist_name, song_title)
lyrics = re.sub(r"\[.+\]", "", lyrics)
lyrics = lyrics.strip()
lyrics = lyrics.lower()
blob = tb(lyrics)
# print(blob)

unique_words = []

lyricsSplit = lyrics.split("\n")
blobs = []

for i, line in enumerate(lyricsSplit):
    lyricsSplit[i] = re.sub(r'\W+\s+|\W+$', " ", lyricsSplit[i])
    lyricsSplit[i] = re.sub(r'\s+\W+|^\W+', " ", lyricsSplit[i])
    lyricsSplit[i] = lyricsSplit[i].strip()
    if len(lyricsSplit[i]) == 0: lyricsSplit.remove(lyricsSplit[i]); continue
    blobs.append(tb(lyricsSplit[i]))

for line in lyricsSplit:
    for word in line.split():
        if word not in unique_words: unique_words.append(word)


frequency_vectors = []
for entry in blobs: # Builds Frequency vectors for each line in the song
    this_line = []
    for word in unique_words:
        this_line.append(MatrixUtils.tfidf(word, entry, blobs))
    frequency_vectors.append(this_line)

np_frequency_vectors = np.array(frequency_vectors)

sim_matrix = []
for i, first in enumerate(np_frequency_vectors): # Checks the cosine between every line's frequency vector
    row = []
    for j, second in enumerate(np_frequency_vectors):
        row.append(np.dot(first, second) / (np.linalg.norm(first) * np.linalg.norm(second)))
    sim_matrix.append(row)


np_sim_matrix = np.array(sim_matrix)
d = pd.DataFrame(data=np_sim_matrix)
sns.heatmap(data=d, cmap="nipy_spectral")
plt.title(artist_name + " - " + song_title + "\nCosine Similarity Matrix")
plt.show()
