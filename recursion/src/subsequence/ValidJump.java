package subsequence;

public class ValidJump {

    public static Integer totalWaysOfJump = 0;

    public static void main(String[] args) {

        int sizeOfArray = 7;
        printValidJump(sizeOfArray,0,"");
        System.out.println(totalWaysOfJump);

    }

    private static void printValidJump(int sizeOfArray, int startIndex,String osf) {

        if(startIndex == sizeOfArray-1){
            totalWaysOfJump++;
            System.out.println(osf);
            return;
        }

        if(startIndex >= sizeOfArray)
            return;

        printValidJump(sizeOfArray,startIndex+1,osf+ (startIndex+1) + "->");
        printValidJump(sizeOfArray,startIndex+2,osf+ (startIndex+2) + "->");
        printValidJump(sizeOfArray,startIndex+3,osf+ (startIndex+3) + "->");
        printValidJump(sizeOfArray,startIndex+4,osf+ (startIndex+4) + "->");
        printValidJump(sizeOfArray,startIndex+5,osf+ (startIndex+5) + "->");
        printValidJump(sizeOfArray,startIndex+6,osf+ (startIndex+6) + "->");

    }
}
