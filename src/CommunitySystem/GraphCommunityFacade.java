package CommunitySystem;

import Graph.DirectedGraph;
import Graph.GraphManager;
import GraphBuilders.GraphArchitect;
import constants.BuiltInGraphs;

public class GraphCommunityFacade {

    private GraphManager graphManager;

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

    private CommunityLibrary communityLibrary;
    private DirectedGraph currentGraph;
    private Community currentCommunity;

    public GraphCommunityFacade(GraphManager graphManager, CommunityLibrary communityLibrary, boolean includeBuiltInGraph) {
        this.graphManager = graphManager;
        this.communityLibrary = communityLibrary;

        if (includeBuiltInGraph) {
            GraphArchitect graphArchitect = new GraphArchitect();
            for (String builtInGraph : BuiltInGraphs.BUILT_IN_GRAPHS) {
                try {
                    DirectedGraph graphToAdd = graphArchitect.setBuilderAndBuildGraph(builtInGraph);
                    createVertices(graphToAdd);
                    graphManager.addGraph(graphToAdd);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createVertices(DirectedGraph graph) {
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

}
