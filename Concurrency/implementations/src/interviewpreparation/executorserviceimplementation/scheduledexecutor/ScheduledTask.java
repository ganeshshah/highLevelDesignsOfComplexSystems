package interviewpreparation.executorserviceimplementation.scheduledexecutor;


class ScheduledTask implements Comparable<ScheduledTask> {
    private final Runnable task;
    private long executeTime;
    private final TaskType taskType;

    private long subsequentDelay;


    private final long creationTime;




    public ScheduledTask(Runnable task, long executeTime, TaskType taskType, long subsequentDelay) {
        this.task = task;
        this.executeTime = executeTime;
        this.taskType = taskType;
        this.subsequentDelay = subsequentDelay;
        this.creationTime = System.currentTimeMillis();
    }

    public void run() {
        task.run();
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Runnable getTask() {
        return task;
    }

    public long getSubsequentDelay() {
        return subsequentDelay;
    }

    public long getCreationTime() {
        return creationTime;
    }
    public ScheduledTask getNewInstance(){
        ScheduledTask clone = new ScheduledTask(task,0,taskType,subsequentDelay);
        clone.setExecuteTime(clone.getCreationTime() + subsequentDelay );
        return clone;
    }

    @Override
    public int compareTo(ScheduledTask other) {
        return Long.compare(this.executeTime, other.executeTime);
    }
}

