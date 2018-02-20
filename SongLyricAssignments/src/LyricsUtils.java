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
}
