package constants;

import java.util.ArrayList;

public class Algorithm {
    public static int recBinaryInsertIndex(ArrayList lst, Comparable comparable, int b, int e) {
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
}
