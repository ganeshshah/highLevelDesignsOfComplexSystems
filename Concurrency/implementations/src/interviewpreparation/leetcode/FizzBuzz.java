package interviewpreparation.leetcode;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * "fizzbuzz" if i is divisible by 3 and 5,
 * "fizz" if i is divisible by 3 and not 5,
 * "buzz" if i is divisible by 5 and not 3, or
 * i if i is not divisible by 3 or 5.
 */
public class FizzBuzz {

    private final int upperBound;
    private volatile AtomicInteger num = new AtomicInteger(1);

    public FizzBuzz(int upperBound) {
        this.upperBound = upperBound;
    }

    synchronized void printFizz() throws InterruptedException {
        while (num.get() <= upperBound) {
            while (!(num.get() % 3 == 0 && num.get() % 5 != 0)) {
                wait();
            }

            System.out.println(" Fizz");
            num.incrementAndGet();
            notifyAll();
        }
    }

    synchronized void printBuzz() throws InterruptedException {

        while (num.get() <= upperBound) {
            while (!(num.get() % 5 == 0 && num.get() % 3 != 0)) {
                wait();
            }

            System.out.println(" Buzz");
            num.incrementAndGet();
            notifyAll();
        }
    }

    synchronized void printFizzBuzz() throws InterruptedException {
        
        while (num.get() <= upperBound) {
            
            while (!(num.get()%5 == 0 && num.get()%3 == 0)) {
                wait();
            }


            System.out.println(" FizzBuzz");
            num.incrementAndGet();
            notifyAll();
        }

    }

    synchronized void printNumber() throws InterruptedException {
       
        while (num.get() <= upperBound) {
            while (!(num.get() % 5 != 0 && num.get() % 3 != 0)) {
                wait();
            }


            System.out.println(" " + num.get());
            num.incrementAndGet();
            notifyAll();
        }

    }


    public static void main(String[] args) {

        FizzBuzz fizzBuzzObject = new FizzBuzz(10);

        Thread thread1 = new Thread(() -> {
            try {
                fizzBuzzObject.printFizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                fizzBuzzObject.printBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                fizzBuzzObject.printFizzBuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                fizzBuzzObject.printNumber();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


}
