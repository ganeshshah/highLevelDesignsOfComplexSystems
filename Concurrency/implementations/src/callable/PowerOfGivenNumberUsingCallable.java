package callable;

import java.util.concurrent.Callable;

public class PowerOfGivenNumberUsingCallable implements Callable<Integer> {

    Integer number;
    Integer pow;

    public PowerOfGivenNumberUsingCallable(Integer number, Integer pow) {
        this.number = number;
        this.pow = pow;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(400);
        return (int) Math.pow(number,pow);
    }
}
