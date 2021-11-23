package constants;

import java.util.Comparator;

public class NameComparator implements Comparator<Namable> {


    @Override
    public int compare(Namable o1, Namable o2) {
        return o1.getName().compareTo(o2.getName());
    }
}