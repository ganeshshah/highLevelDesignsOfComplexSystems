package interviewpreparation.mustdoquestions.submitwait;

public interface CustomExecutor {

    void submit(Runnable r) throws InterruptedException;

    void waitUntilComplete() throws InterruptedException;
}
