public class Song
{
    private String artist;
    private String title;
    private String album;
    private String lyrics;

    public Song()
    {
        this(null, null, null, null);
    }

    public Song(String lyrics)
    {
        this(null, null, null, lyrics);
    }

    public Song(String artist, String title, String lyrics)
    {
        this(artist, title, null, lyrics);
    }

    public Song(String artist, String title, String album, String lyrics)
    {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.lyrics = lyrics;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public String getLyrics()
    {
        return lyrics;
    }

    public void setLyrics(String lyrics)
    {
        this.lyrics = lyrics;
    }

    @Override
    public String toString()
    {
        return String.format("%s - %s", this.artist, this.title);
    }
}
