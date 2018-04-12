import bridges.base.ColorGrid;
import bridges.connect.Bridges;

import java.util.ArrayList;
import java.util.Hashtable;

public class LineSimilaritySkeleton
{
    // This method should return the frequency of a term within a document
    public static int termFrequency(String term, String[] document)
    {
       return 0;
    }

    // This method should return a boolean indicating whether or not a term is within a document
    public static boolean hasTerm(String term, String[] document)
    {
        return false;
    }

    // This method should return the number of documents that contain at least one instance of some term
    public static int documentsContainingTerm(String term, String[][] corpus)
    {
       return 0;
    }

    // This method should return the inverseDocumentFrequency of a term in a corpus which is
    // the log of the number of documents in a corpus divided by 1 + documents containing that term
    public static double inverseDocumentFrequency(String term, String[][] corpus)
    {
        return 0;
    }

    // This method should return the product of the termFrequency of a term in a document and
    // the inverseDocumentFrequency of a term in a corpus
    public static double termFrequencyInverseDocumentFrequency(String term, String[] document, String[][] corpus)
    {
        return 0;
    }

    // This method should return an array of all unique terms in the corpus
    public static String[] getUniqueTerms(String[][] corpus)
    {
        return null;
    }

    // This method should create a new Hashtable, using each unique term as a key and the TFIDF value for that term as the value
    public static Hashtable<String, Double> vectorize(String[] document, String[][] corpus, String[] uniqueTerms)
    {
        return null;
    }

    // This method should return the dotProduct of two vectors divided by the product of their norms
    public static double cosine(Hashtable<String, Double> v1,  Hashtable<String, Double> v2)
    {
        return 0;
    }

    // This method should return the dotProduct of two vectors, which is the sum of the product of every element in each vector
    // i.e [4, 5, 6] * [1, 2, 3] = (4 * 1) + (5 * 2) + (6 * 3) = 32
    public static double dotProduct(Hashtable<String, Double> v1,  Hashtable<String, Double> v2)
    {
        return 0;
    }

    // This method should return the dotProduct of a vector with itself
    public static double norm(Hashtable<String, Double> vector)
    {
        return 0;
    }


    public static void main(String[] args) throws Exception
    {
        Bridges bridges = new Bridges(-1, "API_KEY_HERE", "USER_ID_HERE");

        String lyrics = SongStrings.bohemianRhapsody;	// Lyrics function here
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
        bridges.visualize();
    }
}
