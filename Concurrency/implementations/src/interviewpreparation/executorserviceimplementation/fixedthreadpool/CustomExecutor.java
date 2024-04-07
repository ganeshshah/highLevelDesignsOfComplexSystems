package interviewpreparation.executorserviceimplementation.fixedthreadpool;

public interface CustomExecutor {

    void submit(Runnable r) throws InterruptedException;
}
