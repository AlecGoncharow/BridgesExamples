import java.util.ArrayList;

public interface Dictionary<K, V>
{
	V put(K key, V value);
	V get(K key);

	ArrayList<K> getKeySet();
	ArrayList<V> getValueSet();
}
