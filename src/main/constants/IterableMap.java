package constants;

import java.util.HashMap;
import java.util.Iterator;

public class IterableMap<K, V> extends HashMap<K, V> implements Iterable<K> {

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
