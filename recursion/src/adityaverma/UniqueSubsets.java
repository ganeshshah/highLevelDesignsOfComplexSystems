package adityaverma;

import java.util.HashSet;

public class UniqueSubsets {


    public static void main(String[] args) {

        HashSet<String> set = new HashSet<>();
        getUniqueSubsets("aab", "", set);
        for(String s : set){
            System.out.println(s);
        }
    }

    private static void getUniqueSubsets(String ip, String op, HashSet<String> set) {

        if(ip.isEmpty()){
            set.add(op);
            return;
        }

        String op1 = op;
        String op2 = op + ip.charAt(0);
        getUniqueSubsets(ip.substring(1),op1,set);
        getUniqueSubsets(ip.substring(1),op2,set);
    }
}
