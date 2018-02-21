/**
 * Created by Alec on Feb 11, 2018.
 */
public class Lyric
{
	public int index;
	public String lyric;


	public Lyric()
	{
		this.index = -1;
		this.lyric = null;
	}

	public Lyric(String lyric)
	{
		this.index = -1;
		this.lyric = lyric;
	}

	public Lyric(String lyric, int index)
	{
		this.lyric = lyric;
		this.index = index;
	}

	@Override
	public boolean equals(Object O)
	{
		if (O instanceof Lyric)
		{
			Lyric other = (Lyric) O;

			if (this.lyric.equalsIgnoreCase(other.lyric))
				return true;
			else
				return false;
		}
		else
			return false;
	}


}
