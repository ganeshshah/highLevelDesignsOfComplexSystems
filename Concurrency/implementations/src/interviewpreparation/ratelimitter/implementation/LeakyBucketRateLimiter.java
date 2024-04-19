package interviewpreparation.ratelimitter.implementation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

class Request {
    long requestId;

    Request(long requestId){
        this.requestId = requestId;
    }

    public long getRequestId() {
        return requestId;
    }
}

public class LeakyBucketRateLimiter {
    public static final int MAX_LIMIT = 5;
    public static final TimeUnit UNIT = TimeUnit.SECONDS;
    public static final int TIME = 3;

    public static void main(String[] args) throws InterruptedException {

        Queue<Request> requestQueue = new LinkedList<>();
        Thread processorThread = new Thread(new RequestProcessor(requestQueue,MAX_LIMIT,TIME,UNIT));
        processorThread.start();

        Thread requestThread1 = new Thread(() -> {
            for(int i=0;i<50;i++){
                if(i % 5 == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                addRequestToProcess(requestQueue, new Request(System.nanoTime()));
            }
        });

        Thread requestThread2 = new Thread(() -> {
            for(int i=0;i<50;i++){
                if(i % 5 == 0) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                addRequestToProcess(requestQueue, new Request(System.nanoTime()));
            }
        });

        requestThread1.start();
        //requestThread2.start();

    }

    static void addRequestToProcess(Queue<Request> requestQueue, Request request){
        synchronized (requestQueue){
            if(requestQueue.size() < MAX_LIMIT)
                requestQueue.add(request);
            else
                System.out.println("Request dropped as current process queue is full...  " + request.getRequestId());
        }
    }

}
