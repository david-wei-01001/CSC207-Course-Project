package Graph;

import constants.Exceptions;
import java.util.ArrayList;
import java.util.Iterator;

public class VertexArray implements Iterable<Vertex>{

    private final Vertex start;
    private final ArrayList<Vertex> END = new ArrayList<>();

    public VertexArray(Vertex vert) {
        start = vert;
    }

    public void addEdge(Vertex end) {
        END.add(end);
    }

    public ArrayList<Vertex> getEnds() {
        return END;
    }

    public Vertex getStart() {
        return start;
    }

    public void deleteEdge(Vertex end) throws Exception {
        if (isEnd(end)) {
            END.remove(end);
        } else {
            throw new Exception(Exceptions.EDGE_NOT_FOUND);
        }

    }

    public boolean isEnd(Vertex end) {
        return END.contains(end);
    }

    @Override
    public Iterator<Vertex> iterator() {
        return END.iterator();
    }
}
