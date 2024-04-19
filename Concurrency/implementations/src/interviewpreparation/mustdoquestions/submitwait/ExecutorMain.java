package interviewpreparation.mustdoquestions.submitwait;


import java.util.Date;


public class ExecutorMain {


    public static void main(String[] args) throws InterruptedException {

        int fixedThreadPoolSizeRequired = 5;

        CustomExecutorService customExecutor = CustomExecutors.newFixedThreadPool(fixedThreadPoolSizeRequired);

        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            customExecutor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + taskNumber + " executed by thread " + Thread.currentThread().getName() + " Current time : " + new Date());
            });
        }

        customExecutor.waitUntilComplete();

        for (int i = 11; i < 21; i++) {
            int taskNumber = i;
            customExecutor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + taskNumber + " executed by thread " + Thread.currentThread().getName() + " Current time : " + new Date());
            });
        }
        customExecutor.waitUntilComplete();

        for (int i = 23; i < 28; i++) {
            int taskNumber = i;
            customExecutor.submit(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + taskNumber + " executed by thread " + Thread.currentThread().getName() + " Current time : " + new Date());
            });
        }

        //Thread.sleep(10000);
        // Shutdown the thread pool
         //customExecutor.shutDown();

    }
}
