package callable;

public class PowerOfGivenNumberUsingRunnable implements Runnable{

    Integer number;
    Integer pow;

    public PowerOfGivenNumberUsingRunnable(Integer number, Integer pow) {
        this.number = number;
        this.pow = pow;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(400);
            System.out.println(Math.pow(number,pow));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
