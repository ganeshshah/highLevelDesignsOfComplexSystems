package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

public class PriorityBasedExecutors {
    public static PriorityBasedThreadExecutorService getFixedThreadPool(int n) {
        return new PriorityBasedThreadPoolExecutor(n);
    }
}
