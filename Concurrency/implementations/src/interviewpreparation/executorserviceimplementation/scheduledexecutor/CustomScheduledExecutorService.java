package interviewpreparation.executorserviceimplementation.scheduledexecutor;


import java.util.concurrent.TimeUnit;

/**
 * executor.schedule
 * executor.scheduleAtFixedRate
 * executor.scheduleWithFixedDelay
 */
public interface CustomScheduledExecutorService  {

    void submit(Runnable r, int delay, TimeUnit timeUnit) throws InterruptedException;
    void scheduleAtFixedRate(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit);

    void scheduleWithFixedDelay(Runnable r, int initialDelay, int subsequentDelay, TimeUnit timeUnit);

    void shutDown();

}
