package constants;

import graph.Vertex;
import graph.VertexArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Algorithm {
    public static <T> int recBinaryInsertIndex(List<T> lst, Comparable<T> comparable, int b, int e) {
        if (b == e) {
            if (comparable.compareTo(lst.get(b)) < 0) {
                return b;
            } else {
                return e + 1;
            }
        } else {
            int m = (b + e) / 2;
            if (comparable.compareTo(lst.get(m)) < 0) {
                return recBinaryInsertIndex(lst, comparable, b, m);
            } else {
                return recBinaryInsertIndex(lst, comparable, m + 1, e);
            }
        }
    }

    public static int pairwiseCompare(Iterator<Vertex> itr1, Iterator<Vertex> itr2) {
        while (itr1.hasNext() && itr2.hasNext()) {
            Vertex next1 = itr1.next();
            Vertex next2 = itr2.next();
            if (next1.compareTo(next2) != 0) {
                return next1.compareTo(next2);
            }
        }

        if (!(itr1.hasNext() || itr2.hasNext())) {
            return 0;
        } else if (itr2.hasNext()) {
            return -1;
        } else {
            return 1;
        }
    }

    public static void mergeSort(List<VertexArray> unsorted, int b, int e) {
        if (b == e) {
            return;
        }
        int m = (b + e) / 2;
        mergeSort(unsorted, b, m);
        mergeSort(unsorted, m + 1, e);
        merge(unsorted, b, m, e);
    }

    // Citation: the merge method was paraphrased form
    // https://www.withexample.com/merge-sort-using-arraylist-java-example/
    private static void merge(List<VertexArray> unsorted, int b, int m, int e) {
        List<VertexArray> merged = new ArrayList<>();
        int l = b;
        int r = m + 1;
        while(l <= m && r <= e) {
            if (unsorted.get(l).compareTo(unsorted.get(r)) <= 0) {
                merged.add(unsorted.get(l));
                l ++;
            } else {
                merged.add(unsorted.get(r));
                r ++;
            }
        }
        while (l <= m) {
            merged.add(unsorted.get(l));
            l ++;
        }
        while (r <= e) {
            merged.add(unsorted.get(r));
            r ++;
        }
        int i = 0;
        int j = b;
        while (i < merged.size()) {
            unsorted.set(j, merged.get(i));
            i ++;
            j ++;
        }
    }

}
