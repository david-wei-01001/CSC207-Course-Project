/**
 * This file contains the implementation of DirectedGraph.
 */
package Graph;

import java.util.HashMap;
import java.util.ArrayList;

public class DirectedGraph {
    public static class VertexNotInGraphException extends Exception {
        public VertexNotInGraphException(String s) {
            super(s);
        }
    }

    /**
     * The key of VERTICES is a String which is the name of a Vertex, the value of the VERTICES is an Array of length
     * 2 where the first element is a Vertex which is the starting vertex of many edges and the second element is an
     * ArrayList containing all Vertices that is the ending vertex which the starting vertex points to.
     */
    private HashMap<String, Object[]> vertices = new HashMap<>();
    private String name;
    private ArrayList<String> currentUnlock = new ArrayList<>();
    private ArrayList<String> completed = new ArrayList<>();
    private String goal;
    //TODO: May be in an separate interface
    private static final VertexNotInGraphException ABSENT =
            new VertexNotInGraphException("Vertex is absent in the graph.");

    public DirectedGraph(Vertex[] firstVertex, String name) {
        for(Vertex v: firstVertex){
            addVertex(v);
            currentUnlock.add(v.getName());}
        this.name = name;
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
    public void addEdge(Vertex[] edge){
        addVertex(edge[0]);
        String name = edge[0].getName();
        ((ArrayList<Vertex>) vertices.get(name)[1]).add(edge[1]);
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
        if (!vertices.containsKey(name)) {
            ArrayList<Vertex> ends = new ArrayList<>();
            Object[] newEdge = {vertex, ends};
            vertices.put(name, newEdge);
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
    public void deleteEdge(Vertex[] edge) {
        String name = edge[0].getName();
        if (!vertices.containsKey(name)) {
            System.out.println("Vertex is absent in the graph.");
        }
        else {
            ArrayList<Vertex> lst = ((ArrayList<Vertex>) vertices.get(name)[1]);
            if (!lst.contains(edge[1])) {
                System.out.println("Vertex is absent in the graph, OR there is no relation between these two vertice");
            } else {
                lst.remove(edge[1]);
                edge[1].minusInLevel();
            }
        }
        if(edge[1].getInLevel() == 0){
            currentUnlock.add(edge[1].getName());
        }
    }

    /**
     * Delete the given Vertex from the graph. All edges having
     * this vertex as the ending vertex will also be removed.
     *
     * @param name The name of vertex
     */
    public void deleteVertex(String name) {
        Vertex delete = (Vertex) vertices.get(name)[0];
        for (Vertex v: (ArrayList<Vertex>) vertices.get(name)[1]){
            v.minusInLevel();
            if (v.getInLevel() == 0){
                currentUnlock.add(v.getName());
            }
        }
        for (String vertexName : vertices.keySet()){
            if (((ArrayList<Vertex>) vertices.get(vertexName)[1]).contains(delete)) {
                Vertex[] edge = {(Vertex) vertices.get(vertexName)[0], delete};
                deleteEdge(edge);
            }
        }
        vertices.remove(name);
        if(currentUnlock.contains(name)){
            currentUnlock.remove(name);
        }
        if(completed.contains(name)){
            completed.remove(name);
        }
    }

    /**
     * Given a name, return vertex
     * @param name The name of Vertex
     * @return Return the vertex with name
     * @throws VertexNotInGraphException
     */
    public Vertex getVertex(String name) throws VertexNotInGraphException{
        for (String vertexName : vertices.keySet()){
            if (vertexName.equals(name)) {
                return ((Vertex) vertices.get(name)[0]);
            }
        }
        throw ABSENT;
    }

    /**
     * To mark the complete for the vertex.
     * @param name: The name for vertex
     */
    public void complete(String name) {
        if (!vertices.containsKey(name)){
            System.out.println("Vertex is absent in the graph.");
        }else if (!currentUnlock.contains(name)){
            System.out.println("This vertex is locked.");
        } else if (completed.contains(name)) {
            System.out.println("This vertex is already completed.");
        } else {
            completed.add(name);
            currentUnlock.remove(name);
            for (Vertex next : ((ArrayList<Vertex>) vertices.get(name)[1])) {
                next.minusInLevel();
                if(next.getInLevel() == 0){
                    currentUnlock.add(next.getName());}
            }
        }

    }

    /**
     * Override ToString method
     * @return the name of the TechTree(graph)
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Return the vertex availiable now.
     * @return Vertices that availiable now.
     */
    public HashMap<String, Vertex> availableVertex(){
        HashMap<String, Vertex> available = new HashMap<>();
        int count = 0;
        for (String name : currentUnlock) {
            available.put(Integer.toString(count), (Vertex) (vertices.get(name)[0]));
            count++;
        }
        return available;
    }

    /**
     * Return all vertices in the TechTree
     * @return Vertices
     */
    public HashMap<String, Object[]> getVertices(){
        return vertices;
    }


}
