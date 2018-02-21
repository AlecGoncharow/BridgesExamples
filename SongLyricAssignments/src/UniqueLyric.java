import java.util.ArrayList;

/**
 * Created by Alec on Feb 11, 2018.
 */
public class UniqueLyric extends Lyric
{
	int occurrences;
	ArrayList<Integer> indices;
	int lastOccurence;

	public UniqueLyric()
	{
		this.occurrences = 0;
		this.lyric = null;
		this.indices = new ArrayList<>();
	}

	public UniqueLyric(String lyric)
	{
		super(lyric);
		this.occurrences = 0;
		indices = new ArrayList<>();
	}

	public UniqueLyric(String lyric, int index)
	{
		super(lyric, index);
		this.occurrences = 0;
		indices = new ArrayList<>();
	}

	public void addOccurrence(int index)
	{
		indices.add(index);
		this.occurrences++;
		lastOccurence = index;
	}

	public int compareTo(Object o)
	{
		if (o instanceof UniqueLyric)
		{
			UniqueLyric other = (UniqueLyric) o;

			if (this.occurrences > other.occurrences)
				return -1;
			else if (this.occurrences < other.occurrences)
				return 1;
			else
				return 0;
		}
		else
			return 0;
	}

	public String toString()
	{
		return String.format("%s, %d, %s", super.lyric, occurrences, indices);
	}
}