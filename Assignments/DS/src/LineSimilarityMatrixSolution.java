
public class LineSimilarityMatrixSolution
{
	public static void main(String[] args)
	{
		//Bridges Credentials

		String lyrics = "";	// Lyrics function here
		String[][] corpus = HelperFunctions.splitLines(lyrics);			// returns cleaned up corpus
		String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);	// returns unique terms
		int amountOfDocuments = corpus.length;
		AssociativeArray[] documentVectors = new AssociativeArray[amountOfDocuments];

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
				cosineSimilarityMatrix[j][i] = (HelperFunctions.dotProduct(documentVectors[i], documentVectors[j]) /
						(HelperFunctions.norm(documentVectors[i])
								* HelperFunctions.norm(documentVectors[j])));
				System.out.print(cosineSimilarityMatrix[j][i]);
			}
			System.out.println();
		}

		//Bridges visualize code
	}
}
