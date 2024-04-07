package callable;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread<T> extends Thread {
    private FutureTask<T> futureTask;

    public MyThread(Callable<T> callable) {
        this.futureTask = new FutureTask<>(callable);
    }

    @Override
    public void run() {
        futureTask.run();
    }

    public T getResult() throws InterruptedException, ExecutionException {
        return futureTask.get();
    }

    public static void main(String[] args) {
        ArrayList<MyThread<Integer>> threads = new ArrayList<>();

        Callable<Integer> callable = () -> {
            // Simulate some long-running task
            Thread.sleep(2000);
            return 42;
        };

        for(int i=0; i<5; i++){
            MyThread<Integer> myThread = new MyThread<>(callable);
            threads.add(myThread);
            myThread.start();
        }

        for(MyThread<Integer> thread : threads){
            try {
                // Wait for the result and print it
                System.out.println("Result: " + thread.getResult());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
