package interviewpreparation.executorserviceimplementation.fixedthreadpool.frommedium;

public class ExecutorServiceMain {

    public static void main(String[] args) {
        CustomExecutorService service = CustomExecutors.getCustomFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            service.submit(new Task());
        }

        service.shutdown();
    }




}
