import bridges.base.GraphAdjList;
import bridges.connect.Bridges;

public class RelationalGraph
{
    public static void main(String[] args) throws Exception
    {
        Bridges bridges = new Bridges(115, "1306669339612", "agoncharow");

        GraphAdjList<Song, Hashtable<String, Double>> graph = new GraphAdjList<>();

        double threshold = 0.07;

        Song song1 = SongData.bohemianRhapsody;
        Song song2 = SongData.feelGoodInc;
        Song song3 = SongData.iFeelGood;
        Song song4 = SongData.i;
        Song song5 = SongData.humble;

        Song[] corpus = new Song[5];
        corpus[0] = song1;
        corpus[1] = song2;
        corpus[2] = song3;
        corpus[3] = song4;
        corpus[4] = song5;

        String[] uniqueTerms = HelperFunctions.getUniqueTerms(corpus);
        int amountOfDocuments = corpus.length;

        Hashtable<String, Double>[] documentVectors = new Hashtable[amountOfDocuments];

        for (int i = 0; i < amountOfDocuments; i++)						//gets a hashtable representing the tfidf values of all terms in the corpus per document
        {
            documentVectors[i] = HelperFunctions.getHashtableVector(corpus[i], corpus, uniqueTerms);
            graph.addVertex(corpus[i], documentVectors[i]);
        }

        for (int i = 0; i < amountOfDocuments; ++i)
        {
            for (int j = 0; j < amountOfDocuments; ++j)
            {
                double cosine = HelperFunctions.cosine(documentVectors[i], documentVectors[j]);
                if (cosine >= threshold)
                {
                    graph.addEdge(corpus[i], corpus[j]);
                }
            }
        }


        bridges.setDataStructure(graph);
        bridges.setServer("clone");
        bridges.visualize();
    }
}
