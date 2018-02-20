import java.util.ArrayList;

/**
 * Created by Alec on Feb 11, 2018.
 */
public class UniqueLyric
{
	int occurrences;
	String lyric;
	ArrayList<Integer> indices;


	public UniqueLyric()
	{

	}

	public UniqueLyric(String lyric)
	{
		this.occurrences = 0;
		this.lyric = lyric;
		indices = new ArrayList<>();
	}

	public void addOccurrence(int index)
	{
		indices.add(index);
		this.occurrences++;
	}

	@Override
	public boolean equals(Object O)
	{
		if (O instanceof UniqueLyric)
		{
			UniqueLyric other = (UniqueLyric) O;

			if (this.lyric.equalsIgnoreCase(other.lyric))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public String toString()
	{
		return String.format("%s, %d, %s", lyric, occurrences, indices);
	}
}