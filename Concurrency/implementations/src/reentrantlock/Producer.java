package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Producer {

    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();


    public void produce(){
        try{
            lock.lock();
            System.out.println("lock acquired by thread : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println("lock released by thread : " + Thread.currentThread().getName());
        }

    }

    public void produceWithLockAsParam(ReentrantLock reentrantLock){
        try{
            reentrantLock.lock();
            System.out.println("reentrantLock acquired by thread : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("reentrantLock released by thread : " + Thread.currentThread().getName());
            reentrantLock.unlock();
        }

    }


}
