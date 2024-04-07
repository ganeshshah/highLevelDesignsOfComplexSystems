package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;

public class WorkStealingExecutors {
    public static WorkStealingExecutorService getFixedThreadPool(int poolSize) {
        return new WorkStealingExecutorThreadPool(poolSize);
    }
}
