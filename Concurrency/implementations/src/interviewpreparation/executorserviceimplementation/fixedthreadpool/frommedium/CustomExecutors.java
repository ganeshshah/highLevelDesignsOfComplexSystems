package interviewpreparation.executorserviceimplementation.fixedthreadpool.frommedium;

public class CustomExecutors {

    int capacity;

    static CustomExecutorService getCustomFixedThreadPool(int capacity) {
        return new CustomThreadPool(capacity);
    }
}
