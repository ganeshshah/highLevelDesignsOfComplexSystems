package interviewpreparation.executorserviceimplementation.fixedthreadpool.frommedium;

import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool implements CustomExecutorService{

    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    Execution e;

    public CustomThreadPool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        linkedBlockingQueue = new LinkedBlockingQueue<Runnable>();
        e = new Execution();
    }

    @Override
    public void submit(Runnable r) {
        linkedBlockingQueue.add(r);
        e.executeMyMethod();
    }

    @Override
    public void shutdown() {

    }
}
