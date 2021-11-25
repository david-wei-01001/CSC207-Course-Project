package constants;

import java.util.Comparator;

public class NameComparator implements Comparator<HasName> {


    @Override
    public int compare(HasName o1, HasName o2) {
        return o1.getName().compareTo(o2.getName());
    }
}