import java.util.ArrayList;

public class HelperFunctions
{
	public static String[] splitDocument(String lyrics)                // splits raw lyrics string into a parsable array
	{
		lyrics = lyrics.replaceAll("\\[.+\\]", "");    // removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\s+");
		for (int i = 0; i < lyricsSplit.length; i++)                    // clears special characters from individual terms
		{
			lyricsSplit[i] = lyricsSplit[i].replaceAll("\\W+$", "");
			lyricsSplit[i] = lyricsSplit[i].replaceAll("^\\W+", "");
			lyricsSplit[i] = lyricsSplit[i].trim();
		}

		return lyricsSplit;
	}

	public static String[][] splitLines(String lyrics)
	{
		lyrics = lyrics.replaceAll("\\[.+\\]", "");    // removes the titles of song stage ex [Intro]
		lyrics = lyrics.trim();
		String[] lyricsSplit = lyrics.split("\\n+");
		String[][] corpus = new String[lyricsSplit.length][];
		for (int i = 0; i < corpus.length; i++)                    // clears special characters from individual terms
		{
			corpus[i] = lyricsSplit[i].split("\\s+");
			for (int j = 0; j < corpus[i].length; j++)
			{
				corpus[i][j] = corpus[i][j].replaceAll("\\W+$", "");
				corpus[i][j] = corpus[i][j].replaceAll("^\\W+", "");
				corpus[i][j] = corpus[i][j].trim();
			}
		}

		return corpus;
	}

	//could be made private
	public static int termFrequency(String term, String[] document)
	{
		int tf = 0;

		for (String word : document)
		{
			tf += term.equalsIgnoreCase(word) ? 1 : 0;
		}

		return tf;
	}

	//could be made private
	public static boolean hasTerm(String term, String[] document)
	{
		for (String word : document)
		{
			if (term.equalsIgnoreCase(word))
				return true;
		}
		return false;
	}

	//could be made private
	public static double documentsContainingTerm(String term, String[][] corpus)
	{
		double n = 0;

		for (String[] document : corpus)
		{
			n += hasTerm(term, document) ? 1 : 0;
		}

		return n;
	}

	//could be made private
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

	public static String[] getUniqueTerms(Song[] corpus)
	{
		ArrayList<String> uniqueTerms = new ArrayList<>();

		for (Song document : corpus)
		{
			for (String term : splitDocument(document.getLyrics()))
			{
				if (!uniqueTerms.contains(term))
					uniqueTerms.add(term);
			}
		}

		return uniqueTerms.toArray(new String[0]);
	}


	public static Hashtable getHashtableVector(String[] document, String[][] corpus, String[] uniqueTerms)
	{
		Hashtable vector = new Hashtable();

		for (String term : uniqueTerms)
		{
			vector.put(term, termFrequencyInverseDocumentFrequency(term, document, corpus));
		}

		return vector;
	}

	public static Hashtable getHashtableVector(Song document, Song[] corpus, String[] uniqueTerms)
	{
		String[][] corpusStrings = new String[corpus.length][];

		for (int i = 0; i < corpus.length; ++i)
		{
			corpusStrings[i] = splitDocument(corpus[i].getLyrics());
		}

		return getHashtableVector(splitDocument(document.getLyrics()), corpusStrings, uniqueTerms);
	}

	public static UnsortedArray getUnsortedArrayVector(String[] document, String[][] corpus, String[] uniqueTerms)
	{
		UnsortedArray vector = new UnsortedArray();

		for (String term : uniqueTerms)
		{
			vector.put(term, termFrequencyInverseDocumentFrequency(term, document, corpus));
		}

		return vector;
	}

	public static double dotProduct(Dictionary v1, Dictionary v2)
	{
		double sum = 0;

		for (Object key : v1.getKeySet())
		{
			sum += (double)v1.get(key) * (double)v2.get(key);
		}

		return sum;
	}

	public static double norm( Dictionary vector)
	{
		return Math.sqrt(dotProduct(vector, vector));
	}

	public static double cosine(Dictionary<String, Double> v1, Dictionary<String, Double> v2)
	{
		return dotProduct(v1, v2)/(norm(v1) * norm(v2));
	}
}
