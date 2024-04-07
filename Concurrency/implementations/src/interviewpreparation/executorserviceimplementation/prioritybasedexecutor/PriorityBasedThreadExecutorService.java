package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

public interface PriorityBasedThreadExecutorService {
    void submit(Runnable r, int priority);
    void shutdown();
}
