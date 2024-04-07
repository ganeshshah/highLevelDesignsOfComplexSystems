package producerconsumer.queueimplementation2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ProduceConsumerMain {


    public static void main(String[] args) throws InterruptedException {

        ArrayList<Thread> threads = new ArrayList<>();

        Queue<Long> taskQueue =  new LinkedList<>();
        int bufferSize = 5;

        for(int i=0;i<2;i++){
            Thread consumerThread = new Thread(new Producer(taskQueue, bufferSize));
            threads.add(consumerThread);
        }

        for(int i=0;i<5;i++){
            Thread consumerThread = new Thread(new Consumer(taskQueue, bufferSize));
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
