package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerMultiplePairsExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        // First pair
        new Thread(() -> exchangeData("Thread 1", "Data 1", exchanger)).start();
        new Thread(() -> exchangeData("Thread 2", "Data 2", exchanger)).start();

        // Second pair
        new Thread(() -> exchangeData("Thread 3", "Data 3", exchanger)).start();
        new Thread(() -> exchangeData("Thread 4", "Data 4", exchanger)).start();

        //new Thread(() -> exchangeData("Thread 5", "Data 5", exchanger)).start();
    }

    private static void exchangeData(String threadName, String data, Exchanger<String> exchanger) {
        try {
            System.out.println(threadName + " has data: " + data);

            // Exchange data with the other thread
            String receivedData = exchanger.exchange(data);
            System.out.println(threadName + " received: " + receivedData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
