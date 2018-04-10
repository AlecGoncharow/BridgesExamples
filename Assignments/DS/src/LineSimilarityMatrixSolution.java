import bridges.connect.Bridges;
import bridges.base.ColorGrid;
import bridges.base.Color;

public class LineSimilarityMatrixSolution
{


	static String lyrics = SongStrings.feelGoodInc;	// Lyrics function here
	static String[][] corpus = HelperFunctions.splitLines(lyrics);			// returns cleaned up corpus
	static String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);	// returns unique terms


	public static ColorGrid calculate(Dictionary[] documentVectors, double[][] cosineSimilarityMatrix, int amountOfDocuments)
	{
		ColorGrid grid = new ColorGrid(documentVectors.length, "black");
		if (documentVectors instanceof UnsortedArray[])
			for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
			{
				documentVectors[i] = HelperFunctions.getUnsortedArrayVector(corpus[i], corpus, uniqueTerms);
			}
		else
		{
			for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
			{
				documentVectors[i] = HelperFunctions.getHastableVector(corpus[i], corpus, uniqueTerms);
			}
		}
		cosineSimilarityMatrix = new double[amountOfDocuments][amountOfDocuments];

		for (int i = 0; i < amountOfDocuments; i++)
		{
			for (int j = 0; j < amountOfDocuments; j++)
			{
				// calculates the cosine between the vectors and stores it in the corresponding index of the matrix
				cosineSimilarityMatrix[j][i] = (HelperFunctions.dotProduct(documentVectors[i], documentVectors[j]) /
						(HelperFunctions.norm(documentVectors[i])
								* HelperFunctions.norm(documentVectors[j])));
				grid.set(i, j, new Color(0, 0, 0, (float)cosineSimilarityMatrix[j][i]));
				System.out.println(grid.get(i, j).getAlpha());

				//System.out.print(cosineSimilarityMatrix[j][i]);
			}
			//System.out.println();
		}
		return grid;
	}


	public static void main(String[] args) throws Exception
	{
		//Bridges Credentials
		Bridges test = new Bridges(1 ,"549234500406", "agoncharow");

		test.setServer("clone");

		int amountOfDocuments = corpus.length;

		Hashtable<String, Double>[] hash = new Hashtable[amountOfDocuments];
		double[][] mat1 = new double[amountOfDocuments][amountOfDocuments];
		UnsortedArray<String, Double>[] arr = new UnsortedArray[amountOfDocuments];
		double[][] mat2 = new double[amountOfDocuments][amountOfDocuments];

		long startTimeHash = System.nanoTime();
		ColorGrid grid = calculate(hash, mat1, amountOfDocuments);
		long endTimeHash = System.nanoTime();
		//calculate(arr, mat2, amountOfDocuments);
		long endTimeArray = System.nanoTime();

		System.out.println(hash[0]);
		System.out.printf("\n\n HASHTIME: %d \n ARRAY: %d", (endTimeHash-startTimeHash) / 1000000 , (endTimeArray-endTimeHash) / 1000000);



		test.setDataStructure(grid);
		test.setVisualizeJSON(true);
		test.visualize();
	}
}
