package constants;

import java.util.Comparator;

public class NameComparator implements Comparator<HasName> {


    /**
     * comparing two HasName by name
     *
     * @param o1 the first HasName to be compared
     * @param o2 the second HasName to be compared
     * @return returning hte result of the comparison
     */
    @Override
    public int compare(HasName o1, HasName o2) {
        return o1.getName().compareTo(o2.getName());
    }
}