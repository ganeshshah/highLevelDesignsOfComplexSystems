package interviewpreparation.educativequizzes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalExample {

    public static void usingThreads() throws Exception {
        Counter counter = new Counter();
        Thread[] tasks = new Thread[100];
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 100; j++)
                    counter.increment();
            });
            tasks[i] = t;
            t.start();
        }
        for (int i = 0; i < 100; i++) {
            tasks[i].join();
        }

        // What is the output of the below line?
        System.out.println(counter.counter.get());
    }

    public static void main(String[] args) throws Exception {
        usingThreads();
        usingSingleThreadPool();
    }

    public static void usingSingleThreadPool() throws Exception {
        Counter counter = new Counter();
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer>[] tasks = new Future[100];
        for (int i = 0; i < 100; i++) {
            tasks[i] = es.submit(() -> {
                for (int j = 0; j < 100; j++)
                    counter.increment();
                return counter.counter.get();
            });
        }
// What is the output of the below line?
        System.out.println(tasks[99].get());
        es.shutdown();
    }

}

