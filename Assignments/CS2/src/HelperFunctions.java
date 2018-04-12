import bridges.base.Color;
import bridges.base.ColorGrid;

public class HelperFunctions
{
	public static String[] splitLyrics(String lyrics)                // splits raw lyrics string into a parsable array
	{
		lyrics = lyrics.replaceAll("\\[.+\\]", "");    // removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\s+");
		for (int i = 0; i < lyricsSplit.length; i++)                    // clears special characters from individual terms
		{
			lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
			lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
			lyricsSplit[i] = lyricsSplit[i].trim();
		}

		return lyricsSplit;
	}

	public static String[][] splitLines(String lyrics)
	{
		lyrics = lyrics.replaceAll("\\[.+\\]", "");    // removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\n+");
		String[][] corpus = new String[lyricsSplit.length][];
		for (int i = 0; i < corpus.length; i++)                    // clears special characters from individual terms
		{
			corpus[i] = lyricsSplit[i].split("\\s+");
			for (int j = 0; j < corpus[i].length; j++)
			{
				corpus[i][j] = corpus[i][j].replaceAll("\\W+$", "");
				corpus[i][j] = corpus[i][j].replaceAll("^\\W+", "");
				corpus[i][j] = corpus[i][j].trim();
			}
		}

		return corpus;
	}

	public static ColorGrid getGrid(double[][] matrix, int[] RGBValues)
	{
		ColorGrid grid = new ColorGrid(matrix.length, "white");

		for (int i = 0; i < matrix.length; ++i)
		{
			for (int j = 0; j < matrix.length; ++j)
			{
				grid.set(i, j, new Color(RGBValues[0], RGBValues[1], RGBValues[2], (float)matrix[i][j]));
			}
		}

		return grid;
	}
}
