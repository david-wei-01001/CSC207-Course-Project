package comparator;

import graph.Vertex;

import java.util.Comparator;

public class InLevelComparator implements Comparator<Vertex> {

    /**
     * comparing two vertex by inlevel
     *
     * @param o1 the first vertex to be compared
     * @param o2 the second vertex to be compared
     * @return returning hte result of the comparison
     */
    @Override
    public int compare(Vertex o1, Vertex o2) {
        if (o1.getInLevel() < o2.getInLevel()) {
            return -1;
        } else if (o1.getInLevel() > o2.getInLevel()) {
            return 1;
        } else {
            NameComparator nameComparator = new NameComparator();
            return nameComparator.compare(o1, o2);
        }
    }
}
