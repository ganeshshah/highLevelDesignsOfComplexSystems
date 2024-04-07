package javabrains.calculateprime;

import java.util.Date;

public class CalculatePrimeMain {


    public static void main(String[] args) {
        int n = 10000000;
        System.out.println("Calculating prime sequentially");
        Date start = new Date();
        System.out.println(CalculateNthPrimeNumberSerial.getNthPrimeNumber(n));
        Date end = new Date();
        System.out.println("Total time taken for serial execution : " + (start.getTime() - end.getTime()));
    }




}
