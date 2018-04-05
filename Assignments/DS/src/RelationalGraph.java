public class RelationalGraph
{
    public static void main(String[] args)
    {
        String song1 = SongStrings.bohemianRhapsody;
        String song2 = SongStrings.feelGoodInc;
        String song3 = SongStrings.iFeelGood;

        String[][] corpus = new String[3][];
        corpus[0] = HelperFunctions.splitDocument(song1);
        corpus[1] = HelperFunctions.splitDocument(song2);
        corpus[2] = HelperFunctions.splitDocument(song3);

        String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);
        int amountOfDocuments = corpus.length;

        Hashtable<String, Double>[] documentVectors = new Hashtable[amountOfDocuments];

        double[][] cosineSimilarityMatrix = new double[amountOfDocuments][amountOfDocuments];

        for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
        {
            documentVectors[i] = HelperFunctions.getHastableVector(corpus[i], corpus, uniqueTerms);
        }


        for (int i = 0; i < amountOfDocuments; i++)
        {
            for (int j = 0; j < amountOfDocuments; j++)
            {
                // calculates the cosine between the vectors and stores it in the corresponding index of the matrix
                cosineSimilarityMatrix[j][i] = (HelperFunctions.dotProduct(documentVectors[i], documentVectors[j]) /
                        (HelperFunctions.norm(documentVectors[i])
                                * HelperFunctions.norm(documentVectors[j])));
                System.out.print(cosineSimilarityMatrix[j][i] + " ");
            }
            System.out.println();
        }

    }
}
