package comparator;

import java.util.Comparator;

public class CompareUsersByAgeDescendingComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o2.getAge().compareTo(o1.getAge());
    }
}
