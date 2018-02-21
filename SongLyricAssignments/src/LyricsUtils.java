/**
 * Created by Alec on Feb 19, 2018.
 */
public class LyricsUtils
{
	public static String[] splitLyrics(String lyrics)
	{
		lyrics = lyrics.replaceAll("\\[.+\\]",""); //removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\s+");
		for(int i = 0; i < lyricsSplit.length; i++)	//clearing special characters
		{
			lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
			lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
			lyricsSplit[i] = lyricsSplit[i].trim();
		}

		return lyricsSplit;
	}

	public static Lyric[] convertToNewLyric(String[] lyrics)
	{
		Lyric[] convert = new Lyric[lyrics.length];	//converts the Strings into a class I made to make parsing easier on myself
		for (int i = 0; i < convert.length; i++)
		{
			convert[i] = new Lyric(lyrics[i], i);
		}
		return convert;
	}
}
