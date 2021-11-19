package Graph;

import constants.Exceptions;

import java.util.HashMap;

/**
 *
 */
public class GraphManager {
    private HashMap<String, DirectedGraph> mapOfGraphs = new HashMap<>();
    private DirectedGraph currentGraph;

    public void addGraph(DirectedGraph graph) {
        mapOfGraphs.put(Integer.toString(mapOfGraphs.size()), graph);
    }

    /**
     *
     * @param graphId
     * @throws Exception
     */
    public void setCurrentGraph(String graphId) throws Exception {
        if (mapOfGraphs.containsKey(graphId)) {
            this.currentGraph = mapOfGraphs.get(graphId);
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_TREE);
        }
    }



    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }
    public HashMap<String, DirectedGraph> getMapOfGraphs(){
        return mapOfGraphs;
    }


}
