package interviewpreparation.diningphilosopher.asleetcode;

public class PickRightFork implements  Runnable{
    @Override
    public void run() {
        try {
            System.out.println("Picked Up Right Fork : " +  Thread.currentThread().getName());
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Work interrupted : " + Thread.currentThread().getName());
        }
    }
}
