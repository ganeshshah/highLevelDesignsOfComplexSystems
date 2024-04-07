package interviewpreparation.executorserviceimplementation.scheduledexecutor;

public class CustomScheduledExecutors {
    public static CustomScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new CustomScheduledThreadPoolExecutor(1);
    }

    public static CustomScheduledExecutorService newScheduledThreadPool(int n) {
        return new CustomScheduledThreadPoolExecutor(n);
    }
}
