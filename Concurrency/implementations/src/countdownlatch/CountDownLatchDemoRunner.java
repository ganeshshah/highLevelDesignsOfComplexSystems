package countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemoRunner implements Runnable{


    private List<String> resultList;
    private CountDownLatch allThreadsReady;
    private CountDownLatch threadsReadyToRun;
    private CountDownLatch allThreadsExecutedSuccessFully;
    public CountDownLatchDemoRunner(List<String> resultList, CountDownLatch allThreadsReady, CountDownLatch threadsReadyToRun,
                                    CountDownLatch allThreadsExecutedSuccessFully) {
        this.resultList = resultList;
        this.allThreadsReady = allThreadsReady;
        this.threadsReadyToRun = threadsReadyToRun;
        this.allThreadsExecutedSuccessFully = allThreadsExecutedSuccessFully;
    }

    @Override
    public void run() {
        allThreadsReady.countDown();
        try {
            threadsReadyToRun.await();
            resultList.add("counting down");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            allThreadsExecutedSuccessFully.countDown();
        }
    }
}
