package Graph;

import constants.Exceptions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public class DirectedGraph implements Serializable {

    /**
     * The key of VERTICES is a String which is the name of a Vertex, the value of the VERTICES is an Array of length
     * 2 where the first element is a Vertex which is the starting vertex of many edges and the second element is an
     * ArrayList containing all Vertices that is the ending vertex which the starting vertex points to.
     */
    private final HashMap<String, VertexArray> VERTICES = new HashMap<>();
    private final String NAME;
    private final ArrayList<String> CURRENTUNCLOCK = new ArrayList<>();
    private final ArrayList<String> COMPLETED = new ArrayList<>();

    public DirectedGraph(Vertex[] firstVertex, String name) {
        for(Vertex v: firstVertex){
            addVertex(v);
            CURRENTUNCLOCK.add(v.getName());}
        this.NAME = name;
    }

    /**
     * This method will add the provided directed edge into the graph.
     * the starting vertex and ending vertex will be added into the graph,
     * and the ending vertex will be added into the ArrayList of the
     * starting vertex containing all vertices it points to.
     *
     * @param edge An array of length 2 which represents a directed edge
     *             containing the starting vertex at index 0 and ending vertex at index 1.
     */
    public void addEdge(Vertex[] edge) throws Exception {
        addVertex(edge[0]);
        getVertexArray(edge[0]).addEdge(edge[1]);
        addVertex(edge[1]);
        edge[1].addInLevel();
    }

    /**
     * This method will add the provided vertex into the graph.
     * the ArrayList which contains vertices it points to will
     * be set as an empty ArrayList
     *
     * @param vertex A vertex to be added into the graph.
     */
    public void addVertex(Vertex vertex) {
        String name = vertex.getName();
        if (!VERTICES.containsKey(name)) {
            VertexArray newEdge = new VertexArray(vertex);
            VERTICES.put(name, newEdge);
        }
    }

    /**
     * Delete the given edge from the graph. Though both
     * starting and ending vertices will remain in the graph,
     * the connection between them will be removed.
     *
     * @param edge An array of length 2 which represents a directed edge
     *             containing the starting vertex at index 0 and ending vertex at index 1.
     */
    public void deleteEdge(Vertex[] edge) throws Exception {
        getVertexArray(edge[0]).deleteEdge(edge[1]);
        edge[1].minusInLevel();
        if(edge[1].getInLevel() == 0){
            CURRENTUNCLOCK.add(edge[1].getName());
        }
    }

    /**
     * Delete the given Vertex from the graph. All edges having
     * this vertex as the ending vertex will also be removed.
     *
     * @param name The name of vertex
     */
    public void deleteVertex(String name) throws Exception {
        Vertex delete = getVertexArray(name).getStart();
        for (Vertex v: getVertexArray(name).getEnds()){
            v.minusInLevel();
            if (v.getInLevel() == 0){
                CURRENTUNCLOCK.add(v.getName());
            }
        }
        for (String vertexName : VERTICES.keySet()) {
            if (getVertexArray(vertexName).getEnds().contains(delete)) {
                Vertex[] edge = {getVertexArray(vertexName).getStart(), delete};
                deleteEdge(edge);
            }
        }
        VERTICES.remove(name);
        CURRENTUNCLOCK.remove(name);
        COMPLETED.remove(name);
    }

    public VertexArray getVertexArray(String name) throws Exception {
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.Vertex_NOT_FOUND);
        }
        else {
            return VERTICES.get(name);
        }
    }

    public VertexArray getVertexArray(Vertex vertex) throws Exception {
        String name = vertex.getName();
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.Vertex_NOT_FOUND);
        }
        else {
            return VERTICES.get(name);
        }
    }

    /**
     * Given a name, return vertex
     * @param name The name of Vertex
     * @return Return the vertex with name
     */
    public Vertex getVertex(String name) throws Exception {
        for (String vertexName : VERTICES.keySet()){
            if (vertexName.equals(name)) {
                return  getVertexArray(name).getStart();
            }
        }
        throw new Exception(Exceptions.Vertex_NOT_FOUND);
    }

    /**
     * To mark the complete for the vertex.
     * @param name: The name for vertex
     */
    public void complete(String name) throws Exception {
        if (!VERTICES.containsKey(name)){
            throw new Exception(Exceptions.Vertex_NOT_FOUND);
        }else if (!CURRENTUNCLOCK.contains(name)){
            throw new Exception(Exceptions.Vertex_LOCKED);
        } else if (COMPLETED.contains(name)) {
            throw new Exception(Exceptions.Vertex_ALREADY_COMPLETED);
        } else {
            COMPLETED.add(name);
            CURRENTUNCLOCK.remove(name);
            for (Vertex next : getVertexArray(name).getEnds()) {
                next.minusInLevel();
                if(next.getInLevel() == 0){
                    CURRENTUNCLOCK.add(next.getName());}
            }
        }

    }

    /**
     * Override ToString method
     * @return the name of the TechTree(graph)
     */
    @Override
    public String toString() {
        return NAME;
    }

    /**
     * Return the vertex availiable now.
     * @return Vertices that availiable now.
     */
    public HashMap<String, Vertex> availableVertex() throws Exception {
        HashMap<String, Vertex> available = new HashMap<>();
        int count = 0;
        for (String name : CURRENTUNCLOCK) {
            available.put(Integer.toString(count), getVertexArray(name).getStart());
            count++;
        }
        return available;
    }

    /**
     * Return all vertices in the TechTree
     * Only intended to be used for testing.
     * It should not be used for any other purpose.
     * @return Vertices
     */
    public HashMap<String, VertexArray> getVertices(){
        return VERTICES;
    }

}
