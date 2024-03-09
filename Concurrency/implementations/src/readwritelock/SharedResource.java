package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedResource {

    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        this.bufferSize = bufferSize;
        this.sharedBuffer = new LinkedList<>();
    }

    public synchronized void produce(int item) throws Exception{

        while(sharedBuffer.size() == bufferSize){
            System.out.println("buffer is full, waiting for consumer to consume");
            wait();
        }

        sharedBuffer.add(item);
        System.out.println("produced item : " + item);
        notify();
    }

    public synchronized void consume() throws Exception{

        while(sharedBuffer.isEmpty()){
            System.out.println("buffer is empty, waiting for producer to produce");
            wait();
        }

        System.out.println("consumed item : " + sharedBuffer.poll());
        notify();
    }



}
