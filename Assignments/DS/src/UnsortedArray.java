import java.util.ArrayList;


public class UnsortedArray<K, V> implements Dictionary<K, V>
{
	private static final int DEFAULT_CAPACITY = 11;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	private int capacity;
	private int size;
	private double loadFactor;
	private double load;
	private Entry[] array;
	private ArrayList<K> keySet;
	private ArrayList<V> valueSet;

	public UnsortedArray()
	{
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public UnsortedArray(int capacity)
	{
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public UnsortedArray(int capacity, double loadFactor)
	{
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		this.array = new Entry[capacity];
		this.load = 0.0;
		this.size = 0;
		this.keySet = new ArrayList<K>();
		this.valueSet = new ArrayList<V>();
	}

	@Override
	public V put(K key, V value)
	{

		V contains = get(key);

		if (contains == null)
		{
			Entry<K, V> newEntry = new Entry<>(key, value);
			array[size++] = newEntry;
			this.keySet.add(key);
			this.valueSet.add(value);
			this.load = ((double) size) / capacity;

			if (load > loadFactor)
			{
				capacity *= 2;
				Entry[] temp = new Entry[capacity];
				System.arraycopy(array, 0, temp, 0, size);
				array = temp;
			}
		}
		else
		{
			for (Entry pair : array)
			{
				if (pair == null) break;

				if (pair.key.equals(key))
				{
					this.valueSet.remove(pair.value);
					pair.value = value;
					this.valueSet.add(value);
					break;
				}
			}
		}

		return contains;
	}

	@Override
	public V get(K key)
	{
		for (Entry pair : array)
		{
			if (pair == null) break;

			if (pair.key.equals(key))
			{
				return (V)pair.value;
			}
		}

		return null;
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
}



