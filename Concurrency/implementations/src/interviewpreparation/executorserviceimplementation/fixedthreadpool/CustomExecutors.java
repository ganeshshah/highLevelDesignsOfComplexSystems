package interviewpreparation.executorserviceimplementation.fixedthreadpool;



public class CustomExecutors {
    public static CustomExecutorService newFixedThreadPool(int fixedThreadPoolSizeRequired) {
        return new CustomThreadPoolExecutor(fixedThreadPoolSizeRequired);
    }
}
