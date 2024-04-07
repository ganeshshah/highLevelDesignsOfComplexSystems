package producerconsumer.queueimplementation2;

import java.util.Queue;

public class Consumer implements Runnable{
    private Queue<Long> taskQueue = null;
    private boolean isStopped = false;
    private int bufferSize;
    public Consumer(Queue<Long> taskQueue, int bufferSize){
        this.taskQueue = taskQueue;
        this.bufferSize = bufferSize;
    }


    @Override
    public void run() {
        while(!isStopped && !Thread.currentThread().isInterrupted()){
           synchronized (taskQueue){
               if(!taskQueue.isEmpty()){
                   try {
                       Long task = taskQueue.poll();
                       System.out.println("Consumed item : " + task + " by thread : " + Thread.currentThread().getName());
                       Thread.sleep(1000);
                       taskQueue.notifyAll();
                   } catch (InterruptedException e) {
                       System.out.println("Interrupt received, shutting down the thread");
                       isStopped = true;
                       Thread.currentThread().interrupt();
                   }
               }else{
                   try {
                       taskQueue.wait();
                   } catch (InterruptedException e) {
                       System.out.println("Interrupt received, shutting down the thread");
                       isStopped = true;
                       Thread.currentThread().interrupt();
                   }
               }
           }
        }
    }
}
