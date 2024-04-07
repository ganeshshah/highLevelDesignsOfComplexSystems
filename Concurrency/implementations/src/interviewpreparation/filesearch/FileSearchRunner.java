package interviewpreparation.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileSearchRunner implements Runnable {

    private String fileToBeSearched;
    private BlockingQueue<File> directoriesQueue = new LinkedBlockingQueue<>();

    private volatile AtomicBoolean fileFound;
    ArrayList<Thread> threads;


    FileSearchRunner(BlockingQueue<File> directoriesQueue, String fileToBeSearched, AtomicBoolean fileFound,ArrayList<Thread> threads) {
        this.fileToBeSearched = fileToBeSearched;
        this.directoriesQueue = directoriesQueue;
        this.fileFound = fileFound;
        this.threads = threads;
    }


    @Override
    public void run() {
        while (!directoriesQueue.isEmpty() && !fileFound.get()) {
            try{
                File file = directoriesQueue.take();
                performRecursiveSearch(file, fileToBeSearched);
            }catch(InterruptedException e){
                System.out.println("Interrupt signal received");
                return;
            }
        }
    }


    private void performRecursiveSearch(File file, String fileToBeSearched) {

        File[] files = file.listFiles();

        if (files == null || files.length == 0)
            return;

        for (File eachFile : files) {

            if(fileFound.get())
                return;

            if (eachFile.isDirectory()){
                performRecursiveSearch(eachFile, fileToBeSearched);
            }
            else {
                if (eachFile.getName().equals(fileToBeSearched)) {
                    System.out.println("File found");
                    System.out.println("File path is :" + eachFile.getAbsolutePath());
                    fileFound.set(true);
                    return;
                }
            }
        }
    }
}
