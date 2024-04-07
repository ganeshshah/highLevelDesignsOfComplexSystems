package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

import java.util.concurrent.PriorityBlockingQueue;

public class WorkerThread extends Thread {

    private volatile boolean isStopped = false;
    private PriorityBlockingQueue<PriorityBasedTask> taskQueue;

    public WorkerThread(PriorityBlockingQueue<PriorityBasedTask> taskQueue){
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while(!isStopped){
            if(taskQueue.size() > 0){
                try {
                    PriorityBasedTask task = taskQueue.take();
                    task.run();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
