package graph;

import constants.Exceptions;
import constants.HasName;
import maps.IterableMap;
import comparator.VertexArrayDefaultComparator;

import java.io.Serializable;
import java.util.*;

import static algorithm.Algorithm.*;

/**
 * A Directed Graph, which is the data structure used to represent a field of knowledge.
 */
public class DirectedGraph implements Serializable, HasName {

    /**
     * The key of VERTICES is a String which is the name of a Vertex, the value of the VERTICES is an VertexArray object
     * containing a starting vertex and all Vertices that is the ending vertex which the starting vertex points to.
     */
    private final IterableMap<String, VertexArray> VERTICES = new IterableMap<>();
    private final String NAME;
    private final List<String> CURRENTUNLOCK = new ArrayList<>();
    private final List<String> COMPLETED = new ArrayList<>();
    private int treeid;

    /**
     * get the number of completed vertex
     *
     * @return number of complete
     */
    public int getNumOfCOMPLETED() {
        return COMPLETED.size();
    }

    /**
     *
     * set tree id
     */

    /**
     * The constructor of the DirectedGraph class.
     *
     * @param lstVertex A list of vertex to be added to the instance of DirectedGraph
     * @param name      The name of the DirectedGraph
     */
    public DirectedGraph(Vertex[] lstVertex, String name) {
        for (Vertex v : lstVertex) {

            addVertex(v);
            CURRENTUNLOCK.add(v.getName());
        }
        this.NAME = name;
    }

    /**
     * This method will add the provided directed edge into the main.graph.
     * the starting vertex and ending vertex will be added into the main. graph,
     * and the ending vertex will be added into the ArrayList of the
     * starting vertex containing all vertices it points to.
     *
     * @param edge An array of length 2 which represents a directed edge
     *             containing the starting vertex at index 0 and ending vertex at index 1.
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     */
    public void addEdge(Vertex[] edge) throws Exception {
        if (!checkEdgeExistence(edge)) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            update(getVertexArray(edge[1]), false);
            getVertexArray(edge[0]).addEdge(edge[1]);
        } else {
            throw new Exception(Exceptions.EDGE_ALREADY_EXIST);
        }
    }

    /**
     * @param edge a DirectedEdge to check if it is in the DirectedGraph
     * @return Whether the given DirectedEdge exists in the DirectedGraph.
     */
    public boolean checkEdgeExistence(Vertex[] edge) {
        if (checkVertexExistence(edge[0])) {
            try {
                if (getVertexArray(edge[0]).isEnd(edge[1])) {
                    return true;
                }
            } catch (Exception ignored) {}
        }
        if (checkVertexExistence(edge[1])) {
            try {
                return getVertexArray(edge[1]).isEnd(edge[0]);
            } catch (Exception ignored) {}
        }
        return false;
    }

    /**
     * @param vertex a vertex to check if it is in the DirectedGraph
     * @return whether the given vertex is in the DirectedGraph
     */
    public boolean checkVertexExistence(Vertex vertex) {
        return VERTICES.containsKey(vertex.getName());
    }

    /**
     * This method will add the provided vertex into the main.graph.
     * the ArrayList which contains vertices it points to will
     * be set as an empty ArrayList
     *
     * @param vertex A vertex to be added into the main.graph.
     */
    public void addVertex(Vertex vertex) {
        String name = vertex.getName();
        if (!VERTICES.containsKey(name)) {
            VertexArray newEdge = new VertexArray(vertex);
            VERTICES.put(name, newEdge);
        }
    }

    /**
     * Delete the given edge from the main.graph. Though both
     * starting and ending vertices will remain in the main. graph,
     * the connection between them will be removed.
     *
     * @param edge An array of length 2 which represents a directed edge
     *             containing the starting vertex at index 0 and ending vertex at index 1.
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     *                   or if the directed edge to be deleted does not exist.
     */
    public void deleteEdge(Vertex[] edge) throws Exception {
        getVertexArray(edge[0]).deleteThisEdge(edge[1]);
        update(getVertexArray(edge[1]), true);
        if (edge[1].getInLevel() == 0) {
            CURRENTUNLOCK.add(edge[1].getName());
        }
    }

    /**
     * Update the inlevel of vertices in all DirectedEdges starting from vertex
     *
     * @param va a VertexArray whose DirectedEdges needs to be updated.
     * @param decrease true if minusInLevel, false if addInLevel
     */
    public void update(VertexArray va, boolean decrease) {
        if (decrease) {
            va.getStart().minusInLevel();
            if (!va.isEmpty()) {
                for (Vertex end : va) {
                    try {
                        update(getVertexArray(end), true);
                    } catch (Exception ignored) {}
                }
            }
        } else {
            va.getStart().addInLevel();
            if (!va.isEmpty()) {
                for (Vertex end : va) {
                    try {
                        update(getVertexArray(end), false);
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    /**
     * Delete the given Vertex from the main.graph. All edges having
     * this vertex as the ending vertex will also be removed.
     *
     * @param name The name of vertex
     * @throws Exception if the name of vertex does not exist in the DirectedGraph
     */
    public void deleteVertex(String name) throws Exception {
        Vertex delete = getVertexArray(name).getStart();
        update(getVertexArray(name), true);
        for (Vertex v : getVertexArray(name)) {
            if (v.getInLevel() == 0) {
                CURRENTUNLOCK.add(v.getName());
            }
        }
        for (String vertexName : VERTICES) {
            if (getVertexArray(vertexName).isEnd(delete)) {
                Vertex[] edge = {getVertexArray(vertexName).getStart(), delete};
                deleteEdge(edge);
            }
        }
        VERTICES.remove(name);
        CURRENTUNLOCK.remove(name);
        COMPLETED.remove(name);
    }

    /**
     * An overloaded version of getVertexArray.
     * This implementation takes in the name of a vertex and return the VertexArray corresponding to this name.
     *
     * @param name the name of a vertex
     * @return a VertexArray that stores all DirectedEdges starting from this vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public VertexArray getVertexArray(String name) throws Exception {
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.VERTEX_NOT_FOUND);
        } else {
            return VERTICES.get(name);
        }
    }

    /**
     * An overloaded version of getVertexArray.
     * This implementation takes in a vertex and return the VertexArray corresponding to this vertex.
     *
     * @param vertex a vertex
     * @return a VertexArray that stores all DirectedEdges starting from this vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public VertexArray getVertexArray(Vertex vertex) throws Exception {
        String name = vertex.getName();
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.VERTEX_NOT_FOUND);
        } else {
            return VERTICES.get(name);
        }
    }

    /**
     * Given a name, return vertex
     *
     * @param name The name of Vertex
     * @return Return the vertex with name
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Vertex getVertex(String name) throws Exception {
        for (String vertexName : VERTICES) {
            if (vertexName.equals(name)) {
                return getVertexArray(name).getStart();
            }
        }
        throw new Exception(Exceptions.VERTEX_NOT_FOUND);
    }

    /**
     * To mark the complete for the vertex.
     *
     * @param name: The name for vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     *                   or if the vertex is currently locked or if the vertex is already completed.
     */
    public void complete(String name) throws Exception {
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.VERTEX_NOT_FOUND);
        } else if (COMPLETED.contains(name)) {
            throw new Exception(Exceptions.VERTEX_ALREADY_COMPLETED);
        } else if (!CURRENTUNLOCK.contains(name))
        {
        throw new Exception(Exceptions.VERTEX_LOCKED);
         } else {
            COMPLETED.add(name);
            CURRENTUNLOCK.remove(name);
            VertexArray next = getVertexArray(name);
            update(next, true);
            for (Vertex vertex : next) {
                if (vertex.getInLevel() == 0) {
                    CURRENTUNLOCK.add(vertex.getName());
                }
            }
        }
    }

    /**
     * Override ToString method
     *
     * @return the string representation of the TechTree(main.graph)
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name of TechTree: ");
        stringBuilder.append(NAME);
        stringBuilder.append("\n");
        for (VertexArray edges : arrangeArray()) {
            if (edges.getStart().getInLevel() == 0) {
                stringBuilder.append("\n");
                stringBuilder.append(singleVertexToString(edges, 1));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Helper method for DirectedGraph.toString. Specifying the String representation of the DirectedGraph starting
     * from this edge.
     *
     * @param edge a starting vertex which String representation is required
     * @param numInward the number of indentations preceding this String representation
     * @return the String representation of the DirectedGraph starting from this edge.
     */
    public String singleVertexToString(VertexArray edge, int numInward) {
        if (edge.isEmpty()) {
            return edge.getStart().toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(edge.getStart().toString());
            for (Vertex vertex : edge) {
                try {
                    VertexArray vertexArray = getVertexArray(vertex);
                    stringBuilder.append("\n");
                    stringBuilder.append("    ".repeat(numInward));
                    stringBuilder.append("-> ");
                    stringBuilder.append(singleVertexToString(vertexArray, numInward + 1));
                } catch (Exception ignored) {}
            }
            return stringBuilder.toString();
        }
    }

    /**
     * Return the vertex available now.
     *
     * @return Vertices that available now.
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Map<String, Vertex> availableVertex() throws Exception {
        Map<String, Vertex> available = new HashMap<>();
        int count = 0;
        for (String name : CURRENTUNLOCK) {
            available.put(Integer.toString(count), getVertexArray(name).getStart());
            count++;
        }
        return available;
    }

    /**
     * Return all vertices in the TechTree
     * Only intended to be used for testing.
     * It should not be used for any other purpose.
     *
     * @return VERTICES
     */
    public Map<String, VertexArray> getVertices() {
        return VERTICES;
    }

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Helper method to be used by the GraphItr class.
     *
     * @return a sorted list of VertexArray which is then going to be iterated.
     */
    private List<VertexArray> arrangeArray() {
        List<VertexArray> vertexArray = new ArrayList<>();
        for (String vertexName : VERTICES) {
            try {
                vertexArray.add(getVertexArray(vertexName));
            } catch (Exception ignored) {}
        }
        if (vertexArray.isEmpty()) {
            return vertexArray;
        }
        int b = 0;
        int e = vertexArray.size() - 1;
        VertexArrayDefaultComparator vertexArrayDefaultComparator = new VertexArrayDefaultComparator();
        mergeSort(vertexArray, b, e, vertexArrayDefaultComparator);
        return vertexArray;
    }

    /**
     * Check if the completed set is zero, in other word, this
     * method is used to check whether the tree/graph was
     * began to learn
     *
     * @return if the graph has begun learning
     */
    public boolean isLearnedGraph() {
        int number = COMPLETED.size();
        return number != 0;
    }
}
