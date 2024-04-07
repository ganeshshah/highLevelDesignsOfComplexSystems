package interviewpreparation.diningphilosopher.asleetcode;

public class Philosopher implements Runnable{

    private Object leftFork;
    private Object rightFork;

    private PickLeftFork pickLeftFork;
    private PickRightFork pickRightFork;
    private PutLeftFork putLeftFork;
    private PutRightFork putRightFork;
    private Eat eat;
    private Thinking thinking;


    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.pickLeftFork = new PickLeftFork();
        this.pickRightFork = new PickRightFork();
        this.putLeftFork = new PutLeftFork();
        this.putRightFork = new PutRightFork();
        this.eat = new Eat();
        this.thinking = new Thinking();
    }

    @Override
    public void run() {
        while(!Thread.interrupted() && !Thread.currentThread().isInterrupted()){ // use any one only
            synchronized (leftFork){
                synchronized (rightFork){
                    pickLeftFork.run();
                    pickRightFork.run();
                    eat.run();
                    putLeftFork.run();
                    putRightFork.run();
                }
            }
            thinking.run();
        }
    }
}
