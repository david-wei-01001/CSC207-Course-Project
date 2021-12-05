package constants;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TreeidMapTest {

    @Test
    void getIdMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", "Introductory CS Series");
        TreeidMap idmap = new TreeidMap(map);
        assert (map.equals(idmap.getIdMap()));
    }

    @Test
    void setIdmap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("1", "Introductory CS Series");
        TreeidMap idmap = new TreeidMap(map);
        idmap.setIdmap("2","Introductory Makeup Steps");
        map.put("2","Introductory Makeup Steps");
        assert (map.equals(idmap.getIdMap()));
    }
}