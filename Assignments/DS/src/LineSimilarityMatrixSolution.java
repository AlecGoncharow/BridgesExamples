
public class LineSimilarityMatrixSolution
{
	static String lyrics = "[Intro]\n" +
			"Is this the real life? Is this just fantasy?\n" +
			"Caught in a landslide, no escape from reality\n" +
			"Open your eyes, look up to the skies and see\n" +
			"I'm just a poor boy, I need no sympathy\n" +
			"Because I'm easy come, easy go, little high, little low\n" +
			"Any way the wind blows doesn't really matter to me, to me\n" +
			"\n" +
			"[Verse 1]\n" +
			"Mama, just killed a man\n" +
			"Put a gun against his head, pulled my trigger, now he's dead\n" +
			"Mama, life had just begun\n" +
			"But now I've gone and thrown it all away\n" +
			"Mama, ooh, didn't mean to make you cry\n" +
			"If I'm not back again this time tomorrow\n" +
			"Carry on, carry on as if nothing really matters\n" +
			"\n" +
			"[Verse 2]\n" +
			"Too late, my time has come\n" +
			"Sends shivers down my spine, body's aching all the time\n" +
			"Goodbye, everybody, I've got to go\n" +
			"Gotta leave you all behind and face the truth\n" +
			"Mama, ooh, (any way the wind blows)\n" +
			"I don't want to die\n" +
			"I sometimes wish I'd never been born at all\n" +
			"\n" +
			"[Guitar Solo]\n" +
			"\n" +
			"[Verse 3]\n" +
			"I see a little silhouetto of a man\n" +
			"Scaramouche, Scaramouche, will you do the Fandango?\n" +
			"Thunderbolt and lightning, very, very fright'ning me\n" +
			"(Galileo.) Galileo. (Galileo.) Galileo. Galileo Figaro magnifico\n" +
			"I'm just a poor boy, nobody loves me\n" +
			"He's just a poor boy from a poor family\n" +
			"Spare him his life from this monstrosity\n" +
			"Easy come, easy go, will you let me go?\n" +
			"Bismillah! No, we will not let you go\n" +
			"(Let him go!) Bismillah! We will not let you go\n" +
			"(Let him go!) Bismillah! We will not let you go\n" +
			"(Let me go.) Will not let you go\n" +
			"(Let me go.) Will not let you go. (Let me go.) Ah\n" +
			"No, no, no, no, no, no, no\n" +
			"(Oh mamma mia, mamma mia) Mamma mia, let me go\n" +
			"Beelzebub has a devil put aside for me, for me, for me!\n" +
			"\n" +
			"[Verse 4]\n" +
			"So you think you can stone me and spit in my eye?\n" +
			"So you think you can love me and leave me to die?\n" +
			"Oh, baby, can't do this to me, baby!\n" +
			"Just gotta get out, just gotta get right outta here!\n" +
			"\n" +
			"[Guitar Solo]\n" +
			"\n" +
			"[Outro]\n" +
			"Nothing really matters, anyone can see\n" +
			"Nothing really matters\n" +
			"Nothing really matters to me\n" +
			"Any way the wind blows";	// Lyrics function here
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

		for(int i = 0; i < mat1.length; i++)
		{
			for(int j = 0; j < mat1.length; j++)
			{
				System.out.println(mat1[j][i] - mat2[j][i]);
			}
		}

		System.out.println(hash[0]);
		System.out.printf("\n\n HASHTIME: %d \n ARRAY: %d", (endTimeHash-startTimeHash) / 1000000 , (endTimeArray-endTimeHash) / 1000000);
		//Bridges visualize code
	}
}
