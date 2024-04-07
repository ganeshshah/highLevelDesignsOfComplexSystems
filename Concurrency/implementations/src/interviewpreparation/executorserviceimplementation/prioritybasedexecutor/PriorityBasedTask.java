package interviewpreparation.executorserviceimplementation.prioritybasedexecutor;

public class PriorityBasedTask implements Comparable<PriorityBasedTask>{
    private final Runnable task;
    private  Integer priority;

    public PriorityBasedTask(Runnable r, int priority) {
        this.task = r;
        this.priority = priority;
    }

    public void run() {
        task.run();
    }

    public Runnable getTask() {
        return task;
    }

    public Integer getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PriorityBasedTask o) {
        return Integer.compare(this.priority, o.priority);
    }
}
