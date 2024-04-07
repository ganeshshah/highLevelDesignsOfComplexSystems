package interviewpreparation.executorserviceimplementation.fixedthreadpool.frommedium;

public class Execution implements Runnable{

    void executeMyMethod() {
        if (CustomThreadPool.currentCapacity < CustomThreadPool.capacity) {
            CustomThreadPool.currentCapacity++;
            Thread t = new Thread(new Execution());
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (CustomThreadPool.linkedBlockingQueue.size() != 0) {
                CustomThreadPool.linkedBlockingQueue.poll().run();
            }
        }
    }

    void shutDown(){

    }
}
