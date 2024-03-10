package semaphoreLock;

import java.util.concurrent.locks.ReentrantLock;

public class Client {


    public static void main(String[] args) {


        Producer producer = new Producer();


        Thread thread1 = new Thread(() -> {
            producer.produce();
        });

        Thread thread2 = new Thread(() -> {
            producer.produce();
        });

        Thread thread3 = new Thread(() -> {
            producer.produce();
        });

        Thread thread4 = new Thread(() -> {
            producer.produce();
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        // when there is a need to have multiple instances of a class but the critical section of that class
        // irrespective of object needs to be accessed one thread at a time, we can use ReentrantLock

    }
}
