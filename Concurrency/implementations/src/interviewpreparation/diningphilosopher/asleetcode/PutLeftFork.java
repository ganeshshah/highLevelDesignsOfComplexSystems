package interviewpreparation.diningphilosopher.asleetcode;

public class PutLeftFork implements Runnable{
    @Override
    public void run() {

        try {
            System.out.println("Put down left fork : " + Thread.currentThread().getName());
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Work interrupted : " + Thread.currentThread().getName());
        }

    }
}
