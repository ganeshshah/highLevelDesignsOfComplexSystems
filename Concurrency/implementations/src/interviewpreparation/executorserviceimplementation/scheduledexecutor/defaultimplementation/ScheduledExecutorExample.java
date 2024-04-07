package interviewpreparation.executorserviceimplementation.scheduledexecutor.defaultimplementation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {


    /**
     * executor.schedule
     * executor.scheduleAtFixedRate
     * executor.scheduleWithFixedDelay
     * executor.scheduleWithFixedDelay
     */
    public static void main(String[] args) {
        // Create a ScheduledExecutorService with a single thread
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Schedule a task to run after a delay of 2 seconds
        executor.schedule(() -> {
            System.out.println("Task 1 executed after 2 seconds" + Thread.currentThread().getName());
        }, 10, TimeUnit.SECONDS);
        executor.schedule(() -> {
            System.out.println("Task 2 executed after 3 seconds" +  Thread.currentThread().getName());
        }, 3, TimeUnit.SECONDS);
        executor.schedule(() -> {
            System.out.println("Task 3 executed after 4 seconds" + Thread.currentThread().getName());
        }, 4, TimeUnit.SECONDS);

        executor.schedule(() -> {
            System.out.println("Task 4 executed after 2 seconds" + Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);

        executor.schedule(() -> {
            System.out.println("Task 5 executed after 2 seconds" + Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);

        executor.schedule(() -> {
            System.out.println("Task 6 executed after 2 seconds" + Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);

//        // Schedule a task to run after an initial delay of 1 second and then repeatedly every 3 seconds
//        executor.scheduleAtFixedRate(() -> {
//            System.out.println("Task 2 executed repeatedly every 3 seconds");
//        }, 1, 3, TimeUnit.SECONDS);
//
//        // Schedule a task to run after an initial delay of 1 second and then repeatedly every 5 seconds, regardless of the execution time of the task
//        executor.scheduleWithFixedDelay(() -> {
//            System.out.println("Task 3 executed repeatedly every 5 seconds with a delay after each execution");
//        }, 1, 5, TimeUnit.SECONDS);
//
//        // Shutdown the executor after 10 seconds
//        executor.schedule(() -> {
//            executor.shutdown();
//            System.out.println("Executor shutdown");
//        }, 10, TimeUnit.SECONDS);
    }
}
