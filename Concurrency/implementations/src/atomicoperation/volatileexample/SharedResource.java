package atomicoperation.volatileexample;

public class SharedResource {

    private volatile Integer count = 0;


    public synchronized void  increment(){
        count++;
    }

    public Integer getCount(){
        return this.count;
    }

}

class Main {
    public static void main(String[] args) throws InterruptedException {

        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                sharedResource.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sharedResource.increment();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedResource.increment();
            }
        });


        thread1.start();
        thread2.start();
        thread3.start();

       Thread.sleep(200);

        System.out.println("Value after increment : " + sharedResource.getCount());
    }
}
