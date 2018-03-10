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

matches = []
for i, first in enumerate(lyricsSplit):
    row = []
    for j, second in enumerate(lyricsSplit):
        if first == second:
            row.append(1)
        else:
            row.append(0)
    matches.append(row)

test = np.array(matches)

d = pd.DataFrame(data=test, columns=lyricsSplit)
plt.title(artist_name + " - " + song_title + "\n Boolean Repetition Matrix")
sns.heatmap(d)
plt.show()
