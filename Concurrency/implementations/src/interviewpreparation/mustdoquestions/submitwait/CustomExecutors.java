package interviewpreparation.mustdoquestions.submitwait;


public class CustomExecutors {
    public static CustomExecutorService newFixedThreadPool(int fixedThreadPoolSizeRequired) {
        return new CustomThreadPoolExecutor(fixedThreadPoolSizeRequired);
    }
}
