import bridges.connect.Bridges;
import bridges.base.DLelement;
import java.util.ArrayList;
import bridges.base.Color;

/**
 * This is an attempt to make a Double Linked list where each node a lyric that points to the next word in the song and the last occurrence of that same word in the song
 */
public class LyricsDoubleLinkedList
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
		Bridges bridges = new Bridges(5, "904691509236", "agoncharow");

		String[] lyricsSplit = LyricsUtils.splitLyrics(lyrics);

		ArrayList<UniqueLyric> uniqueLyrics = new ArrayList();

		ArrayList<LyricNode> list = new ArrayList<>();

		Lyric[] convert = LyricsUtils.convertToLyric(lyricsSplit);

		for (Lyric word : convert)	//parses the individual lyrics and indexes where the repeats are
		{
			if (uniqueLyrics.contains(word))
			{
				LyricNode newWord = new LyricNode(word.lyric, word.index);
				list.get(list.size() - 1).setNext(newWord);
				list.add(newWord);
				list.get(list.size() - 1).setLastOccurence(list.get(uniqueLyrics.get(uniqueLyrics.indexOf(word)).lastOccurence));
				uniqueLyrics.get(uniqueLyrics.indexOf(word)).addOccurrence(word.index);
				list.get(list.indexOf(newWord)).setUniqueNumber(uniqueLyrics.indexOf(word));
			}
			else
			{
				LyricNode newWord = new LyricNode(word.lyric, word.index);
				if (list.size() > 1) list.get(list.size() - 1).setNext(newWord);
				list.add(newWord);
				uniqueLyrics.add(new UniqueLyric(word.lyric, word.index));
				uniqueLyrics.get(uniqueLyrics.size() - 1).addOccurrence(word.index);
				list.get(list.indexOf(newWord)).setUniqueNumber(uniqueLyrics.size() - 1);
			}
		}

		Color[] colors = new Color[uniqueLyrics.size()];

		for (int i = 0; i < uniqueLyrics.size(); i++)
		{
			colors[i] = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		}

		DLelement<LyricNode>[] linkedList = new DLelement[list.size()];
		DLelement<LyricNode> head = new DLelement<>(list.get(0).lyric, list.get(0));
		linkedList[0] = head;
		head.getVisualizer().setColor(colors[list.get(0).uniqueNumber]);
		DLelement<LyricNode> curr = head;
		for (int i = 1; i < list.size(); i++)	//builds list
		{
			curr.setNext(new DLelement<>(list.get(i).lyric, list.get(i)));
			curr = curr.getNext();
			linkedList[i] = curr;
			curr.getVisualizer().setColor(colors[list.get(i).uniqueNumber]);
		}

		curr = head;
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).lastOccurence != null)
			{
				curr.setPrev(linkedList[list.get(i).lastOccurence.index]);
			}
			curr = curr.getNext();
		}


		bridges.setDataStructure(head);
		bridges.visualize();

	}
}
