import java.util.ArrayList;

/**
 * Created by Alec on Apr 3, 2018.
 */
public class Hashtable<K, V> implements Dictionary<K, V>
{
	private static final int DEFAULT_CAPACITY = 113;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private int capacity;
	private int size;
	private double loadFactor;
	private double load;
	private LinkedEntry<K, V>[] array;
	private ArrayList<K> keySet;
	private ArrayList<V> valueSet;

	public Hashtable()
	{
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public Hashtable(int capacity)
	{
		this(capacity, DEFAULT_LOAD_FACTOR);
	}
	public Hashtable(int capacity, double loadFactor)
	{
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.array = new LinkedEntry[capacity];
		this.load = 0.0;
		this.size = 0;
		this.keySet = new ArrayList<K>();
		this.valueSet = new ArrayList<V>();
	}

	private int hash(K key)
	{
//		if (key instanceof String)
//		{
//			char[] chars = ((String) key).toCharArray();
//			int hash = 0;
//			for (int i = 0; i < chars.length; i++)
//			{
//				hash += Math.pow(31, chars.length - i)*((int)chars[i]);
//
//			}
//			return hash % capacity;
//		}
//		else
		int result = key.hashCode() % capacity;
		return result < 0 ? result + capacity : result;
	}

	@Override
	public V put(K key, V value)
	{
		int hash = hash(key);

		if (array[hash] == null)
		{
			this.array[hash] = new LinkedEntry<>(key, value);
			size++;
		}
		else
		{
			LinkedEntry<K, V> current = array[hash];
			while(current.next != null)
			{
				current = current.next;
			}
			current.next = new LinkedEntry<>(key, value);
		}

		this.keySet.add(key);
		this.valueSet.add(value);
		return null;
	}

	@Override
	public V get(K key)
	{
		int hash = hash(key);

		if (array[hash] == null)
			return null;
		else
		{
			LinkedEntry<K, V> current = array[hash];
			while(!current.key.equals(key))
			{
				if (current.next == null)
					return null;
				current = current.next;
			}
			return current.value;
		}
	}

	@Override
	public ArrayList<K> getKeySet()
	{
		return keySet;
	}

	@Override
	public ArrayList<V> getValueSet()
	{
		return valueSet;
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++)
		{
			str.append(i);
			str.append(": ");
			if (array[i] != null)
			{
				LinkedEntry<K, V> current = array[i];
				while(current.next != null)
				{
					str.append(current.key);
					current = current.next;
				}
			}
			str.append("\n");
		}
		return str.toString();
	}

}
