package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkStealingMain {


    public static void main(String[] args) throws InterruptedException {


        WorkStealingExecutorService executor = WorkStealingExecutors.getFixedThreadPool(5);

        for(int i=0; i<50; i++){
            int ii = i;
            executor.submit(()->{
                System.out.println("Sample task - " + ii + " to sleep 1 seconds in thread : " +  Thread.currentThread().getName());
            });
        }

    }
}
