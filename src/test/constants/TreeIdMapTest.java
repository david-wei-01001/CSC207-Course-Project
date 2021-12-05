package constants;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class TreeIdMapTest {

    @Test
    void getIdMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Introductory CS Series");
        TreeIdMap idMap = new TreeIdMap(map);
        assert (map.equals(idMap.getIdMap()));
    }

    @Test
    void setIdMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Introductory CS Series");
        TreeIdMap idMap = new TreeIdMap(map);
        idMap.setIdMap("2","Introductory Makeup Steps");
        map.put("2","Introductory Makeup Steps");
        assert (map.equals(idMap.getIdMap()));
    }
}