package constants;

import java.util.HashMap;
import java.util.Iterator;

public class IterableMap<K, V> extends HashMap<K, V> implements Iterable<K> {

    /**
     * Apply Iterator design pattern to HashMap so that loopinb over HashMap is now possible.
     *
     * @return an iterator class representation of HashMap
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
