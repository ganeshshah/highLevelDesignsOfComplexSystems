package interviewpreparation.ratelimitter.gptsolution;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter {
    private final int capacity;
    private final long intervalMillis;
    private final BlockingQueue<Object> bucket;

    public LeakyBucketRateLimiter(int capacity, int refillRatePerInterval, TimeUnit intervalUnit) {
        this.capacity = capacity;
        this.intervalMillis = intervalUnit.toMillis(1) / refillRatePerInterval;
        this.bucket = new LinkedBlockingQueue<>(capacity);
        startLeak();
    }

    private void startLeak() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(intervalMillis);
                    bucket.poll(); // Leak the bucket
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public boolean tryAcquire() {
        return bucket.offer(new Object()); // Add an object to the bucket
    }

    public static void main(String[] args) throws InterruptedException {
        LeakyBucketRateLimiter limiter = new LeakyBucketRateLimiter(10, 5, TimeUnit.SECONDS);
        for (int i = 0; i < 20; i++) {
            if(i > 10)
                Thread.sleep(100);
            if (limiter.tryAcquire()) {
                System.out.println("Request " + (i + 1) + " processed");
            } else {
                System.out.println("Request " + (i + 1) + " rejected");
            }
        }
    }
}
