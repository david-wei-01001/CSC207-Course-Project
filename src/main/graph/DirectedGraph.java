package graph;

import constants.Exceptions;
import constants.HasName;
import constants.IterableMap;
import constants.VertexArrayDefaultComparator;

import java.io.Serializable;
import java.util.*;

import static constants.Algorithm.*;

/**
 * A Directed Graph, which is the data structure used to represent a field of knowledge.
 */
public class DirectedGraph extends Observable implements Serializable, Iterable<VertexArray>, HasName {

    /**
     * The key of VERTICES is a String which is the name of a Vertex, the value of the VERTICES is an Array of length
     * 2 where the first element is a Vertex which is the starting vertex of many edges and the second element is an
     * ArrayList containing all Vertices that is the ending vertex which the starting vertex points to.
     */
    private final IterableMap<String, VertexArray> VERTICES = new IterableMap<>();
    private final String NAME;
    private final List<String> CURRENTUNCLOCK = new ArrayList<>();

    private final List<String> COMPLETED = new ArrayList<>();

    public int getNumOfCOMPLETED() {
        return COMPLETED.size();
    }

    /**
     * The constructor of the DirectedGraph class.
     * @param lstVertex A list of vertex to be added to the instance of DirectedGraph
     * @param name The name of the a DirectedGraph
     */
    public DirectedGraph(Vertex[] lstVertex, String name) {
        for(Vertex v: lstVertex){

            addVertex(v);
            CURRENTUNCLOCK.add(v.getName());
        }
        this.NAME = name;
    }

    /**
     * This method will add the provided directed edge into the main.graph.
     * the starting vertex and ending vertex will be added into the main.graph,
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
            edge[1].addInLevel();
            updateAll(edge[1]);
            getVertexArray(edge[0]).addEdge(edge[1]);
        } else {
            throw new Exception(Exceptions.EDGE_ALREADY_EXIST);
        }
    }

    public boolean checkEdgeExistence(Vertex[] edge) throws Exception {
        if (checkVertexExistence(edge[0])) {
            if (getVertexArray(edge[0]).isEnd(edge[1])){
                return true;
            }
        }
        if (checkVertexExistence(edge[1])) {
            return getVertexArray(edge[1]).isEnd(edge[0]);
        }
        return false;
    }

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
     * starting and ending vertices will remain in the main.graph,
     * the connection between them will be removed.
     *
     * @param edge An array of length 2 which represents a directed edge
     *             containing the starting vertex at index 0 and ending vertex at index 1.
     * @throws Exception if the name of the first vertex in edge does not exist in the DirectedGraph
     * or if the directed edge to be deleted does not exist.
     */
    public void deleteEdge(Vertex[] edge) throws Exception {
        getVertexArray(edge[0]).deleteThisEdge(edge[1]);
        edge[1].minusInLevel();
        updateAll(edge[1]);
        if(edge[1].getInLevel() == 0){
            CURRENTUNCLOCK.add(edge[1].getName());
        }
    }

    public void updateAll(Vertex vertex) throws Exception {
        for (String name: VERTICES) {
            if (getVertexArray(name).isEnd(vertex)) {
                getVertexArray(name).updateVertex(vertex);
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
        for (Vertex v: getVertexArray(name)){
            v.minusInLevel();
            updateAll(v);
            if (v.getInLevel() == 0){
                CURRENTUNCLOCK.add(v.getName());
            }
        }
        for (String vertexName : VERTICES) {
            if (getVertexArray(vertexName).isEnd(delete)) {
                Vertex[] edge = {getVertexArray(vertexName).getStart(), delete};
                deleteEdge(edge);
            }
        }
        VERTICES.remove(name);
        CURRENTUNCLOCK.remove(name);
        COMPLETED.remove(name);
    }

    /**
     * An overloaded version of getVertexArray.
     * This implementation takes in the name of a vertex and return the VertexArray corresponding to this name.
     * @param name the name of a vertex
     * @return a VertexArray that stores all DirectedEdges starting from this vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public VertexArray getVertexArray(String name) throws Exception {
        if (!VERTICES.containsKey(name)) {
            throw new Exception(Exceptions.Vertex_NOT_FOUND);
        }
        else {
            return VERTICES.get(name);
        }
    }

    /**
     * An overloaded version of getVertexArray.
     * This implementation takes in a vertex and return the VertexArray corresponding to this vertex.
     * @param vertex a vertex
     * @return a VertexArray that stores all DirectedEdges starting from this vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
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
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Vertex getVertex(String name) throws Exception {
        for (String vertexName : VERTICES){
            if (vertexName.equals(name)) {
                return  getVertexArray(name).getStart();
            }
        }
        throw new Exception(Exceptions.Vertex_NOT_FOUND);
    }

    /**
     * To mark the complete for the vertex.
     * @param name: The name for vertex
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     * or if the vertex is currently locked or if the vertex is already completed.
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
            for (Vertex next : getVertexArray(name)) {
                next.minusInLevel();
                updateAll(next);
                if(next.getInLevel() == 0){
                    CURRENTUNCLOCK.add(next.getName());}
            }
        }
    }

    /**
     * Override ToString method
     * @return the string representation of the TechTree(main.graph)
     */
    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name of TechTree: ");
        stringBuilder.append(NAME);
        stringBuilder.append("\n");
        for (VertexArray edges : arrangeArray()) {
            stringBuilder.append("    ");
            stringBuilder.append(edges.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Return the vertex availiable now.
     * @return Vertices that availiable now.
     * @throws Exception if the name of the vertex does not exist in the DirectedGraph
     */
    public Map<String, Vertex> availableVertex() throws Exception {
        Map<String, Vertex> available = new HashMap<>();
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
    public Map<String, VertexArray> getVertices(){
        return VERTICES;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Iterator<VertexArray> iterator() {
        return new GraphItr();
    }

    private List<VertexArray> arrangeArray() {
        List<VertexArray> vertexArray = new ArrayList<>();
        for (String vertexName : VERTICES) {
            try{
                vertexArray.add(getVertexArray(vertexName));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

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

    private class GraphItr implements Iterator<VertexArray> {

        private final List<VertexArray> arranged;
        private int index;

        public GraphItr() {
            arranged = arrangeArray();
        }

        @Override
        public boolean hasNext() {
            return index < arranged.size();
        }

        @Override
        public VertexArray next() {
            VertexArray toReturn = arranged.get(index);
            index ++;
            return toReturn;
        }
    }

    /**
     * Check if the completed set is zero, in other word, this
     * method is used to check whether the tree/graph was
     * began to learn
     */
    public boolean isLearnedGraph(){
        int number = COMPLETED.size();
        if (number == 0){
            return false;
        }
        else{
            return true;
        }
    }
}
