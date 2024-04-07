package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityBasedThreadPoolExecutor implements PriorityBasedThreadExecutorService{

    private volatile AtomicInteger threadListSize = new AtomicInteger(0);
    PriorityBlockingQueue<PriorityBasedTask> taskQueue;
    List<WorkerThread> threadList;
    private final int poolSize;
    public PriorityBasedThreadPoolExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.taskQueue = new PriorityBlockingQueue<>();
        this.threadList = new ArrayList<>(poolSize);
    }

    @Override
    public void submit(Runnable r, int priority) {
        PriorityBasedTask task = new PriorityBasedTask(r,priority);
        taskQueue.put(task);
        if(threadListSize.get() < poolSize){
            threadListSize.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threadList.add(thread);
            thread.start();
        }
    }

    @Override
    public void shutdown() {

    }
}
