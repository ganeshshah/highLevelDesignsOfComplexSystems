package interviewpreparation.executorserviceimplementation.fixedthreadpool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPoolExecutor implements CustomExecutorService {

    private volatile AtomicInteger totalWorkerThreadSoFar = new AtomicInteger(0);
    private volatile int poolSize;

    private BlockingQueue<Runnable> taskQueue;
    private List<WorkerThread> threads;
    public CustomThreadPoolExecutor(int fixedThreadPoolSizeRequired) {
        this.poolSize = fixedThreadPoolSizeRequired;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>(fixedThreadPoolSizeRequired);
    }

    @Override
    public void submit(Runnable r) throws InterruptedException {
        taskQueue.put(r);
        if(totalWorkerThreadSoFar.get() < poolSize){
            totalWorkerThreadSoFar.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void shutDown() {
        for (WorkerThread worker : threads) {
            worker.stopThread();
            //worker.interrupt();
        }
    }

    public void waitUntilComplete(){

    }
}
