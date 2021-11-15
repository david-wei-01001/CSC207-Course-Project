package GraphBuilders;

import Graph.DirectedGraph;
import constants.Exceptions;

public class GraphArchitect {

//    enum BuiltInTechnicalTree {
//        INTRODUCTORY_CS_SERIES;
//    }

    private GraphBuilder graphBuilder;

    /*
     * GraphArchitect imitates Class Chef in the lecture material.
     */

    public DirectedGraph setBuilderAndBuildGraph(String treeName) throws Exception {

        // create more if statements when we come up with more built-in Technical Trees.
        if (treeName.equals("Introductory CS Series")) {
            graphBuilder = new IntroCSGraphBuilder();
        } else {
            throw new Exception(Exceptions.CANNOT_RECOGNIZE_BUILT_IN_TREE);
        }

        // if no exception is thrown, graphBuilder != null/
//        if (graphBuilder != null) {
            return graphBuilder.buildGraph();
//        }
    }

}
