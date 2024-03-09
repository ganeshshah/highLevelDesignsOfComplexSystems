package readwritelock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;

    public void produce(ReadWriteLock lock){
        try{
            lock.readLock().lock();
            System.out.println("lock acquired by thread : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.readLock().unlock();
            System.out.println("lock released by thread : " + Thread.currentThread().getName());
        }

    }

    public void consume(ReadWriteLock lock){
        try{
            lock.writeLock().lock();
            System.out.println("reentrantLock acquired by thread : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("reentrantLock released by thread : " + Thread.currentThread().getName());
            lock.writeLock().unlock();
        }

    }

}
