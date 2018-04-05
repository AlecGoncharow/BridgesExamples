
public class LinkedEntry<K, V> extends Entry<K, V>
{
	public LinkedEntry<K, V> next;
	public LinkedEntry(K key, V value)
	{
		super(key, value);
		this.next = null;
	}
}
