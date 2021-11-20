package GraphBuilders;

import Graph.DirectedGraph;
import Graph.Vertex;

import java.util.HashMap;
import java.util.Map;

public class IntroCSGraphBuilder implements GraphBuilder {

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


    @Override
    public DirectedGraph buildGraph() throws Exception {

        Map<String, Vertex> vertices = buildVertices();

        Vertex[][] directedEdges = buildDirectedEdges(vertices);
        DirectedGraph result = new DirectedGraph(new Vertex[] {vertices.get("Introductory Python")},
                                            "CS Introduction Series");
        for (Vertex[] directedEdge : directedEdges) {
            result.addEdge(directedEdge);
        }

        return result;
    }

}
