package interviewpreparation.ratelimitter.implementation;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class RequestProcessor implements Runnable{

    private Queue<Request> requestQueue;
    private int maxLimit;
    private TimeUnit unit;
    private long timeToProcessRequests;
    public RequestProcessor(Queue<Request> requestQueue, int maxLimit, int time, TimeUnit unit) {
        this.requestQueue = requestQueue;
        this.maxLimit = maxLimit;
        this.unit = unit;
        this.timeToProcessRequests = unit.toMillis(time);
    }

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(timeToProcessRequests);
                synchronized (requestQueue){
                    while(!requestQueue.isEmpty()){
                        System.out.println("Processing request : " + requestQueue.poll().getRequestId());
                    }
                }
                System.out.println("Done processing request in current queue...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
