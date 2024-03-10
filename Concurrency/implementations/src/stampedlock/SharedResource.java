package stampedlock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {

    int a = 10;
    StampedLock lock = new StampedLock();

    public void produce(){
        try{
            long stamp = lock.tryOptimisticRead();
            System.out.println("Taken optimistic lock : " + Thread.currentThread().getName());
            a = 11;
            Thread.sleep(4000);

            if(lock.validate(stamp)){
                System.out.println("updated value successfully");
            }else{
                System.out.println("Rolling back the update");
                a = 10;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void consume(){
        long stamp = lock.writeLock();

        try{
            System.out.println("writeLock acquired by thread : " + Thread.currentThread().getName());
            a = 9;
        } finally {
            System.out.println("stampedLock released by thread : " + Thread.currentThread().getName());
            lock.unlockWrite(stamp);
        }

    }

}
