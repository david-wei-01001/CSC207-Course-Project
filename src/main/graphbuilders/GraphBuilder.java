package graphbuilders;

import graph.DirectedGraph;
import graph.Vertex;

import java.util.Map;

/**
 * An interface specifies what is needed to be a GraphBuilder.
 */
public interface GraphBuilder {
    /**
     * build vertices for a DirectedGraph.
     * @return a Map maps the name of vertices to vertices itself
     */
    Map<String, Vertex> buildVertices();

    /**
     * Build directed edges where vertices of edges are from vertices.
     * @param vertices contains all vertex as building blocks of directed edges
     * @return return an Array of DirectedEdges
     */
    Vertex[][] buildDirectedEdges(Map<String, Vertex> vertices);

    /**
     * @return a DirectedGraph that is a built-in default main. graph
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     */
    DirectedGraph buildGraph() throws Exception;
}
