package main.constants;

import main.graph.VertexArray;

import java.util.Comparator;

import static main.constants.Algorithm.pairwiseCompare;

public class VertexArrayDefaultComparator implements Comparator<VertexArray> {
    @Override
    public int compare(VertexArray o1, VertexArray o2) {
        InLevelComparator inLevelComparator = new InLevelComparator();
        int startCompare = inLevelComparator.compare(o1.getStart(), o2.getStart());
        if (startCompare != 0) {
            return startCompare;
        } else {
            return pairwiseCompare(o1, o2, inLevelComparator);
        }
    }
}
