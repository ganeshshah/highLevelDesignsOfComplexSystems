package interviewpreparation.executorserviceimplementation.fixedthreadpool.frommedium;

public interface CustomExecutorService {

    /**
     * 1. user can set the size of Pool.
     * 2. user can submit tasks to the ExecutorService.
     * 3. It is the Responsibility of ExecutorService to execute submitted task.
     */

    void submit(Runnable runnable);

    void shutdown();
}
