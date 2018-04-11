import bridges.base.Color;
import bridges.base.ColorGrid;

public class HelperFunctions
{
	public static String[] getLyrics(String song)
	{
		return splitLyrics(song);
	}

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

	public static ColorGrid getGrid(int[][] matrix, Color background, Color match)
	{
		ColorGrid grid = new ColorGrid(matrix.length, background);

		for (int i = 0; i < matrix.length; ++i)
		{
			for (int j = 0; j < matrix.length; ++j)
			{
				Color color = matrix[i][j] == 1 ? match : background;
				grid.set(i, j, color);
			}
		}

		return grid;
	}
}
