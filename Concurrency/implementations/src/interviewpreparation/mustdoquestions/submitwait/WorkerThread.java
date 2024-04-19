package interviewpreparation.mustdoquestions.submitwait;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{

    private BlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped = false;
    private boolean threadCompletedTask = false;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.taskQueue = queue;
    }
    @Override
    public void run() {
        while (!isStopped) {
            try {
                if(!taskQueue.isEmpty()){
                    threadCompletedTask = false;
                    Thread.sleep(1000);
                    Runnable runnable = taskQueue.take(); // Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                    runnable.run();
                    threadCompletedTask = true;
                }

            } catch (InterruptedException e) {
                // Re-interrupt the thread and handle interruption
                Thread.currentThread().interrupt();
            }
        }
    }


    public synchronized void stopThread() {
        isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }

    public boolean isThreadCompletedTask() {
        return threadCompletedTask;
    }
}
