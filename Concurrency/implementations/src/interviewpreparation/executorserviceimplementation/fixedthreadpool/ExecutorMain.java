package interviewpreparation.executorserviceimplementation.fixedthreadpool;



public class ExecutorMain {


    public static void main(String[] args) throws InterruptedException {

        int fixedThreadPoolSizeRequired = 5;

        CustomExecutorService customExecutor = CustomExecutors.newFixedThreadPool(fixedThreadPoolSizeRequired);


        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            customExecutor.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by thread " + Thread.currentThread().getName());
            });
        }

        Thread.sleep(2000);
        // Shutdown the thread pool
         customExecutor.shutDown();

    }
}
