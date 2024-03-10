package atomicoperation.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

    AtomicInteger counter = new AtomicInteger(0);

    public void increment(){
        counter.incrementAndGet();
    }

    public int get(){
        return counter.get();
    }

}


class Main{
    public static void main(String[] args) throws InterruptedException {

        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {
           for(int i = 0; i < 100 ; i++){
               sharedResource.increment();
           }
        });
        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < 100 ; i++){
                sharedResource.increment();
            }
        });
        Thread thread3 = new Thread(() -> {
            for(int i = 0; i < 100 ; i++){
                sharedResource.increment();
            }
        });


        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Value after increment : " + sharedResource.get());
    }
}