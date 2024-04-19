package interviewpreparation.mustdoquestions.submitwait;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPoolExecutor implements CustomExecutorService {

    private volatile AtomicInteger totalWorkerThreadSoFar = new AtomicInteger(0);
    private volatile int poolSize;
    private volatile AtomicBoolean needToWait = new AtomicBoolean(false);

    private BlockingQueue<Runnable> taskQueue;
    private BlockingQueue<Runnable> taskBacklogQueue;
    private List<WorkerThread> threads;
    public CustomThreadPoolExecutor(int fixedThreadPoolSizeRequired) {
        this.poolSize = fixedThreadPoolSizeRequired;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>(fixedThreadPoolSizeRequired);
        this.taskBacklogQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void submit(Runnable r) throws InterruptedException {
        //System.out.println("Hey, submit called...");
        while (needToWait.get()){
            Thread.sleep(100);
        }
        taskQueue.put(r);
        if(totalWorkerThreadSoFar.get() < poolSize){
            totalWorkerThreadSoFar.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void waitUntilComplete() throws InterruptedException {
        needToWait.set(true);
        while(!taskQueue.isEmpty()){
            Thread.sleep(100);
        }

        int count = 0;
        for(WorkerThread thread : threads){
            while(!thread.isThreadCompletedTask()){

            }
        }
//
//        taskBacklogQueue.drainTo(taskQueue);
//        //taskQueue.addAll(taskBacklogQueue);
        System.out.println("Waiting completed....");
        needToWait.set(false);
    }

    @Override
    public void shutDown() {
        for (WorkerThread worker : threads) {
            worker.stopThread();
            //worker.interrupt();
        }
    }
}
