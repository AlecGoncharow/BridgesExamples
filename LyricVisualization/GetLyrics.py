import os
import requests
import re
from bs4 import BeautifulSoup

base_url = "http://api.genius.com"
headers = {'Authorization': 'Bearer TokenHere'}
newSong = None
songFilePath = ".\\Artists\\"

if not os.path.exists(songFilePath): os.mkdir(songFilePath)


def lyrics_from_song_api_path(song_api_path):
    song_url = base_url + song_api_path
    response = requests.get(song_url, headers=headers)
    json = response.json()
    path = json["response"]["song"]["path"]
    # gotta go regular html scraping... come on Genius
    page_url = "http://genius.com" + path
    page = requests.get(page_url)
    html = BeautifulSoup(page.text, "html.parser")
    # remove script tags that they put in the middle of the lyrics
    [h.extract() for h in html('script')]
    # at least Genius is nice and has a tag called 'lyrics'!
    lyrics = html.find("div", class_="lyrics").get_text() # updated css where the lyrics are based in HTML
    return lyrics


def lyrics_from_genius_path(genius_path):
    page = requests.get(genius_path)
    html = BeautifulSoup(page.text, "html.parser")
    # remove script tags that they put in the middle of the lyrics
    [h.extract() for h in html('script')]
    # at least Genius is nice and has a tag called 'lyrics'!
    lyrics = html.find("div", class_="lyrics").get_text()  # updated css where the lyrics are based in HTML
    return lyrics


def album_from_api_path(api_path):
    album_url = base_url + api_path
    response = requests.get(album_url, headers=headers)
    json = response.json()
    path = json["response"]["song"]["path"]
    page_url = "http://genius.com" + path
    page = requests.get(page_url)
    html = BeautifulSoup(page.text, "html.parser")
    links = []
    for a in html.find_all('a', {'data-real-link': "true"}):
        if re.match(r'.+-lyrics', a['href']):
            links.append(a['href'])
    return links


def album_from_song_api_path(song_api_path):
    song_url = base_url + song_api_path
    response = requests.get(song_url, headers=headers)
    json = response.json()
    json_response = json.get('response')
    json_song = json_response.get('song')
    json_album = json_song.get('album')
    if json_album is not None:
        return json["response"]["song"]["album"]["name"]
    else:
        return "Singles"


def genius_search_for_song(artist_name, song_title):
    search_url = base_url + "/search"
    data = {'q': artist_name + song_title}
    response = requests.get(search_url, params=data, headers=headers)
    json = response.json()
    song_info = None
    for hit in json["response"]["hits"]:
        if hit["result"]["primary_artist"]["name"].lower() == artist_name.lower():
            song_info = hit
            break
    if song_info is not None:
        song_api_path = song_info["result"]["api_path"]
        album = album_from_song_api_path(song_api_path)
        album = re.sub(r"\W+$", "", album)
        album = re.sub(r"^\W+", "", album)

        song_lyrics = str(lyrics_from_song_api_path(song_api_path))

        artist_file_path = songFilePath + artist_name
        album_file_path = artist_file_path + "\\" + album
        if not os.path.exists(artist_file_path):
            os.mkdir(artist_file_path)
            os.mkdir(album_file_path)
        elif not os.path.exists(album_file_path):
            os.mkdir(album_file_path)

        song_file = open(album_file_path + "\\" + song_title, "w+", encoding='utf-8')
        song_file.write(song_lyrics)
        song_file.close()
        print("in genius")
        return song_lyrics
    raise ValueError("Unable to find a song matching those parameters")


def search_for_song(artist_name, song_title, album=None):
    artist_file_path = songFilePath + artist_name
    song_lower = song_title.lower()
    if album is not None:
        album_lower = album.lower()

    if os.path.exists(artist_file_path):
        for entry in os.listdir(artist_file_path):
            if album is not None:
                if str(entry).lower() == album_lower:
                    album_dir = artist_file_path + album
                    for song in os.listdir(album_dir):
                        if str(song).lower() == song_lower:
                            print("in files")
                            return open(album_dir + "\\" + song, encoding='utf-8').read()
            else:
                if os.path.exists(artist_file_path + "\\" + entry):
                    sub_path = artist_file_path + "\\" + entry
                    for song in os.listdir(sub_path):
                        if str(song).lower() == song_lower:
                            print("in files")
                            return open(sub_path + "\\" + song, encoding='utf-8').read()
    print("file not found, searching genius")
    return genius_search_for_song(artist_name, song_title)

''' I have yet to get these working, commenting out for now
def genius_search_for_album(artist_name, album):
    search_url = base_url + "/search"
    data = {'q': artist_name + album}
    response = requests.get(search_url, params=data, headers=headers)
    json = response.json()
    song_info = None
    for hit in json["response"]["hits"]:
        if hit["result"]["primary_artist"]["name"] == artist_name:
            song_info = hit
            break
    if song_info is not None:
        api_path = song_info["result"]["api_path"]
        song_links = album_from_api_path(api_path)
        song_lyrics = []
        for link in song_links:
            song_lyrics.append()


def search_for_album(artist_name, album):
    artist_file_path = songFilePath + artist_name
    if os.path.exists(artist_file_path):
        for entry in os.listdir(artist_file_path):
            if str(entry).lower() == album.lower():
                songs = []
                sub_path = artist_file_path + "\\" + entry
                for song in os.listdir(sub_path):
                    songs.append(open(sub_path + "\\" + song, encoding='utf-8').read())
                return songs
'''