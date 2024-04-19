package interviewpreparation.uniqueidgenerator;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {

    private static AtomicInteger sequence = new AtomicInteger(0);
    private static long lastTimestamp = -1;

    public static synchronized String generateUniqueId() {
        long currentTimestamp = Instant.now().toEpochMilli();

        if (currentTimestamp != lastTimestamp) {
            lastTimestamp = currentTimestamp;
            sequence.set(0);
        } else {
            sequence.incrementAndGet();
        }

        return String.valueOf(currentTimestamp) + String.format("%03d", sequence.get());
    }

    public static void main(String[] args) {
        // Generating and printing 5 unique IDs
        for (int i = 0; i < 1000; i++) {
            String uniqueId = generateUniqueId();
            System.out.println("Unique ID " + (i + 1) + ": " + uniqueId);
        }
    }
}