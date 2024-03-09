package readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Client {


    // readLock is a shared lock  ==> more than one thread can acquire it at a time
    // writeLock is a mutually exclusive lock ==> only one thread can acquire it at a time

    public static void main(String[] args) {

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedResource sharedResource = new SharedResource();

        Thread thread3 = new Thread(() -> {
            sharedResource.produce(readWriteLock);
        });

        Thread thread4 = new Thread(() -> {
            sharedResource.produce(readWriteLock);
        });

        Thread thread2 = new Thread(() -> {
            sharedResource.consume(readWriteLock);
        });


        thread3.start();
        thread4.start();

        thread2.start();


    }
}
