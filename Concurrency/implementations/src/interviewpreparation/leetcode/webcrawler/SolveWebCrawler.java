package interviewpreparation.leetcode.webcrawler;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class SolveWebCrawler implements Runnable{

    private BlockingQueue<String> taskQueue ;
    private Set<String> result; // private Set<String> result = ConcurrentHashMap.newKeySet();
    private String startUrl;
    private HtmlParser htmlParser;

    private String hostname;

    private boolean isStopped = false;

    private AtomicInteger activeTask;

    public SolveWebCrawler(BlockingQueue<String> taskQueue, Set<String> result, String startUrl, HtmlParser htmlParser, AtomicInteger activeTask) {
        this.taskQueue = taskQueue;
        this.result = result;
        this.startUrl = startUrl;
        this.htmlParser = htmlParser;
        this.hostname = getHostName(startUrl);
        this.activeTask = activeTask;
    }

    @Override
    public void run() {
          while(activeTask.get() > 0 ){
              if(!taskQueue.isEmpty()){
                  try {
                      activeTask.incrementAndGet();
                      String currentUrl = taskQueue.take();
                      List<String> urls = HtmlParser.getUrls(taskQueue.take());
                      for(String url : urls){
                          if(getHostName(url).equals(hostname) && !url.equals(currentUrl)){
                              result.add(url);
                              taskQueue.add(url);
                          }else if(getHostName(url).equals(hostname) && url.equals(currentUrl)){
                              result.add(url);
                          }
                      }
                      activeTask.decrementAndGet();
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
              }
          }

    }

    private String getHostName(String url) {
        return url.split("/")[2];
    }
}
