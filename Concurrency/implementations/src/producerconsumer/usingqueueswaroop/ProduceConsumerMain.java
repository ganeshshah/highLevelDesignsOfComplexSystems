package producerconsumer.usingqueueswaroop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class ProduceConsumerMain {
    static Queue<Long> taskQueue =  new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition isFull = lock.newCondition();
        Condition isEmpty = lock.newCondition();

        ArrayList<Thread> threads = new ArrayList<>();

        int bufferSize = 5;

        Thread producerThread1 = new Thread(new Producer(taskQueue,bufferSize,lock, isFull, isEmpty));
        threads.add(producerThread1);


        //Consumer consumer = new Consumer(taskQueue,bufferSize,lock, isFull, isEmpty);

        for(int i=0;i<5;i++){
            Thread consumerThread = new Thread(new Consumer(taskQueue,bufferSize,lock, isFull, isEmpty));
            threads.add(consumerThread);
        }

        for(Thread thread : threads){
            thread.start();
        }

//        Thread.sleep(1000);
//
//        for(Thread thread : threads){
//            thread.interrupt();
//        }


    }

}
