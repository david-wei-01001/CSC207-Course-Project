package Graph;

import constants.Exceptions;

import java.util.HashMap;

/**
 * THis class store and manage all DirectedGraphs of a user.
 */
public class GraphManager {

    private final HashMap<String, DirectedGraph> mapOfGraphs = new HashMap<>();
    private int numberOfGraphs;
    private DirectedGraph currentGraph;

    /**
     * @return the instance variable mapOfGraphs
     */
    public HashMap<String, DirectedGraph> getAllGraphs(){
        return mapOfGraphs;
    }

    /**
     * add a DirectedGraph to the GraphManager.
     * @param graph a DirectedGraph to be added
     */

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

    /**
     *  complete a vertex in the current graph.
     * @param name the name of the vertex to be completed
     * @throws Exception if the name of the vertex does not exist in the current graph
     * or if the vertex is currently locked or if the vertex is already completed.
     */
    public void complete(String name) throws Exception {
        currentGraph.complete(name);
    }

    /**
     * return a vertex with the provided name in the current graph.
     * @param name The name of a Vertex
     * @return Return the vertex with name
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Vertex getVertex(String name) throws Exception {
        return currentGraph.getVertex(name);
    }

    /**
     * @return a string representation of the current graph
     */
    public String getName() {
        return currentGraph.toString();
    }


    /**
     * @return the instance variable currentGraph
     */
    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }
    public HashMap<String, DirectedGraph> getMapOfGraphs(){
        return mapOfGraphs;
    }


}
