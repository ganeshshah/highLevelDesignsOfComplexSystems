//package javabrains.calculateprime;
//
//import threadpool.CustomRejectedHandler;
//import threadpool.CustomThreadFactory;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//
//public class CalculateNthPrimeParallel {
//
//
//
//
//    // Create a thread pool
//    // execute each calculation in a separate thread
//
//
//
//
//    static int primeCount = 0;
//
//    static int getNthPrimeNumber(int n) {
//
//        int totalProcessors = Runtime.getRuntime().availableProcessors();
//        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(totalProcessors);
//
//        //int i = 2;
//        int step = n / totalProcessors;
//
//        for(int i = 0; i<totalProcessors; i++){
//            fixedExecutorService.submit(new CheckIfPrimeRunnable(i,i+step));
//        }
//    }
//
//
//}
