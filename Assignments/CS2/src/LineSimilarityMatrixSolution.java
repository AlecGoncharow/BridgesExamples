
import bridges.base.ColorGrid;
import bridges.connect.Bridges;

import java.util.ArrayList;
import java.util.Hashtable;

public class LineSimilarityMatrixSolution
{
	public static int termFrequency(String term, String[] document)
	{
		int tf = 0;

		for (String word : document)
		{
			tf += term.equalsIgnoreCase(word) ? 1 : 0;
		}

		return tf;
	}

	public static boolean hasTerm(String term, String[] document)
	{
		for (String word : document)
		{
			if (term.equalsIgnoreCase(word))
				return true;
		}
		return false;
	}

	public static int documentsContainingTerm(String term, String[][] corpus)
	{
		int n = 0;

		for (String[] document : corpus)
		{
			n += hasTerm(term, document) ? 1 : 0;
		}

		return n;
	}

	public static double inverseDocumentFrequency(String term, String[][] corpus)
	{
		return Math.log(corpus.length / (1 + documentsContainingTerm(term, corpus)));
	}

	public static double termFrequencyInverseDocumentFrequency(String term, String[] document, String[][] corpus)
	{
		return termFrequency(term, document) * inverseDocumentFrequency(term, corpus);
	}

	public static String[] getUniqueTerms(String[][] corpus)
	{
		ArrayList<String> uniqueTerms = new ArrayList<>();

		for (String[] document : corpus)
		{
			for (String term : document)
			{
				if (!uniqueTerms.contains(term))
					uniqueTerms.add(term);
			}
		}

		return uniqueTerms.toArray(new String[0]);
	}

	public static Hashtable<String, Double> vectorize(String[] document, String[][] corpus, String[] uniqueTerms)
	{
		Hashtable<String, Double> vector = new Hashtable<>();

		for (String term : uniqueTerms)
		{
			vector.put(term, termFrequencyInverseDocumentFrequency(term, document, corpus));
		}

		return vector;
	}

	public static double cosine(Hashtable<String, Double> v1,  Hashtable<String, Double> v2)
	{
		return dotProduct(v1, v2)/(norm(v1) * norm(v2));
	}

	public static double dotProduct(Hashtable<String, Double> v1,  Hashtable<String, Double> v2)
	{
		double sum = 0;

		for (String key : v1.keySet())
		{
			sum += v1.get(key) * v2.get(key);
		}

		return sum;
	}

	public static double norm( Hashtable<String, Double> vector)
	{
		return Math.sqrt(dotProduct(vector, vector));
	}


	public static void main(String[] args) throws Exception
	{
		Bridges bridges = new Bridges(109, "121899988829", "agoncharow");

		String lyrics = SongStrings.iFeelGood;	// Lyrics function here
		String[][] corpus = HelperFunctions.splitLines(lyrics);			// returns cleaned up corpus
		String[] uniqueTerms = getUniqueTerms(corpus);	// returns unique terms
		int amountOfDocuments = corpus.length;
		Hashtable<String, Double>[] documentVectors = new Hashtable[amountOfDocuments];

		for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
		{
			documentVectors[i] = vectorize(corpus[i], corpus, uniqueTerms);
		}

		double[][] cosineSimilarityMatrix = new double[amountOfDocuments][amountOfDocuments];

		for (int i = 0; i < amountOfDocuments; i++)
		{
			for (int j = 0; j < amountOfDocuments; j++)
			{
				// calculates the cosine between the vectors and stores it in the corresponding index of the matrix
				cosineSimilarityMatrix[j][i] = cosine(documentVectors[i], documentVectors[j]);
			}
		}

		int[] RGBValues = {0, 0, 0};

		ColorGrid grid = HelperFunctions.getGrid(cosineSimilarityMatrix, RGBValues);
		bridges.setDataStructure(grid);
		bridges.setServer("clone");
		bridges.setTitle("Cosine Similarity Matrix Example");
		bridges.setDescription("James Brown - I Feel Good");
		bridges.visualize();
	}
}
