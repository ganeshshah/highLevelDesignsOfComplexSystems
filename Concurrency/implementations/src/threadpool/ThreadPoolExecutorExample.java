package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

    // if task is cpu intensive use less thread
    // if  I/O intensive use more threads as many thread will be waiting for IO
    public static void main(String[] args) {

        ThreadPoolExecutor poolExec = new ThreadPoolExecutor(2,5,1,
                TimeUnit.HOURS,new ArrayBlockingQueue<>(20), new CustomThreadFactory(),new CustomRejectedHandler());

        poolExec.allowCoreThreadTimeOut(true);

        for(int i=0;i<25;i++){
            poolExec.submit(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("Thread name : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        poolExec.shutdown();
    }

}
