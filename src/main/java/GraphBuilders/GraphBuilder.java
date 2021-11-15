package GraphBuilders;

import Graph.DirectedGraph;
import Graph.Vertex;

import java.util.Map;

public interface GraphBuilder {

    Map<String, Vertex> buildVertices();

    Vertex[][] buildDirectedEdges(Map<String, Vertex> vertices);

    DirectedGraph buildGraph();
}
