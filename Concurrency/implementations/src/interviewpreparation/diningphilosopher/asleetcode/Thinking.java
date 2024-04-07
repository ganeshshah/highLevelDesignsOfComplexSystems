package interviewpreparation.diningphilosopher.asleetcode;

public class Thinking implements Runnable{

    @Override
    public void run() {
        System.out.println("Thinking out loud : " + Thread.currentThread().getName());
        try {
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Work interrupted : " + Thread.currentThread().getName());
        }
    }
}
