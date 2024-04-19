package interviewpreparation;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class InitRequestClose {

    private  AtomicBoolean isInitSuccessfull = new AtomicBoolean(false);
    private  AtomicInteger closePermit = new AtomicInteger(0);
    private Semaphore closeAllowed = new Semaphore(0);

    void init() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Init done successfully " + Thread.currentThread().getName());
        isInitSuccessfull.set(true);
    }

    void request() throws InterruptedException {
        if (isInitSuccessfull.get()) {
            Thread.sleep(100);
            System.out.println("Request executed successfully " + Thread.currentThread().getName());
            if (closePermit.get() == 0) {
                closePermit.set(1);
                closeAllowed.release();
            }
        }
    }

    synchronized void close() throws InterruptedException {
        if (isInitSuccessfull.get()) {
            closeAllowed.acquire();
            Thread.sleep(100);
            System.out.println("Closing all tasks " + Thread.currentThread().getName());
            closePermit.set(0);
        }
    }


}
