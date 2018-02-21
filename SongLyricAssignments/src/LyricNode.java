/**
 * Created by Alec on Feb 21, 2018.
 */
public class LyricNode
{
	LyricNode next;
	LyricNode lastOccurence;
	int index;
	String lyric;
	int uniqueNumber;

	public LyricNode(String lyric, int index)
	{
		this.lyric = lyric;
		this.index = index;
		this.lastOccurence = null;
		this.next = null;
		this.uniqueNumber = -1;
	}

	public void setNext(LyricNode next)
	{
		this.next = next;
	}

	public void setLastOccurence(LyricNode lastOccurence)
	{
		this.lastOccurence = lastOccurence;
	}

	public void setUniqueNumber(int uniqueNumber)
	{
		this.uniqueNumber = uniqueNumber;
	}

	public String toString()
	{
		if (next != null && lastOccurence != null)
		{
			return String.format("%d: %s | next: %s | lastOccur: %d", this.index, this.lyric, this.next.lyric, this.lastOccurence.index);
		}
		else if (next != null)
		{
			return String.format("%d: %s | next: %s", this.index, this.lyric, this.next.lyric);
		}
		else if (next == null)
		{
			if (lastOccurence != null) return String.format("%d: %s | lastOccur: %s", this.index, this.lyric, this.lastOccurence.index);
			else return String.format("%d: %s", this.index, this.lyric);
		}
		else return "ERROR";
	}
}
