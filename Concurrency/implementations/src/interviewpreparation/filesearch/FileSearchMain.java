package interviewpreparation.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSearchMain {


    public static void main(String[] args) throws InterruptedException {


        File files = new File("C:\\Windows\\");
        String fileToBeSearched = "hosts";

        BlockingQueue<File> directories = new LinkedBlockingQueue<>();
        AtomicBoolean fileFound = new AtomicBoolean(false);
        ArrayList<Thread> threads = new ArrayList<>();

        File[] allFilesInRootDirectory = files.listFiles();

        for(File file : allFilesInRootDirectory){
            if(file.isDirectory())
                directories.add(file);
            else if (file.getName().equals(fileToBeSearched))
                System.out.println("file found in path : " + file.getName());
        }

        ExecutorService executor = Executors.newFixedThreadPool(12);


        for(int i=0;i<12;i++){
            executor.submit(new FileSearchRunner(directories,fileToBeSearched, fileFound, threads));
        }

        while(!fileFound.get() && !directories.isEmpty()){
           Thread.sleep(100);
        }

        executor.shutdown();

//        for(int i=0; i<12;i++){
//            Thread t = new Thread(new FileSearchRunner(directories,fileToBeSearched, fileFound, threads));
//            threads.add(t);
//            t.start();
//        }
//
//        Thread.sleep(1000);
//
//            for(Thread thread : threads){
//                System.out.println(thread.getState());
//                thread.interrupt();
//            }
//
//            Thread.sleep(1000);
//        for(Thread thread : threads){
//            System.out.println(thread.getState());
//        }

    }
}
