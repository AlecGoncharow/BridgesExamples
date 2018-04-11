
import bridges.base.ColorGrid;
import bridges.connect.Bridges;

import java.util.Hashtable;

public class LineSimilarityMatrixSolution
{
	public static void main(String[] args) throws Exception
	{
		Bridges bridges = new Bridges(105, "1134423833841", "agoncharow");

		String lyrics = SongStrings.bohemianRhapsody;	// Lyrics function here
		String[][] corpus = HelperFunctions.splitLines(lyrics);			// returns cleaned up corpus
		String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);	// returns unique terms
		int amountOfDocuments = corpus.length;
		Hashtable<String, Double>[] documentVectors = new Hashtable[amountOfDocuments];

		for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
		{
			documentVectors[i] = HelperFunctions.vectorize(corpus[i], corpus, uniqueTerms);
		}

		double[][] cosineSimilarityMatrix = new double[amountOfDocuments][amountOfDocuments];

		for (int i = 0; i < amountOfDocuments; i++)
		{
			for (int j = 0; j < amountOfDocuments; j++)
			{
				// calculates the cosine between the vectors and stores it in the corresponding index of the matrix
				cosineSimilarityMatrix[j][i] = HelperFunctions.cosine(documentVectors[i], documentVectors[j]);
			}
		}

		int[] RGBValues = {0, 0, 0};

		ColorGrid grid = HelperFunctions.getGrid(cosineSimilarityMatrix, RGBValues);
		bridges.setDataStructure(grid);
		bridges.setServer("clone");
		bridges.visualize();
	}
}
