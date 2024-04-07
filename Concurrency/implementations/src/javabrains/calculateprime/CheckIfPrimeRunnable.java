package javabrains.calculateprime;

public class CheckIfPrimeRunnable implements Runnable{

    int start;
    int end;

    public CheckIfPrimeRunnable(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {


    }


    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
