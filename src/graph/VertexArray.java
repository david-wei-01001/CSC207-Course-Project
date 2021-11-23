package graph;

import constants.Exceptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A special Array which stores a vertex and all DirectedEdges started from a vertex.
 */
public class VertexArray implements Iterable<Vertex>{

    private final Vertex start;
    private final ArrayList<Vertex> END = new ArrayList<>();

    /**
     * the constructor of a VertexArray.
     * @param vert a vertex to be stored
     */
    public VertexArray(Vertex vert) {
        start = vert;
    }

    /**
     * add a DirectedEdge starting from the start to the VertexArray.
     * @param end the ending vertex of this VertexArray
     */
    public void addEdge(Vertex end) {
        END.add(end);
    }

    public int recBinaryInsertIndex {

    }

    /**
     * @return all vertices rechable from start
     */
    public ArrayList<Vertex> getEnds() {
        return END;
    }

    /**
     * @return the instance variable start
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Delete a DirectedEdge.
     * @param end the ending vertex of the DirectedEdge to be deleted
     * @throws Exception if the DirectedEdge to be deleted does not exist
     */
    public void deleteThisEdge(Vertex end) throws Exception {
        if (isEnd(end)) {
            END.remove(end);
        } else {
            throw new Exception(Exceptions.EDGE_NOT_FOUND);
        }

    }

    /**
     * Check whether a vertex is reachable from start.
     * @param end a vertex to be checked
     * @return True if end is reachable from start, False otherwise
     */
    public boolean isEnd(Vertex end) {
        return END.contains(end);
    }

    /**
     * Apply Iterator design pattern to VertexArray.
     * @return an iterator class representation of VertexArray
     */
    @Override
    public Iterator<Vertex> iterator() {
        return new VertexItr();
    }

    private class VertexItr implements Iterator<Vertex> {

        private int curr;

        @Override
        public boolean hasNext() {
            return curr < END.size();
        }

        @Override
        public Vertex next() {
            Vertex toReturn;
            try{
                toReturn = END.get(curr);
            } catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }
            curr += 1;
            return toReturn;
        }
    }

    public String arrowEnd() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Vertex vertex : END) {
            stringBuilder.append("->    ");
            stringBuilder.append(vertex.getName());
            stringBuilder.append("\n");
        }
        String toReturn = stringBuilder.toString();
        return toReturn;
    }


    @Override
    public String toString() {
        return  start.toString() + "    " + arrowEnd();
    }
}
