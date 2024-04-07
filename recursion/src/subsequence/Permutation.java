package subsequence;


public class Permutation {
    public static void main(String[] args) {
        String input = "abc";
        findPermutations("",input);
    }

    public static void findPermutations(String p, String up) {

        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        for(int i=0; i< up.length(); i++){
            char ch = up.charAt(i);
            String ros = up.substring(0,i) + up.substring(i+1);
            findPermutations(p+ch,ros);
        }

    }
}
