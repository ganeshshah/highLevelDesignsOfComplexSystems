package interviewpreparation.diningphilosopher.asleetcode;

public class PickLeftFork implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("Picked up left fork : " + Thread.currentThread().getName());
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Work interrupted : " + Thread.currentThread().getName());
        }
    }
}
