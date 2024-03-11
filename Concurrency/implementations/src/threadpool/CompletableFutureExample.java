package threadpool;

import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ThreadPoolExecutor poolExec = new ThreadPoolExecutor(3,3,1,
                TimeUnit.HOURS,new ArrayBlockingQueue<>(20), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            return "task completed";
        },poolExec);

        System.out.println(asyncTask1.get());


        // thenApply is synchronous
        CompletableFuture<String> asyncTask2 = CompletableFuture.supplyAsync(() -> {
            return "testing thenApply " + Thread.currentThread().getName();
        },poolExec).thenApply( v -> {
            return (v + " successfully " + Thread.currentThread().getName());
        });

        System.out.println(asyncTask2.get());


        // thenApply is Asynchronous
        CompletableFuture<String> asyncTask3 = CompletableFuture.supplyAsync(() -> {
            return "testing thenApplyAsync " + Thread.currentThread().getName();
        },poolExec).thenApplyAsync( v -> {
            return (v + " successfully " + Thread.currentThread().getName());
        },poolExec);

        System.out.println(asyncTask3.get());


        // chain dependent async tasks
        CompletableFuture<String> asyncTask4 = CompletableFuture.supplyAsync(() -> {
            return "testing thenCompose " + Thread.currentThread().getName();
        },poolExec).thenCompose( v -> {
            return CompletableFuture.supplyAsync(() -> {
               return v + " successfully " + Thread.currentThread().getName();
            });
        });

        System.out.println(asyncTask4.get());

        // chain dependent async tasks
        CompletableFuture<String> asyncTask5 = CompletableFuture.supplyAsync(() -> {
            return "testing thenComposeAsync " + Thread.currentThread().getName();
        },poolExec).thenComposeAsync( v -> {
            return CompletableFuture.supplyAsync(() -> {
                return v + " successfully " + Thread.currentThread().getName();
            });
        });

        System.out.println(asyncTask5.get());



        poolExec.shutdown();

        // thenAccept , thenAcceptAsync ==> does not return anything
        // theCombine, thenCombineAsync ==> used to combine the results of 2 comparable future



    }



}
