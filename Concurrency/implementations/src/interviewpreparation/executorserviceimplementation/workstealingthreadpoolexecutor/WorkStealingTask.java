package interviewpreparation.executorserviceimplementation.workstealingthreadpoolexecutor;

public class WorkStealingTask{

    Runnable r;

    public WorkStealingTask(Runnable r) {
        this.r = r;
    }

    public void run() {
        this.r.run();
    }
}
