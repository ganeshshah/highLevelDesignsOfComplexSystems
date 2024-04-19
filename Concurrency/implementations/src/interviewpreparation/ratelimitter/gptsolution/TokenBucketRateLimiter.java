package interviewpreparation.ratelimitter.gptsolution;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter {
    private int capacity; // Maximum number of tokens the bucket can hold
    private double tokens; // Current number of tokens in the bucket
    private long lastRefillTime; // Last time the bucket was refilled
    private final long refillIntervalMillis; // Time interval for refilling the bucket
    private final double refillRate; // Rate at which tokens are added to the bucket per refill interval
    private final Lock lock; // Lock for thread safety

    public TokenBucketRateLimiter(int capacity, double refillRate, long refillIntervalMillis) {
        this.capacity = capacity;
        this.tokens = capacity; // Initially, bucket is full
        this.refillRate = refillRate;
        this.refillIntervalMillis = refillIntervalMillis;
        this.lastRefillTime = System.currentTimeMillis(); // Initialize last refill time to current time
        this.lock = new ReentrantLock(); // Initialize the lock
    }

    public boolean tryConsume(int tokensRequested) {
        lock.lock(); // Acquire the lock to ensure thread safety
        try {
            refill(); // Refill the bucket with tokens if needed
            if (tokens >= tokensRequested) { // Check if enough tokens are available
                tokens -= tokensRequested; // Consume the tokens
                return true; // Request allowed
            } else {
                return false; // Request rejected due to insufficient tokens
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    private void refill() {
        long currentTime = System.currentTimeMillis(); // Get the current time
        long timePassed = currentTime - lastRefillTime; // Calculate time passed since last refill
        if (timePassed > refillIntervalMillis) { // Check if refill interval has passed
            double tokensToAdd = (timePassed / refillIntervalMillis) * refillRate; // Calculate tokens to add
            tokens = Math.min(capacity, tokens + tokensToAdd); // Add tokens, ensuring not to exceed capacity
            lastRefillTime = currentTime; // Update last refill time
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(10, 1, 1000); // Create rate limiter with 10 tokens, refill rate of 1 token/sec, and refill interval of 1 second

        // Simulate 15 requests
        for (int i = 0; i < 15; i++) {
            Thread.sleep(100); // Simulate some delay between requests
            if (limiter.tryConsume(1)) {
                System.out.println("Request " + (i + 1) + ": Allowed"); // Request allowed if enough tokens available
            } else {
                System.out.println("Request " + (i + 1) + ": Rejected"); // Request rejected due to insufficient tokens
            }
        }
    }
}
