package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

/**
 * Algorithms class Including all algorithms required for sorting searching and comparing different data structures.
 */
public class Algorithm {

    /**
     * Return the index at where the given item need to be inserted without disrupting the sorted nature of given list.
     *
     * @param lst a sorted list that the given item will be inserted.
     * @param toInsert an element that is to be inserted into the list
     * @param comparator a comparator that specifies the comparing strategy
     * @param b the beginning index of searching
     * @param e the ending index of searching
     * @param <T> The type of objects stored in list
     * @return the index at where the element should be inserted.
     */
    public static <T> int recBinaryInsertIndex(List<T> lst, T toInsert, Comparator<T> comparator, int b, int e) {
        if (b == e) {
            if (comparator.compare(toInsert, lst.get(b)) < 0) {
                return b;
            } else {
                return e + 1;
            }
        } else {
            int m = (b + e) / 2;
            if (comparator.compare(toInsert, lst.get(m)) < 0) {
                return recBinaryInsertIndex(lst, toInsert, comparator, b, m);
            } else {
                return recBinaryInsertIndex(lst, toInsert, comparator, m + 1, e);
            }
        }
    }

    /**
     * Comparing two iterable items and return the result of the compare
     *
     * @param itr1 first iterable object to be compared
     * @param itr2 second iterable object to be compared
     * @param comparator a comparator that specifies the comparing strategy
     * @param <T> The type of objects stored in both iterable objects
     * @return the result of comparison: -1 for the first element less than the second element,
     *         0 for the first element equal to the second element,
     *         1 for the first element greater than the second element
     */
    public static <T> int pairwiseCompare(Iterable<T> itr1, Iterable<T> itr2, Comparator<T> comparator) {
        Iterator<T> iterator1 = itr1.iterator();
        Iterator<T> iterator2 = itr2.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            T next1 = iterator1.next();
            T next2 = iterator2.next();
            if (comparator.compare(next1, next2) != 0) {
                return comparator.compare(next1, next2);
            }
        }

        if (!(iterator1.hasNext() || iterator2.hasNext())) {
            return 0;
        } else if (iterator2.hasNext()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Sort the unsorted list in ascending order
     *
     * @param unsorted a list to be sorted
     * @param b the beginning index to be sorted
     * @param e the ending index to be sorted
     * @param comparator a comparator that specifies the comparing strategy
     * @param <T> The type of objects stored in the list
     */
    public static <T> void mergeSort(List<T> unsorted, int b, int e, Comparator<T> comparator) {
        if (b == e) {
            return;
        }
        int m = (b + e) / 2;
        mergeSort(unsorted, b, m, comparator);
        mergeSort(unsorted, m + 1, e, comparator);
        merge(unsorted, b, m, e, comparator);
    }

    /**
     * Helper method for mergesort, merging two half sorted list together in ascending order.
     *
     * @param unsorted a list that is half sorted
     * @param b the beginning index of the first sorted half
     * @param m the ending index of the first sorted half as well as
     *          the beginning index of the second sorted half
     * @param e the ending index of the second sorted half
     * @param comparator a comparator that specifies the comparing strategy
     * @param <T> The type of objects stored in the list
     */
    // Citation: the merge method was paraphrased form
    // https://www.withexample.com/merge-sort-using-arraylist-java-example/
    private static <T> void merge(List<T> unsorted, int b, int m, int e, Comparator<T> comparator) {
        List<T> merged = new ArrayList<>();
        int l = b;
        int r = m + 1;
        while(l <= m && r <= e) {
            if (comparator.compare(unsorted.get(l), unsorted.get(r)) <= 0) {
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
