import GetLyrics
import re
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt

artist_name = ""
song_title = ""

lyrics = GetLyrics.search_for_song(artist_name, song_title)
lyrics = re.sub(r"\[.+\]", "", lyrics)
lyrics = lyrics.lower()
lyrics = lyrics.strip()

lyricsSplit = lyrics.split()

for i, lyric in enumerate(lyricsSplit):
    lyricsSplit[i] = re.sub(r'\W+$', "", lyricsSplit[i])
    lyricsSplit[i] = re.sub(r'^\W+', "", lyricsSplit[i])
    lyricsSplit[i] = lyricsSplit[i].strip()

unique_words = []
for word in lyricsSplit:
    if word not in unique_words: unique_words.append(word)

color_hash = {}
for i, word, in enumerate(unique_words):
    color_hash[word] = i

matches = []
for i, first in enumerate(lyricsSplit):
    row = []
    for j, second in enumerate(lyricsSplit):
        if first == second:
            row.append(color_hash[first])
        else:
            row.append(-50)
    matches.append(row)

test = np.array(matches)

d = pd.DataFrame(data=test)

sns.heatmap(data=d, cmap="nipy_spectral")
plt.title(artist_name + " - " + song_title + "\n Colorized Repetition Matrix")
plt.show()