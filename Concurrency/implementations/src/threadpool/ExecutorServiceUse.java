package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUse {


    public static void main(String[] args) {

        // Async, Not good for scaling during heavy concurrency workloads
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);

        for(int i=0;i<25;i++){
            fixedExecutorService.submit(() -> System.out.println("I am a task executed by fixedThreadPool thread : " + Thread.currentThread().getName()));
        }


        fixedExecutorService.shutdown();

        // Async, Good for scaling during heavy concurrency workloads as no. of threads increases dynamically. Best for handling burst of short-lived tasks
        ExecutorService cachedExecutorService = Executors.newCachedThreadPool();

        for(int i=0;i<25;i++){
            cachedExecutorService.submit(() -> System.out.println("I am a task executed by cachedThreadPool thread : " + Thread.currentThread().getName()));
        }

        cachedExecutorService.shutdown();


        // Good for synchronous task with single threads
        ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();

        for(int i=0;i<25;i++){
            singleThreadExecutorService.submit(() -> System.out.println("I am a task executed by singleThreadExecutor thread : " + Thread.currentThread().getName()));
        }

        singleThreadExecutorService.shutdown();


    }



}
