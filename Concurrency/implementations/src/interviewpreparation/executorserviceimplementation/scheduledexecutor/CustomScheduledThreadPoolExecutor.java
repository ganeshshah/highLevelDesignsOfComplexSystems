package interviewpreparation.executorserviceimplementation.scheduledexecutor;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomScheduledThreadPoolExecutor implements CustomScheduledExecutorService{

    private volatile AtomicInteger totalWorkerThreadSoFar = new AtomicInteger(0);
    private final int poolSize;

    private PriorityBlockingQueue<ScheduledTask> taskQueue;
    private List<WorkerThread> threads;
    public CustomScheduledThreadPoolExecutor(int fixedThreadPoolSizeRequired) {
        this.poolSize = fixedThreadPoolSizeRequired;
        this.taskQueue = new PriorityBlockingQueue<>();
        this.threads = new ArrayList<>(fixedThreadPoolSizeRequired);
    }

    @Override
    public void shutDown() {

        for(WorkerThread thread : threads){
            System.out.println("Interrupt received, hence shutting down the executor");
            thread.stopExecution();
        }
    }


    @Override
    public void submit(Runnable r, int delay, TimeUnit timeUnit) throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        long executeTime = currentTime + timeUnit.toMillis(delay);
        ScheduledTask scheduledTask = new ScheduledTask(r,executeTime,TaskType.SINGLE_DELAY, 0);
        taskQueue.put(scheduledTask);
        if(totalWorkerThreadSoFar.get() < poolSize){
            totalWorkerThreadSoFar.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void scheduleAtFixedRate(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit) {
        long currentTime = System.currentTimeMillis();
        long executeTime = currentTime + timeUnit.toMillis(initialDelay);
        ScheduledTask scheduledTask = new ScheduledTask(r,executeTime,TaskType.FIXED_RATE,timeUnit.toMillis(subsequentDelay));
        taskQueue.put(scheduledTask);
        if(totalWorkerThreadSoFar.get() < poolSize){
            totalWorkerThreadSoFar.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public void scheduleWithFixedDelay(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit) {
        long currentTime = System.currentTimeMillis();
        long executeTime = currentTime + timeUnit.toMillis(initialDelay);
        ScheduledTask scheduledTask = new ScheduledTask(r,executeTime,TaskType.FIXED_DELAY,timeUnit.toMillis(subsequentDelay));
        taskQueue.put(scheduledTask);
        if(totalWorkerThreadSoFar.get() < poolSize){
            totalWorkerThreadSoFar.incrementAndGet();
            WorkerThread thread = new WorkerThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }
}
