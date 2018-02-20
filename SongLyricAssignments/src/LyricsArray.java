/**
 * Created by Alec on Feb 11, 2018.
 */
import bridges.base.Array;
import bridges.base.Element;
import bridges.connect.Bridges;

import java.util.ArrayList;
import java.util.Comparator;

public class LyricsArray
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
			"Any way the wind blows";

	public static void main(String[] args) throws Exception
	{
		Bridges bridges = new Bridges(4, "904691509236", "agoncharow");

		String[] lyricsSplit = LyricsUtils.splitLyrics(lyrics);

		NewLyric[] convert = new NewLyric[lyricsSplit.length];	//converts the Strings into a class I made to make parsing easier on myself
		for (int i = 0; i < convert.length; i++)
		{
			convert[i] = new NewLyric(lyricsSplit[i], i);
		}

		ArrayList<UniqueLyric> uniqueLyrics = new ArrayList();

		for (NewLyric word : convert)	//parses the individual lyrics and indexes where the repeats are
		{
			if (uniqueLyrics.contains(word))
			{
				uniqueLyrics.get(uniqueLyrics.indexOf(word)).addOccurrence(word.index);
			}
			else
			{
				uniqueLyrics.add(word);
				uniqueLyrics.get(uniqueLyrics.indexOf(word)).addOccurrence(word.index);
			}
		}

		uniqueLyrics.sort((UniqueLyric o1, UniqueLyric o2) -> o1.compareTo(o2));	//sorts the array based on occurrences, highest first

		int[] dims = {uniqueLyrics.size(), 1, 1};
		Array<UniqueLyric> visualArray = new Array<>(1, dims);

		for (int i = 0; i < uniqueLyrics.size(); i++)
		{
			visualArray.setValue(i,new Element<UniqueLyric>(uniqueLyrics.get(i).toString(), uniqueLyrics.get(i)));
			visualArray.getValue(i).getVisualizer().setColor(0, (uniqueLyrics.get(i).occurrences * 9) + 100, 0, 1);
		}


		bridges.setDataStructure(visualArray);

		bridges.visualize(); //output: http://bridges-cs.herokuapp.com/assignments/4/agoncharow
	}
}
