/**
 * Created by Alec on Feb 11, 2018.
 */
public class NewLyric extends UniqueLyric
{
	public int index;
	public String lyric;

	public NewLyric(String lyric, int index)
	{
		super(lyric);
		this.index = index;
	}
}
