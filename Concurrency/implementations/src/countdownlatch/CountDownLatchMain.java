package countdownlatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchMain {


    public static void main(String[] args) throws InterruptedException {

        // result list

        List<String> resultList = Collections.synchronizedList(new ArrayList<String>());
        CountDownLatch allThreadsReady = new CountDownLatch(5);
        CountDownLatch threadsReadyToRun = new CountDownLatch(1);
        CountDownLatch allThreadsExecutedSuccessFully = new CountDownLatch(5);

        for(int i=0;i<5;i++){
            CountDownLatchDemoRunner worker = new CountDownLatchDemoRunner(resultList,allThreadsReady,threadsReadyToRun,allThreadsExecutedSuccessFully);
            Thread t = new Thread(worker);
            t.start();
        }

        allThreadsReady.await();
        resultList.add("workers ready");
        threadsReadyToRun.countDown();
        allThreadsExecutedSuccessFully.await();
        resultList.add("workers finished work");

        for(String result : resultList){
            System.out.println(result);
        }

    }
}
