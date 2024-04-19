package interviewpreparation.mustdoquestions.initrequestclose;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InitRequestClose {


    AtomicBoolean isInitDone = new AtomicBoolean(false);
    ReentrantLock initLock = new ReentrantLock();
    Condition initWait = initLock.newCondition();
    AtomicBoolean isCloseDone = new AtomicBoolean(false);
    AtomicBoolean closeInitiated = new AtomicBoolean(false);
    AtomicInteger requestCount = new AtomicInteger(0);

    //Semaphore requestLock = new Semaphore(0);
     void init(){
         System.out.println("Initiating....");
         if(!isInitDone.get()){
             initLock.lock();
             try {
                 Thread.sleep(1000);
                 isInitDone.set(true);
                 initWait.signalAll();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }finally {
                 initLock.unlock();
                 System.out.println("Init done");
             }
         }else{
             System.out.println("Init already done");
         }
    }

    public void request() {

         if(!closeInitiated.get()){
             if (!isCloseDone.get()) {
                 requestCount.incrementAndGet();
                 if (!isInitDone.get()) {
//                     try {
//                         initLock.lock();
//                         initWait.await();
//                         initLock.unlock();
//                     } catch (InterruptedException e) {
//                         e.printStackTrace();
//                     }
                     System.out.println("Init not done, rejecting the request....");
                 }
                 try {
                     Thread.sleep(500);
                     System.out.println("Hey it's a request : " + Thread.currentThread().getName());
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 requestCount.decrementAndGet();
             }
         }

    }

    public void close() throws InterruptedException {
        if(!closeInitiated.get()){
            closeInitiated.set(true);
            while(requestCount.get() > 0){
                Thread.sleep(100);
            }
            isCloseDone.set(true);
            System.out.println("Closed the connection");
        }else{
            System.out.println("Connection is already closed");
        }
    }
}
