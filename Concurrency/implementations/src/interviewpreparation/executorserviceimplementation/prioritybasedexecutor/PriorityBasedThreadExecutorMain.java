package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

public class PriorityBasedThreadExecutorMain {


    public static void main(String[] args) throws InterruptedException {

        PriorityBasedThreadExecutorService executors = PriorityBasedExecutors.getFixedThreadPool(1);

        executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);
        executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);
        executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);
        executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);
        executors.submit(()->{
            System.out.println("Task 1 : " + Thread.currentThread().getName());
        },1);
        executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);  executors.submit(()->{
            System.out.println("Task 5 : " + Thread.currentThread().getName());
        },5);
        executors.submit(()->{
            System.out.println("Task 1 : " + Thread.currentThread().getName());
        },1);
        executors.submit(()->{
            System.out.println("Tak 1 : " +  Thread.currentThread().getName());
        },1);

        executors.submit(()->{
            System.out.println("Task 1 : " + Thread.currentThread().getName());
        },1);
        executors.submit(()->{
            System.out.println("Task 1 : " + Thread.currentThread().getName());
        },1);
        executors.submit(()->{
            System.out.println("Task 1 : " + Thread.currentThread().getName());
        },1);

        //Thread.sleep(1000);

    }
}
