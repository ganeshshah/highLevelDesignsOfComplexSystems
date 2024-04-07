package interviewpreparation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RequestCounter {
    private final Map<String, Integer> data = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public void increment(String key) {
        lock.lock();
        try {
            data.put(key, data.getOrDefault(key, 0) + 1);
        } finally {
            lock.unlock();
        }
    }

    public int getCount(String key) {
        lock.lock();
        try {
            return data.getOrDefault(key, 0);
        } finally {
            lock.unlock();
        }
    }

    public Map<String, Integer> getData() {
        return data;
    }
}

class RequestTracker {
    private final long timeWindow;
    private final Map<String, RequestCounter> requestCounters = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public RequestTracker(long timeWindow) {
        this.timeWindow = timeWindow;
    }

    public void trackRequest(String attribute, String value) {
        long currentTime = System.currentTimeMillis() / 1000;
        lock.lock();
        try {
            if (!requestCounters.containsKey(attribute)) {
                requestCounters.put(attribute, new RequestCounter());
            }
            requestCounters.get(attribute).increment(value);

            // Remove expired entries
            for (Map.Entry<String, Integer> entry : requestCounters.get(attribute).getData().entrySet()) {
                if (currentTime - entry.getValue() > timeWindow) {
                    requestCounters.get(attribute).getData().remove(entry.getKey());
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public int getRequestCount(String attribute, String value) {
        lock.lock();
        try {
            if (requestCounters.containsKey(attribute)) {
                return requestCounters.get(attribute).getCount(value);
            }
            return 0;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        RequestTracker tracker = new RequestTracker(60); // Set time window to 60 seconds

        // Simulating incoming requests
        for (int i = 0; i < 10; i++) {
            simulateRequest(tracker, "192.168.0.1", "Chrome");
        }

        // Get request count for IP address "192.168.0.1" and BrowserAgent "Chrome"
        System.out.println("Requests from IP '192.168.0.1': " + tracker.getRequestCount("IP", "192.168.0.1"));
        System.out.println("Requests with BrowserAgent 'Chrome': " + tracker.getRequestCount("BrowserAgent", "Chrome"));
    }

    private static void simulateRequest(RequestTracker tracker, String ipAddress, String browserAgent) {
        tracker.trackRequest("IP", ipAddress);
        tracker.trackRequest("BrowserAgent", browserAgent);
    }
}

