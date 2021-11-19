package Graph;

import constants.Exceptions;

import java.util.HashMap;

/**
 *
 */
public class GraphManager {
    private final HashMap<String, DirectedGraph> mapOfGraphs = new HashMap<>();
    private int numberOfGraphs;
    private DirectedGraph currentGraph;

    public HashMap<String, DirectedGraph> getAllGraphs(){
        return mapOfGraphs;
    }

    public void addGraph(DirectedGraph graph) {
        numberOfGraphs += 1;
        mapOfGraphs.put(Integer.toString(numberOfGraphs), graph);

    }



    /**
     * change String graphName to enum in the future
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

    public void complete(String name) throws Exception {
        currentGraph.complete(name);
    }

    public Vertex getVertex(String name) throws Exception {
        return currentGraph.getVertex(name);
    }

    public String getName() {
        return currentGraph.toString();
    }



    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }


}
