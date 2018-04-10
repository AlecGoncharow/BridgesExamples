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
		Bridges test = new Bridges(2 ,"549234500406", "agoncharow");

		test.setServer("clone");

		//bridges credentials
		String lyrics = SongStrings.feelGoodInc;
		String[] lyricsSplit = HelperFunctions.splitLyrics(lyrics);

		int wordCount = lyricsSplit.length;
		ColorGrid grid = new ColorGrid(wordCount, "white");
		int[][] binaryMatrix = new int[wordCount][wordCount];	//could be int or boolean

		for (int i = 0; i < wordCount; i++)
		{
			for (int j = 0; j < wordCount; j++)
			{
				// unsure of when CS1 students learn of ternary operators, more likely to see if statements here
				binaryMatrix[i][j] = lyricsSplit[i].equalsIgnoreCase(lyricsSplit[j]) ? 1 : 0;
				if (binaryMatrix[i][j] == 1)
					grid.set(i, j, new Color(0, 0,0,1));
 			}
		}

		test.setDataStructure(grid);
		test.visualize();
		//bridges visualize
	}
}
