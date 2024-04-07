package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;

public interface WorkStealingExecutorService {
    public void submit(Runnable r) throws InterruptedException;
}
