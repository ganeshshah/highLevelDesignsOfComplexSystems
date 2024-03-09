package comparator;

import java.util.Comparator;
import java.util.List;

public class ComparatorExample {

    public static void main(String[] args) {

        User user1 = new User("ganesh",27);
        User user2 = new User("digambar",26);
        User user3 = new User("sumit",47);
        User user4 = new User("pramod",27);
        User user5 = new User("mrityunjoy",32);

        List<User> listOfUsers = List.of(user1,user2,user3,user4,user5);

        // comparable
        listOfUsers.stream().sorted().forEach(v -> System.out.println(v.getName()));

        Comparator<User> compareByAgeUsingCompareInt = Comparator.comparing(User::getAge);
        Comparator<User> compareByAgeUsingLambdas = (User user01, User user02) -> Integer.compare(user01.getAge(), user02.getAge());

        System.out.println("Sorted using compareByAgeUsingCompareInt : ");
        listOfUsers.stream().sorted(compareByAgeUsingCompareInt).forEach(v -> System.out.println(v.getName()));
        System.out.println("Sorted using compareByAgeUsingLambdas : ");
        listOfUsers.stream().sorted(compareByAgeUsingLambdas).forEach(v -> System.out.println(v.getName()));

        System.out.println("Sorted using lambdas inside sorted() ascending age : ");
        listOfUsers.stream().sorted((v1,v2) -> v1.getAge() - v2.getAge()).forEach(v -> System.out.println(v.getName()));
        System.out.println("Sorted using lambdas inside sorted() descending age : ");
        listOfUsers.stream().sorted((v1,v2) -> v2.getAge() - v1.getAge()).forEach(v -> System.out.println(v.getName()));

        System.out.println("Sorted using lambdas inside sorted() ascending name : ");
        listOfUsers.stream().sorted((v1,v2) -> v1.getName().compareTo(v2.getName())).forEach(v -> System.out.println(v.getName()));
        System.out.println("Sorted using lambdas inside sorted() descending name : ");
        listOfUsers.stream().sorted((v1,v2) -> v2.getName().compareTo(v1.getName())).forEach(v -> System.out.println(v.getName()));

        System.out.println("Sorted using CompareUsersByAgeDescendingComparator : ");
        listOfUsers.stream().sorted(new CompareUsersByAgeDescendingComparator()).forEach(v -> System.out.println(v.getName()));

        // if we implement comparable then  only one type of sorting is allowed because compareTo method can be overridden only once
        // if we use comparator we can define any number of sorting type
    }


}
