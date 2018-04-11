/*
	Sample solution for BinaryMatrix Assignment
 */

import bridges.base.Color;
import bridges.base.ColorGrid;
import bridges.connect.Bridges;

public class BinaryMatrixSampleSolution
{
	public static void main(String[] args) throws Exception
	{
		/*
			The below variables declarations / initializations could be modified as needed to change difficulty
		 */

		Bridges bridges = new Bridges(-1, "API_KEY", "USER_NAME");	// bridges credentials
		String[] lyrics = HelperFunctions.getLyrics("");									// returns already split and cleaned array of the lyrics
		int wordCount = lyrics.length;															// makes the for loops and matrix initializations a bit cleaner, could be removed
		int[][] binaryMatrix = new int[wordCount][wordCount];									// should be removed for more advanced students

		for (int i = 0; i < wordCount; ++i)
		{
			for (int j = 0; j < wordCount; ++j)
			{
				binaryMatrix[i][j] = lyrics[i].equalsIgnoreCase(lyrics[j]) ? 1 : 0;
			}
		}

		Color defaultColor = new Color(0, 0, 0, 1);								// input your own RGBA values if you wish
		Color matchColor = new Color(255, 255, 255, 1);

		ColorGrid grid = HelperFunctions.getGrid(binaryMatrix, defaultColor, matchColor);
		bridges.setDataStructure(grid);
		bridges.setServer("clone");
		bridges.visualize();
	}
}
