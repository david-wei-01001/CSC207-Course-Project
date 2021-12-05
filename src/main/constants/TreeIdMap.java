package constants;

import java.util.HashMap;

public class TreeIdMap {
    private final HashMap<String, String> idMap;

    /**
     * TreeId Map Constructor.
     * @param idMap the id of the map
     */
    public TreeIdMap(HashMap<String, String> idMap) {
        this.idMap = idMap;
    }

    /**
     * UserId to get IDMap.
     * @return return the id of the map
     */
    public HashMap<String, String> getIdMap() {
        return idMap;
    }

    /**
     * Used set for new tree ID relationship.
     * @param treeId the id of the tree
     * @param graphName the name of the graph
     */
    public void setIdMap(String treeId, String graphName) {
        if(idMap.containsKey(treeId)) {
            this.idMap.replace(treeId, graphName);
        }
        else
        {
            idMap.put(treeId, graphName);
        }
    }

    public String useIdToGetName(String treeId) {
        return idMap.get(treeId);
    }

}
