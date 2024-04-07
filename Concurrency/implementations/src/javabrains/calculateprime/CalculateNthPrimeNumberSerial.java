package javabrains.calculateprime;

public class CalculateNthPrimeNumberSerial {


    static int primeCount = 0;

    static int getNthPrimeNumber(int n) {

        int i = 2;

        while (primeCount < n) {
            if (isPrime(i)) {
                primeCount++;
            }
            i++;
        }
        return i-1;
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
