import java.util.ArrayList;

/**
 * Created by Alec on Apr 3, 2018.
 */
public interface Dictionary<K, V>
{
	V put(K key, V value);
	V get(K key);

	ArrayList<K> getKeySet();
	ArrayList<V> getValueSet();
}
