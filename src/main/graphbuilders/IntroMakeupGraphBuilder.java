package graphbuilders;

import graph.DirectedGraph;
import graph.Vertex;
import constants.BuiltInGraphs;

import java.util.HashMap;
import java.util.Map;

public class IntroMakeupGraphBuilder implements GraphBuilder{

    /**
     * build vertices for a DirectedGraph.
     * @return a Map maps the name of vertices to vertices itself
     */
    @Override
    public Map<String, Vertex> buildVertices() {

        Map<String, Vertex> result = new HashMap<>();
        String[] vertexNames = {"Foundation",
                "Concealer",
                "Makeup setting",
                "Eye shadow",
                "Eye linear",
                "Face blusher",
                "Contour",
                "Lipstick"};

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
                new Vertex[]{vertices.get("Foundation"), vertices.get("Concealer")},
                new Vertex[]{vertices.get("Concealer"), vertices.get("Makeup setting")},
                new Vertex[]{vertices.get("Makeup setting"), vertices.get("Face blusher")},
                new Vertex[]{vertices.get("Makeup setting"), vertices.get("Eye shadow")},
                new Vertex[]{vertices.get("Eye shadow"), vertices.get("Eye linear")},
                new Vertex[]{vertices.get("Makeup setting"), vertices.get("Contour")},
                new Vertex[]{vertices.get("Makeup setting"), vertices.get("Lipstick")},
        };
    }

    /**
     * @return a DirectedGrah that is a built-in default main.graph
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     */
    @Override
    public DirectedGraph buildGraph() throws Exception {

        Map<String, Vertex> vertices = buildVertices();

        Vertex[][] directedEdges = buildDirectedEdges(vertices);
        DirectedGraph result = new DirectedGraph(new Vertex[] {vertices.get("Foundation")},
                BuiltInGraphs.INTRODUCTORY_MAKEUP);
        for (Vertex[] directedEdge : directedEdges) {
            result.addEdge(directedEdge);
        }
        result.setTreeid(1);
        return result;
    }

}
