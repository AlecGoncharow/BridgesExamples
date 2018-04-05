
public class Entry<K, V>
{
	public K key;
	public V value;

	public Entry()
	{
		this(null, null);
	}

	public Entry(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
}
