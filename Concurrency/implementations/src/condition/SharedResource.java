package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {

    boolean isAvailable = false;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void produce(){

        lock.lock();
        try{
            if(isAvailable){
                System.out.println("produce already exist. waiting for it to be consumed....");
                condition.await();
            }
            isAvailable = true;
            condition.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("produced....");
            lock.unlock();
        }

    }

    public void consume(){
        lock.lock();
        try{
            if(!isAvailable){
                System.out.println("produce doesn't exist. waiting for it to be produced....");
                condition.await();
            }
            isAvailable = false;
            condition.signal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("consumed....");
            lock.unlock();
        }

    }

}
