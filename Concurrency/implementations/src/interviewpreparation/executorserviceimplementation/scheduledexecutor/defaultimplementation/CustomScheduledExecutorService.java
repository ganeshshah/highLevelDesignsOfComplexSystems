package interviewpreparation.executorserviceimplementation.scheduledexecutor.defaultimplementation;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class CustomScheduledExecutorService {
    private final BlockingQueue<ScheduledTask> queue;
    private final Thread executorThread;

    public CustomScheduledExecutorService() {
        this.queue = new LinkedBlockingQueue<>();
        this.executorThread = new Thread(this::executeTasks);
        this.executorThread.start();
    }

    public void schedule(Runnable task, long delay, TimeUnit timeUnit) {
        long currentTime = System.currentTimeMillis();
        long executeTime = currentTime + timeUnit.toMillis(delay);
        ScheduledTask scheduledTask = new ScheduledTask(task, executeTime);
        queue.offer(scheduledTask);
    }

    private void executeTasks() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ScheduledTask task = queue.take();
                long currentTime = System.currentTimeMillis();
                long delay = task.getExecuteTime() - currentTime;
                if (delay > 0) {
                    queue.offer(task); // Put back the task into the queue
                    Thread.sleep(delay); // Sleep until the next task's execution time
                } else {
                    task.run(); // Execute the task immediately
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() {
        executorThread.interrupt();
    }

    private static class ScheduledTask implements Comparable<ScheduledTask> {
        private final Runnable task;
        private final long executeTime;

        public ScheduledTask(Runnable task, long executeTime) {
            this.task = task;
            this.executeTime = executeTime;
        }

        public void run() {
            task.run();
        }

        public long getExecuteTime() {
            return executeTime;
        }

        @Override
        public int compareTo(ScheduledTask other) {
            return Long.compare(this.executeTime, other.executeTime);
        }
    }

    public static void main(String[] args) {
        CustomScheduledExecutorService executor = new CustomScheduledExecutorService();

        // Example tasks to be executed
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


        // Shutdown executor after some time
        executor.schedule(() -> executor.shutdown(), 20, TimeUnit.SECONDS);
    }
}
