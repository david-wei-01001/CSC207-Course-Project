package main.graphbuilders;

import main.graph.DirectedGraph;
import main.constants.Exceptions;

/**
 * A class to select GraphBuilders and build corresponding graphs.
 */
public class GraphArchitect {

//    enum BuiltInTechnicalTree {
//        INTRODUCTORY_CS_SERIES;
//    }

    /*
     * GraphArchitect imitates Class Chef in the lecture material.
     */

    /**
     * select GraphBuilders according to treeName and build corresponding graphs.
     * @param treeName the name of a default tree to be build
     * @return a DirectedGraph with the treeName
     * @throws Exception if the name does not represent a default tree
     */
    public DirectedGraph setBuilderAndBuildGraph(String treeName) throws Exception {

        // create more if statements when we come up with more built-in Technical Trees.
        GraphBuilder graphBuilder;
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
