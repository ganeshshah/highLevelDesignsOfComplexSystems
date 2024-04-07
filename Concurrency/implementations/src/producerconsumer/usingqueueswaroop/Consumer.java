package producerconsumer.usingqueueswaroop;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Consumer implements Runnable {
    private Queue<Long> taskQueue = null;
    private boolean isStopped = false;
    private int bufferSize;

   //ReentrantLock lock = new ReentrantLock();
    ReentrantLock lock ;
    Condition isFull ;
    Condition isEmpty ;

    public Consumer(Queue<Long> taskQueue, int bufferSize, ReentrantLock lock,Condition isFull,Condition isEmpty ) {
        this.taskQueue = taskQueue;
        this.bufferSize = bufferSize;
        this.lock = lock;
        this.isFull = isFull;
        this.isEmpty = isEmpty;
    }


    @Override
    public void run() {
        while (!isStopped && !Thread.currentThread().isInterrupted()) {

            lock.lock();
            try {
                if (!taskQueue.isEmpty()) {
                    Long task = taskQueue.poll();
                    Thread.sleep(1000);
                    System.out.println("Consumed item : " + task + " by thread : " + Thread.currentThread().getName());
                    isFull.signal();
                } else {
                    isEmpty.await();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupt received, shutting down the thread");
                isStopped = true;
                Thread.currentThread().interrupt();
            }
            lock.unlock();
        }
    }
}
