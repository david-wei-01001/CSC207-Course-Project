package comparator;

import comparator.InLevelComparator;
import graph.VertexArray;

import java.util.Comparator;

import static algorithm.Algorithm.pairwiseCompare;

public class VertexArrayDefaultComparator implements Comparator<VertexArray> {

    /**
     * comparing two VertexArray by pairwiseCompare
     *
     * @param o1 the first VertexArray to be compared
     * @param o2 the second VertexArray to be compared
     * @return returning hte result of the comparison
     */
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
