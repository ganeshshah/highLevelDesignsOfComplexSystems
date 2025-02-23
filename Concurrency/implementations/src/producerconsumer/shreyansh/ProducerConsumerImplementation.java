package producerconsumer.shreyansh;

public class ProducerConsumerImplementation {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource(4);

        Thread producerThread = new Thread(() -> {
            try {
                for(int i=0;i<6;i++){
                    sharedResource.produce(i);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for(int i=0;i<6;i++){
                    sharedResource.consume();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();

        // why not use suspend, resume, stop ==> no monitor-lock is release, no resource cleanup happens.

    }

}
