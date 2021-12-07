package graph;

import constants.Exceptions;
import constants.InLevelComparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static constants.Algorithm.*;

/**
 * A special Array which stores a vertex and all DirectedEdges started from a vertex.
 */
public class VertexArray implements Iterable<Vertex>, Serializable {

    private final Vertex start;

    /**
     * END is always sorted.
     */
    private final List<Vertex> END = new ArrayList<>();

    /**
     * the constructor of a VertexArray.
     *
     * @param vert a vertex to be stored
     */
    public VertexArray(Vertex vert) {
        start = vert;
    }

    /**
     * add a DirectedEdge starting from the start to the VertexArray.
     *
     * @param end the ending vertex of this VertexArray
     */
    public void addEdge(Vertex end) {
        int i = indexToInsert(end);
        if (i == END.size()) {
            END.add(end);
        } else {
            END.add(i, end);
        }
    }

    /**
     * Update the inLevel of the vertices in this DirectedEdge
     *
     * @param vertex a vertex specifying a DirectedEdge starting from START and ending with vertex
     * @throws Exception if the DirectedEdge starting from START and ending with vertex does not exist
     */
    public void updateVertex(Vertex vertex) throws Exception {
        deleteThisEdge(vertex);
        addEdge(vertex);
    }

    /**
     * Find at what index should the given vertex to be inserted in the END
     *
     * @param vertex a vertex to be inserted in the END
     * @return the index where it needs to be inserted in order for END to stay sorted
     */
    public int indexToInsert(Vertex vertex) {
        if (END.isEmpty()) {
            return 0;
        }
        int b = 0;
        int e = END.size() - 1;
        InLevelComparator inLevelComparator = new InLevelComparator();
        return recBinaryInsertIndex(END, vertex, inLevelComparator, b, e);
    }

    /**
     * Only intended to be used for testing purpose.
     * Check whether the END instance variable is exactly the same as other
     *
     * @param other a predefined list to be compared with END
     * @return whtether END is exactly the same as other
     */
    public boolean endEqual(ArrayList<Vertex> other) {
        InLevelComparator inLevelComparator = new InLevelComparator();
        return pairwiseCompare(END, other, inLevelComparator) == 0;
    }

    /**
     * @return the instance variable start
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Delete a DirectedEdge.
     *
     *  @param end the ending vertex of the DirectedEdge to be deleted
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
     *
     * @param end a vertex to be checked
     * @return True if end is reachable from start, False otherwise
     */
    public boolean isEnd(Vertex end) {
        return END.contains(end);
    }

    /**
     * Apply Iterator design pattern to VertexArray.
     *
     * @return an iterator class representation of VertexArray
     */
    @Override
    public Iterator<Vertex> iterator() {
        return END.iterator();
    }

    /**
     * @return whether END is empty
     */
    public boolean isEmpty(){
        return END.isEmpty();
    }

    /**
     * Helper method of toString.
     * For each ending vertices that start can reach,
     * return its name preceded by an arrow and followed by a newline character.
     *
     * @return the name of each ending vertices preceded
     *         by an arrow and followed by a newline character
     */
    public String arrowEnd() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Vertex vertex : END) {
            stringBuilder.append("        ->    ");
            stringBuilder.append(vertex.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * @return the string representation of the VertexArray
     */
    @Override
    public String toString() {
        return start.toString() + "\n" + arrowEnd();
    }
}
