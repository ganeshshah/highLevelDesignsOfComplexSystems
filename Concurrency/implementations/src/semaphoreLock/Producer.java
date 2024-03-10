package semaphoreLock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Producer {

    boolean isAvailable = false;
    Semaphore lock = new Semaphore(2);


    public void produce(){
        try{
            lock.acquire();
            System.out.println("lock acquired by thread : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("lock released by thread : " + Thread.currentThread().getName());
            lock.release();
        }

    }
// when more than one thread needs access to critical section use - semaphores

}
