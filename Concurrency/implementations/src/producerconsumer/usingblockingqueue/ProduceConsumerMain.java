package producerconsumer.usingblockingqueue;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ProduceConsumerMain {


    public static void main(String[] args) throws InterruptedException {

        ArrayList<Thread> threads = new ArrayList<>();

        BlockingQueue<Long> taskQueue =  new ArrayBlockingQueue(5);

        Thread producerThread1 = new Thread(new Producer(taskQueue));
        threads.add(producerThread1);
        Thread producerThread2 = new Thread(new Producer(taskQueue));
        threads.add(producerThread2);
        Thread producerThread3 = new Thread(new Producer(taskQueue));
        threads.add(producerThread3);

        for(int i=0;i<5;i++){
            Thread consumerThread = new Thread(new Consumer(taskQueue));
            threads.add(consumerThread);
        }

        for(Thread thread : threads){
            thread.start();
        }

        Thread.sleep(1000);

        for(Thread thread : threads){
            thread.interrupt();
        }


    }
}
