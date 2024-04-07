package interviewpreparation.threadsafearraylist;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeArrayListTest {


    public static void main(String[] args) {

        //ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();

        ExecutorService es = Executors.newFixedThreadPool(10);

        for(int i=0; i <1000; i++){
            int finalI = i;
            es.submit(()->{
                list.add(finalI);
            });
        }

        Iterator<Integer> iter = list.iterator();

        for(int i=0; i <1000; i++){
            int finalI1 = i;
            es.submit(()->{
                System.out.println(list.get(finalI1));
            });
        }

        for(int i=0; i <1000; i++){
            int finalI = i;
            es.submit(()->{
                list.add(finalI);
            });
        }


    }
}
