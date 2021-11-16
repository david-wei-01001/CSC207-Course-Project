package CommunitySystem;

import Graph.DirectedGraph;
import Graph.GraphManager;
import GraphBuilders.GraphArchitect;
import constants.BuiltInGraphs;

/**
 * This Facade class unifies communityLibrary and graphManager.
 */
public class GraphCommunityFacade {

    private GraphManager graphManager;
    private CommunityLibrary communityLibrary;
    private DirectedGraph currentGraph;
    private Community currentCommunity;

    /**
     * @param includeBuiltInGraphs true if include all built-in graphs of this program.
     */
    public GraphCommunityFacade(GraphManager graphManager, CommunityLibrary communityLibrary, boolean includeBuiltInGraphs) {
        this.graphManager = graphManager;
        this.communityLibrary = communityLibrary;

        if (includeBuiltInGraphs) {
            GraphArchitect graphArchitect = new GraphArchitect();
            for (String builtInGraph : BuiltInGraphs.BUILT_IN_GRAPHS) {
                try {
                    DirectedGraph graphToAdd = graphArchitect.setBuilderAndBuildGraph(builtInGraph);
                    createCommunities(graphToAdd);
                    graphManager.addGraph(graphToAdd);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Create the communities corresponding to the vertices of the inputted graph
     * @param graph the graph whose vertices' community need to be created
     */
    private void createCommunities(DirectedGraph graph) {
        for (String vertexName : graph.getVertices().keySet()) {
            communityLibrary.addCommunity(vertexName);
        }
    }

    public void setCurrentGraph(String graphId) throws Exception {
        graphManager.setCurrentGraph(graphId);
    }

    public void setCurrentCommunity(String communityName) throws Exception {
        communityLibrary.setCurrentCommunity(communityName);
    }

    public GraphManager getGraphManager() {
        return graphManager;
    }

    public CommunityLibrary getCommunityLibrary() {
        return communityLibrary;
    }

    public DirectedGraph getCurrentGraph() {
        return currentGraph;
    }

    public Community getCurrentCommunity() {
        return currentCommunity;
    }

}
