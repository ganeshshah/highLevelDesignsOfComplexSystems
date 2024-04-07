package subsequence;

public class RemoveCharFromString {

    public static void main(String[] args) {

        String str = "ccdbrtcd";
        char charToRemove = 'c';
        String result = removeChar(str, charToRemove,0,"");
        System.out.println(result);
    }

    private static String removeChar(String str, char charToRemove,int len, String resultantString) {
        if(len >= str.length())
            return resultantString;

        if(str.charAt(len) != charToRemove){
            resultantString += str.charAt(len);
        }

        return removeChar(str,charToRemove,len+1,resultantString);
    }

}
