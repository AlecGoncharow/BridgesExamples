import java.util.ArrayList;
import java.util.Hashtable;

public class HelperFunctions
{
	public static String[] splitLyrics(String lyrics)                // splits raw lyrics string into a parsable array
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
	public static int documentsContainingTerm(String term, String[][] corpus)
	{
		int n = 0;

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


	public static Hashtable<String, Double> vectorize(String[] document, String[][] corpus, String[] uniqueTerms)
	{
		Hashtable<String, Double> vector = new Hashtable<>();

		for (String term : uniqueTerms)
		{
			vector.put(term, termFrequencyInverseDocumentFrequency(term, document, corpus));
		}

		return vector;
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
}
