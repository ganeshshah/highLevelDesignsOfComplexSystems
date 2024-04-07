package callable;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

       long start = new Date().getTime();
        ArrayList<FutureTask<Integer>> listOfPowers = new ArrayList<>();
        for(int i=0;i<20;i++){
            FutureTask<Integer> future = new FutureTask<Integer>(new PowerOfGivenNumberUsingCallable(2,i));
            listOfPowers.add(future);
            Thread thread = new Thread(future);
            thread.start();
        }

        for(FutureTask<Integer> future : listOfPowers){
            System.out.println(future.get());
        }
        System.out.println("Total time taken by future task :" + (new Date().getTime() - start));

        long start1 = new Date().getTime();
        for(int i=0;i<20;i++){
            Thread thread = new Thread(new PowerOfGivenNumberUsingRunnable(2,i));
            thread.start();
            thread.join();
        }
        System.out.println("Total time taken by runnable task :" + (new Date().getTime() - start1));

    }

}
