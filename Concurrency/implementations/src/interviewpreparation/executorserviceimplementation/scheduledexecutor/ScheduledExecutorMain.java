package interviewpreparation.executorserviceimplementation.scheduledexecutor;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorMain {

    /**
     * Implement following method of ScheduledExecutorService interface in Java
     *
     * schedule(Runnable command, long delay, TimeUnit unit)
     * Creates and executes a one-shot action that becomes enabled after the given delay.
     *
     * scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently with the given period;
     * that is executions will commence after initialDelay then initialDelay+period, then initialDelay + 2 * period, and so on.
     *
     * scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
     * Creates and executes a periodic action that becomes enabled first after the given initial delay, and subsequently with the given delay between
     * the termination of one execution and the
     */

    public static void main(String[] args) throws InterruptedException {

        //CustomScheduledExecutorService executor = CustomScheduledExecutors.newSingleThreadScheduledExecutor();

        CustomScheduledExecutorService executor = CustomScheduledExecutors.newScheduledThreadPool(3);

        executor.submit(()->{
            System.out.println("Scheduled runnable example 1 " + Thread.currentThread().getName());
        },1, TimeUnit.SECONDS);
        executor.submit(()->{
            System.out.println("Scheduled runnable example 2 " + Thread.currentThread().getName());
        },0, TimeUnit.SECONDS);
        executor.submit(()->{
            System.out.println("Scheduled runnable example 3 " + Thread.currentThread().getName());
        },3, TimeUnit.SECONDS);

        executor.submit(()->{
            System.out.println("Scheduled runnable example 4 " + Thread.currentThread().getName());
        },1, TimeUnit.SECONDS);
        executor.submit(()->{
            System.out.println("Scheduled runnable example 5 " + Thread.currentThread().getName());
        },2, TimeUnit.SECONDS);
        executor.submit(()->{
            System.out.println("Scheduled runnable example 6 " + Thread.currentThread().getName());
        },2, TimeUnit.SECONDS);


        executor.scheduleAtFixedRate(()->{
            System.out.println("Fixed Rate Scheduled runnable example 7 " + Thread.currentThread().getName());
        },0,5,TimeUnit.SECONDS);


        executor.scheduleWithFixedDelay(()->{
            System.out.println("Fixed Delay Scheduled runnable example 8 " + Thread.currentThread().getName() + " current time : " + new Date());
        },0,5,TimeUnit.SECONDS);

        Thread.sleep(10000);
        executor.shutDown();

    }

}
