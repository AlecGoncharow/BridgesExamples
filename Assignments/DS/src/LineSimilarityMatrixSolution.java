
public class LineSimilarityMatrixSolution
{
	static String lyrics = SongStrings.feelGoodInc;	// Lyrics function here
	static String[][] corpus = HelperFunctions.splitLines(lyrics);			// returns cleaned up corpus
	static String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);	// returns unique terms


	public static void calculate(Dictionary[] documentVectors, double[][] cosineSimilarityMatrix, int amountOfDocuments)
	{
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
				System.out.print(cosineSimilarityMatrix[j][i]);
			}
			System.out.println();
		}
	}


	public static void main(String[] args)
	{
		//Bridges Credentials


		int amountOfDocuments = corpus.length;

		Hashtable<String, Double>[] hash = new Hashtable[amountOfDocuments];
		double[][] mat1 = new double[amountOfDocuments][amountOfDocuments];
		UnsortedArray<String, Double>[] arr = new UnsortedArray[amountOfDocuments];
		double[][] mat2 = new double[amountOfDocuments][amountOfDocuments];

		long startTimeHash = System.nanoTime();
		calculate(hash, mat1, amountOfDocuments);
		long endTimeHash = System.nanoTime();
		calculate(arr, mat2, amountOfDocuments);
		long endTimeArray = System.nanoTime();

		System.out.println(hash[0]);
		System.out.printf("\n\n HASHTIME: %d \n ARRAY: %d", (endTimeHash-startTimeHash) / 1000000 , (endTimeArray-endTimeHash) / 1000000);
		//Bridges visualize code
	}
}
