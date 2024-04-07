package interviewpreparation.executorserviceimplementation.scheduledexecutor;

import java.util.concurrent.PriorityBlockingQueue;


public class WorkerThread extends Thread {

    private PriorityBlockingQueue<ScheduledTask> taskQueue;
    private volatile boolean isStopped = false;

    public WorkerThread(PriorityBlockingQueue<ScheduledTask> queue) {
        this.taskQueue = queue;
    }

    @Override
    public void run() {


        while (!isStopped) {
            if (taskQueue.size() > 0) {
                try {
                    ScheduledTask task = taskQueue.take();
                    if (task.getTaskType().equals(TaskType.SINGLE_DELAY))
                        singleDelayedExecution(task);
                    else if (task.getTaskType().equals(TaskType.FIXED_RATE))
                        fixedRateDelayExecution(task);
                    else if (task.getTaskType().equals(TaskType.FIXED_DELAY))
                        fixedDelayExecution(task);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("interrupt received, hence stopping");
                }
            }
        }

    }

    private void fixedRateDelayExecution(ScheduledTask task) throws InterruptedException {
        if ((task.getExecuteTime() - System.currentTimeMillis()) > 0) {
            taskQueue.offer(task);
        } else {
            task.run();
            //taskQueue.offer(task);
        }
    }

    private void fixedDelayExecution(ScheduledTask task) {
        if ((task.getExecuteTime() - System.currentTimeMillis()) > 0) {
            taskQueue.offer(task);
        } else {
            task.run();
            task.setExecuteTime(System.currentTimeMillis() + task.getSubsequentDelay());
            taskQueue.offer(task);
        }
    }

    void singleDelayedExecution(ScheduledTask task) {
        if ((task.getExecuteTime() - System.currentTimeMillis()) > 0) {
            taskQueue.offer(task);
        } else {
            task.run();
        }
    }


    void stopExecution() {
        this.isStopped = true;
        this.interrupt();
    }
}
