public class ReverseNumber {

    public static void main(String[] args) {

        System.out.println(reverseNumber(12345));
        System.out.println("Is number " + 123322 + " palindrome?? : " + isPalindrome(123322));
        System.out.println("Is number " + 123321 + " palindrome?? : " + isPalindrome(123321));
    }

    private static boolean isPalindrome(int n) {
        return n == reverseNumber(n);
    }


    static Integer reverseNumber(Integer n){

        if(n==0)
            return 0;

        return (int) ((n%10)*Math.pow(10,String.valueOf(n).length()-1) + reverseNumber(n/10));
    }
}
