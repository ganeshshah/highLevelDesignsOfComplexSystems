package producerconsumer.usingblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private BlockingQueue<Long> taskQueue = null;

    private boolean isStopped = false;

    public Producer(BlockingQueue<Long> taskQueue){
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        while(!isStopped && !Thread.currentThread().isInterrupted()){
            Long currenTime = System.currentTimeMillis();
            try {
                taskQueue.put(currenTime);
                System.out.println("Produced item " + currenTime + "by thread : " + Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupt received, shutting down the thread");
                isStopped = true;
                Thread.currentThread().interrupt();
            }
        }
    }
}
