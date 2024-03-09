import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<5000000;i++){
            list.add(i+10);
        }
        long end = System.currentTimeMillis();
        System.out.println("time taken to add to list:" + (end-begin));

        long begin1 = System.currentTimeMillis();
        for(int i=0;i<5000000;i++){
            //System.out.println(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("time taken to retrieve and print from list using procedural syntax:" + (end1-begin1));

        long begin2 = System.currentTimeMillis();
        list.parallelStream().forEach(System.out::println);
        long end2 = System.currentTimeMillis();
        System.out.println("time taken to retrieve and print from list using stream syntax:" + (end2-begin2));
    }

    //1371981900
    //1241605300
}