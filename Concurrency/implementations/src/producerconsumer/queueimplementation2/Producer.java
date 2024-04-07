package producerconsumer.queueimplementation2;


import java.util.Queue;

public class Producer implements Runnable{

    private Queue<Long> taskQueue = null;

    private boolean isStopped = false;
    private int bufferSize ;

    public Producer(Queue<Long> taskQueue,int bufferSize){
        this.taskQueue = taskQueue;
        this.bufferSize = bufferSize;
    }


    @Override
    public void run() {
        while(!isStopped && !Thread.currentThread().isInterrupted()){
            synchronized (taskQueue){
                if(taskQueue.size() < bufferSize){
                    Long currenTime = System.currentTimeMillis();
                    try {
                        taskQueue.add(currenTime);
                        System.out.println("Produced item " + currenTime + "by thread : " + Thread.currentThread().getName());
                        Thread.sleep(100);
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
