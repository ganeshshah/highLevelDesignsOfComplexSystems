package interviewpreparation.diningphilosopher.asleetcode;


import java.util.ArrayList;

public class DiningPhilosopherMain {


    public static void main(String[] args) throws Exception {

        // int n = 10;
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if(i==0){
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }else{
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads.add(t);
            t.start();
        }


        Thread.sleep(10000);

        for(Thread thread : threads)
            thread.interrupt();
    }
}
