package maps;

import maps.TreeIdMap;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Test the TreeIdMap class
 */
class TreeIdMapTest {

    /**
     * Test if the getIdMap method correctly retrieve an idMap.
     */
    @Test
    void testGetIdMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Introductory CS Series");
        TreeIdMap idMap = new TreeIdMap(map);
        assert (map.equals(idMap.getIdMap()));
    }

    /**
     * Test if the setIdMap method correctly set an idMap.
     */
    @Test
    void testSetIdMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Introductory CS Series");
        TreeIdMap idMap = new TreeIdMap(map);
        idMap.setIdMap("2","Introductory Makeup Steps");
        map.put("2","Introductory Makeup Steps");
        assert (map.equals(idMap.getIdMap()));
    }
}