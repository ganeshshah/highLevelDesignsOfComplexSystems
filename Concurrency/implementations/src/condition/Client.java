package condition;

public class Client {


    // readLock is a shared lock  ==> more than one thread can acquire it at a time
    // writeLock is a mutually exclusive lock ==> only one thread can acquire it at a time

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread thread3 = new Thread(() -> {
            sharedResource.produce();
        });

        Thread thread4 = new Thread(() -> {
            sharedResource.consume();
        });



        thread4.start();
        thread3.start();

    }
}
