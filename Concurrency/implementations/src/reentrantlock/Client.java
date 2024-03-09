package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class Client {


    public static void main(String[] args) {

        ReentrantLock clientLock = new ReentrantLock();

        Producer producer1 = new Producer();
        Producer producer2 = new Producer();


        Thread thread1 = new Thread(() -> {
            producer1.produce();
        });

        Thread thread2 = new Thread(() -> {
            producer2.produce();
        });

        thread1.start();
        thread2.start();

        Thread thread3 = new Thread(() -> {
            producer1.produceWithLockAsParam(clientLock);
        });

        Thread thread4 = new Thread(() -> {
            producer2.produceWithLockAsParam(clientLock);
        });

        thread3.start();
        thread4.start();

        // when there is a need to have multiple instances of a class but the critical section of that class
        // irrespective of object needs to be accessed one thread at a time, we can use ReentrantLock

    }
}
