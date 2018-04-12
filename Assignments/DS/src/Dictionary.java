import java.util.ArrayList;

public interface Dictionary<K, V>
{
	// Stores the Key Value pair in your data structure, returns old value if the key was already assigned.
	V put(K key, V value);

	// Returns the value linked to the given key
	V get(K key);

	// Returns an ArrayList of all the keys
	ArrayList<K> getKeySet();

	// Returns an ArrayList of all the values
	ArrayList<V> getValueSet();
}
