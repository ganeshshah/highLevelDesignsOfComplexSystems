package producerconsumer.usingblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue<Long> taskQueue = null;
    private boolean isStopped = false;
    public Consumer(BlockingQueue<Long> taskQueue){
        this.taskQueue = taskQueue;
    }


    @Override
    public void run() {
        while(taskQueue.size() > 0){
            try {
                Long task = taskQueue.take();
                System.out.println("Consumed item : " + task + " by thread : " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupt received, Consuming remaining items");
                isStopped = true;
                //Thread.currentThread().interrupt();
            }
        }
    }
}
