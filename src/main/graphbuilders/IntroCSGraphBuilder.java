package graphbuilders;

import graph.DirectedGraph;
import graph.Vertex;
import constants.BuiltInGraphs;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible to build the IntroCSGraph built-in default DirectedGraph
 */
public class IntroCSGraphBuilder implements GraphBuilder {
    /**
     * build vertices for a DirectedGraph.
     * @return a Map maps the name of vertices to vertices itself
     */
    @Override
    public Map<String, Vertex> buildVertices() {

        Map<String, Vertex> result = new HashMap<>();
        String[] vertexNames = {"Introductory Python",
                                "CSC165",
                                "Introductory Java",
                                "Introductory C++",
                                "CSC236",
                                "CSC209",
                                "CSC263"};

        for (String vertexName : vertexNames) {
            result.put(vertexName, new Vertex(vertexName));

        }

        return result;
    }

    /**
     * Build directed edges where vertices of edges are from vertices.
     * @param vertices contains all vertex as building blocks of directed edges
     * @return return an Array of DirectedEdges
     */
    @Override
    public Vertex[][] buildDirectedEdges(Map<String, Vertex> vertices) {

        return new Vertex[][]{
                new Vertex[]{vertices.get("Introductory Python"), vertices.get("Introductory Java")},
                new Vertex[]{vertices.get("Introductory Python"), vertices.get("Introductory C++")},
                new Vertex[]{vertices.get("CSC165"), vertices.get("CSC236")},
                new Vertex[]{vertices.get("CSC236"), vertices.get("CSC263")},
                new Vertex[]{vertices.get("Introductory Java"), vertices.get("CSC209")},
                new Vertex[]{vertices.get("Introductory Python"), vertices.get("CSC165")}
        };
    }

    /**
     * @return a DirectedGraph that is a built-in default main. graph
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     */
    @Override
    public DirectedGraph buildGraph() throws Exception {

        Map<String, Vertex> vertices = buildVertices();

        Vertex[][] directedEdges = buildDirectedEdges(vertices);
        DirectedGraph result = new DirectedGraph(new Vertex[] {vertices.get("Introductory Python")},
                BuiltInGraphs.INTRODUCTORY_CS_SERIES);
        for (Vertex[] directedEdge : directedEdges) {
            result.addEdge(directedEdge);
        }
        result.setTreeId();
        return result;
    }

}
