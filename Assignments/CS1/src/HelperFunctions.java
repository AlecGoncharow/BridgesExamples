

public class HelperFunctions
{
	public static String[] splitLyrics(String lyrics)				// splits raw lyrics string into a parsable array
	{
		lyrics = lyrics.replaceAll("\\[.+\\]","");	// removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\s+");
		for (int i = 0; i < lyricsSplit.length; i++)					// clears special characters from individual terms
		{
			lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
			lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
			lyricsSplit[i] = lyricsSplit[i].trim();
		}

		return lyricsSplit;
	}
}
