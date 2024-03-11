package threadpool;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureExample {

    // callable has return type, runnable does not have return type

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor poolExec = new ThreadPoolExecutor(3,3,3000,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(20), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        Future<?> futureObject = poolExec.submit(() -> {
            System.out.println("Task1 with Runnable");
        });

        Object object = futureObject.get();


        List<Integer> output = new ArrayList<>();
        Future<List<Integer>> futureObjectList = poolExec.submit(() -> {
            output.add(100);
            System.out.println("Task2 with Runnable and Return Object");
        },output);


        List<Integer> futureObjectListOutput = futureObjectList.get();


        futureObjectListOutput.stream().forEach(System.out::println);

        Future<List<Integer>> futureObjectList2 = poolExec.submit(() -> {
            System.out.println("Task3 with Callable");
            List<Integer> list = new ArrayList<>();
            list.add(300);
            list.add(500);
            return list;
        });


        futureObjectList2.get().stream().forEach(System.out::println);

        poolExec.shutdown();

    }

}
