package interviewpreparation.taskscheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadScheduler {
    private final int numThreads;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    public SimpleThreadScheduler(int numThreads) throws InterruptedException {
        this.numThreads = numThreads;
        this.threads = new Thread[numThreads];
        this.taskQueue = new LinkedBlockingQueue<>();

        // Start worker threads
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
            //threads[i].join();
        }
    }

    public void scheduleTask(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take(); // Blocking call to retrieve task
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break; // Terminate thread upon interruption
                }
            }
        }
    }

    public void shutdown() {
        // Interrupt all worker threads to stop them
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    // Example usage
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadScheduler scheduler = new SimpleThreadScheduler(5); // Create scheduler with 5 worker threads

        // Schedule tasks
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            scheduler.scheduleTask(() -> System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName()));
        }

        //Thread.sleep(100);
        // Shutdown the scheduler
        scheduler.shutdown();
    }
}
