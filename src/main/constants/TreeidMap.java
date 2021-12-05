package constants;

import java.util.HashMap;

public class TreeidMap {
    private HashMap<String, String> idmap;

    /**
     * Treeid Map Constructor.
     * @param idmap
     */
    public TreeidMap(HashMap<String, String> idmap) {
        this.idmap = idmap;
    }

    /**
     * Userd to get IDmap.
     * @return
     */
    public HashMap<String, String> getIdMap() {
        return idmap;
    }

    /**
     * Used set for new tree ID relationship.
     * @param treeid
     * @param graphname
     */
    public void setIdmap(String treeid, String graphname) {
        if(idmap.containsKey(treeid)) {
            this.idmap.replace(treeid, graphname);
        }
        else
        {
            idmap.put(treeid, graphname);
        }
    }

    public String useIdToGetName(String treeid) {
        return idmap.get(treeid);
    }

}
