/*
	Sample solution for BinaryMatrix Assignment
 */

public class BinaryMatrixSampleSolution
{

	public static void main(String[] args)
	{
		//bridges credentials
		String lyrics = "Lyric retrieval method here";
		String[] lyricsSplit = HelperFunctions.splitLyrics(lyrics);

		int wordCount = lyricsSplit.length;
		int[][] binaryMatrix = new int[wordCount][wordCount];	//could be int or boolean

		for (int i = 0; i < wordCount; i++)
		{
			for (int j = 0; j < wordCount; j++)
			{
				// unsure of when CS1 students learn of ternary operators, more likely to see if statements here
				binaryMatrix[j][i] = lyricsSplit[i].equalsIgnoreCase(lyricsSplit[j]) ? 1 : 0;
 			}
		}

		//bridges visualize
	}
}
