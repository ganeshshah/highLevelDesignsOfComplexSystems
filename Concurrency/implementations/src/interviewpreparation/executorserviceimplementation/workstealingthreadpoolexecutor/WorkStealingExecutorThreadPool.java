package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;



import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkStealingExecutorThreadPool implements WorkStealingExecutorService {

    BlockingQueue<WorkStealingTask> mainQueue = null;
    ArrayList<BlockingDeque<WorkStealingTask>> workerThreadsQueueList = null;

    private volatile AtomicInteger totalThreads = new AtomicInteger(0);

    ArrayList<WorkerThread> threads = new ArrayList<>();

    private int poolSize;


    public WorkStealingExecutorThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.mainQueue = new LinkedBlockingQueue<>();
        this.workerThreadsQueueList = new ArrayList<>();
    }

    @Override
    public void submit(Runnable r) throws InterruptedException {
        WorkStealingTask task = new WorkStealingTask(r);

        if(totalThreads.get() < poolSize){
            totalThreads.getAndIncrement();
            BlockingDeque<WorkStealingTask> workerDeque = new LinkedBlockingDeque<>(5);
            workerDeque.add(task);
            workerThreadsQueueList.add(workerDeque);
            WorkerThread thread = new WorkerThread(mainQueue,workerDeque,workerThreadsQueueList);
            threads.add(thread);
            Random random = new Random();
            thread.start();
        }else{
            Random random = new Random();
            boolean success = workerThreadsQueueList.get(random.nextInt(poolSize)).offer(task);
            if(!success){
                mainQueue.put(task);
            }
        }
    }
}
