package producerconsumer.usingqueueswaroop;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Producer implements Runnable{

    private Queue<Long> taskQueue = null;

    private boolean isStopped = false;

    private int bufferSize;
    ReentrantLock lock ;
    Condition isFull ;
    Condition isEmpty;

    public Producer(Queue<Long> taskQueue,int bufferSize, ReentrantLock lock,Condition isFull,Condition isEmpty  ){
        this.taskQueue = taskQueue;
        this.bufferSize = bufferSize;
        this.lock = lock;
        this.isFull = isFull;
        this.isEmpty = isEmpty;
    }


    @Override
    public void run() {
        while(!isStopped && !Thread.currentThread().isInterrupted()){
            Long currenTime = System.currentTimeMillis();
            lock.lock();
                try {
                    if(taskQueue.size() <= bufferSize){
                        taskQueue.add(currenTime);
                        Thread.sleep(100);
                        System.out.println("Produced item " + currenTime + "by thread : " + Thread.currentThread().getName());
                        isEmpty.signal(); //notify
                    } else {
                        isFull.await();
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
