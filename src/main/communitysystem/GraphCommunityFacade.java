package communitysystem;

import graph.DirectedGraph;
import graph.GraphManager;
import graphbuilders.GraphArchitect;
import constants.BuiltInGraphs;

/**
 * This Facade class unifies communityLibrary and graphManager.
 */
public class GraphCommunityFacade {

    private final GraphManager graphManager;
    private final CommunityLibrary communityLibrary;
    private DirectedGraph currentGraph;
    private Community currentCommunity;

    /**
     * @param includeBuiltInGraphs true if include all built-in graphs of this program.
     */
    public GraphCommunityFacade(GraphManager graphManager, CommunityLibrary communityLibrary, boolean includeBuiltInGraphs) {
        this.graphManager = graphManager;
        this.communityLibrary = communityLibrary;

        if (includeBuiltInGraphs) {
            for (String builtInGraph : BuiltInGraphs.BUILT_IN_GRAPHS) {
                try {
                    DirectedGraph graphToAdd = GraphArchitect.setBuilderAndBuildGraph(builtInGraph);
                    createCommunities(graphToAdd);
                    graphManager.addGraph(graphToAdd);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
     *
     * @param graphId
     * @throws Exception
     */
    public void setCurrentGraph(String graphId) throws Exception {
        graphManager.setCurrentGraph(graphId);
    }

//    public void setCurrentCommunity(String communityName) throws Exception {
//        communityLibrary.setCurrentCommunity(communityName);
//    }

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
