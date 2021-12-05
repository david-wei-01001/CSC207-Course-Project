package graph;

import communitysystem.CommunityLibrary;
import constants.BuiltInGraphs;
import constants.Exceptions;
import graphbuilders.GraphArchitect;

import java.util.HashMap;
import java.util.Map;

/**
 * This class store and manage all DirectedGraphs of a main.user.
 */
public class GraphManager {

    private final Map<String, DirectedGraph> mapOfGraphs = new HashMap<>();
    private DirectedGraph currentGraph;
    private CommunityLibrary communityLibrary;

    /**
     * Constructor of GraphManager
     */
    public void addBuiltInGraph(CommunityLibrary communityLibrary){
        setCommunityLibrary(communityLibrary);
        int i = 0;
        for (String builtInGraph : BuiltInGraphs.BUILT_IN_GRAPHS) {
            try {
                DirectedGraph graphToAdd = GraphArchitect.setBuilderAndBuildGraph(builtInGraph);
                createCommunities(graphToAdd);
                mapOfGraphs.put(Integer.toString(i), graphToAdd);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCommunityLibrary(CommunityLibrary communityLibrary){
        this.communityLibrary = communityLibrary;
    }



    /**
     * @return the instance variable mapOfGraphs
     */
    public Map<String, DirectedGraph> getAllGraphs(){
        return mapOfGraphs;
    }


    public String getAllGraphName(){
        StringBuilder stringBuilder = new StringBuilder();
        for(String i : mapOfGraphs.keySet()){
            stringBuilder.append(i).append(": ");
            stringBuilder.append(mapOfGraphs.get(i).getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
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
     * Create the communities corresponding to the vertices of the inputted main.graph
     * @param graph the main.graph whose vertices' community need to be created
     */
    private void createCommunities(DirectedGraph graph) {
        for (String vertexName : graph.getVertices().keySet()) {
            communityLibrary.addCommunity(vertexName);
        }
    }

    /**
     *  complete a vertex in the current main.graph.
     * @param name the name of the vertex to be completed
     * @throws Exception if the name of the vertex does not exist in the current main.graph
     * or if the vertex is currently locked or if the vertex is already completed.
     */
    public void complete(String name) throws Exception {
        currentGraph.complete(name);
    }

    /**
     * return a vertex with the provided name in the current main.graph.
     * @param name The name of a Vertex
     * @return Return the vertex with name
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Vertex getVertex(String name) throws Exception {
        return currentGraph.getVertex(name);
    }





    /**
     * @return the instance variable currentGraph
     */
    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }

    public Map<String, DirectedGraph> getMapOfGraphs(){
        return mapOfGraphs;
    }

    /**
     * @return a string representation of the current main.graph
     */
    public String displayCurrentGraph(){
        return currentGraph.toString();
    }
}
