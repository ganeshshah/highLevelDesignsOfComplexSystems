package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;

import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread {

    BlockingQueue<WorkStealingTask> mainQueue;
    BlockingDeque<WorkStealingTask> workerDeque;
    ArrayList<BlockingDeque<WorkStealingTask>> workerThreadsQueueList;

    private boolean isStopped = false;

    public WorkerThread(BlockingQueue<WorkStealingTask> mainQueue, BlockingDeque<WorkStealingTask> workerDeque,
                        ArrayList<BlockingDeque<WorkStealingTask>> workerThreadsQueueList) {
        this.mainQueue = mainQueue;
        this.workerDeque = workerDeque;
        this.workerThreadsQueueList = workerThreadsQueueList;
    }

    @Override
    public void run() {

        while(!isStopped){

            if(workerDeque.size() > 0){
                WorkStealingTask task = null;
                try {
                    task = workerDeque.take();
                    System.out.printf(" Task executed in workerQueue :" );
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }else if(mainQueue.size() > 0){
                WorkStealingTask task = null;
                try {
                    task = mainQueue.take();
                    System.out.printf(" Task executed in mainQueue :" );
                    task.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                for(int i=0; i<workerThreadsQueueList.size();i++){
                    if(workerThreadsQueueList.get(i).size() > 0 && !workerThreadsQueueList.get(i).equals(this.workerDeque)){
                        WorkStealingTask task = null;
                        try {
                            task = workerThreadsQueueList.get(i).takeLast();
                            System.out.printf(" Task executed after stealing work :" );
                            task.run();
                            break;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

    }
}
