package exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        // First thread
        new Thread(() -> {
            try {
                String data1 = "Thread 1 data";
                System.out.println("Thread 1 has data: " + data1);

                // Exchange data with the other thread
                String receivedData = exchanger.exchange(data1);
                System.out.println("Thread 1 received: " + receivedData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Second thread
        new Thread(() -> {
            try {
                String data2 = "Thread 2 data";
                System.out.println("Thread 2 has data: " + data2);

                // Exchange data with the other thread
                String receivedData = exchanger.exchange(data2);
                System.out.println("Thread 2 received: " + receivedData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
