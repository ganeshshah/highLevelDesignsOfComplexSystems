package threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class WorkStealingPoolForkJoinImplementation {

    // WorkStealingQueue and SubmissionQueue
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> futureObject = pool.submit(new ComputeSumTask(0,100));
        System.out.println(futureObject.get());


    }

}


// Tasks can be broken into sub-task by implementing RecursiveTask and RecursiveAction

class ComputeSumTask extends RecursiveTask<Integer>{

    int start;
    int end;

    public ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end - start <= 4){
            int totalSum = 0;
            for(int i=0;i<=end;i++){
                totalSum += i;
            }
            return totalSum;
        }else{
            // split tasks
            int mid = (start + end) /2;
            ComputeSumTask leftTask = new ComputeSumTask(start,mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid+1,end);

            // fork tasks for parallel execution
            leftTask.fork();
            rightTask.fork();


            // Combine the results of subtasks

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            return leftResult + rightResult;

        }

    }
}