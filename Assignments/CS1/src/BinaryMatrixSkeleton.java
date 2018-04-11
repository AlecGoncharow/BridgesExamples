/*
	Instructions:
	You will be going over an array of Strings, and checking for repetition within the array,
	You will be storing this result in a two dimensional int array, a 1 for matching strings and a 0 otherwise.
 */

import bridges.base.Color;
import bridges.base.ColorGrid;
import bridges.connect.Bridges;

public class BinaryMatrixSkeleton
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

		/*
			Create a new multi dimensional array, or use provided code.
			Iterate over the lyrics, setting the value of each index in the matrix
			such that,
			If the two words match, set that index to 1.
			If the two words do not match, set that index to 0.
		 */


		Color defaultColor = new Color(0, 0, 0, 1);								// input your own RGBA values if you wish
		Color matchColor = new Color(255, 255, 255, 1);

		ColorGrid grid = HelperFunctions.getGrid(binaryMatrix, defaultColor, matchColor);
		bridges.setDataStructure(grid);
		bridges.visualize();
	}
}
