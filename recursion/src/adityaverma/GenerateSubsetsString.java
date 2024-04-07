package adityaverma;

import java.util.ArrayList;


public class GenerateSubsetsString {


    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        getUniqueSubsets("abc", "", list);
        for(String s : list){
            System.out.println(s);
        }
    }

    private static void getUniqueSubsets(String ip, String op,  ArrayList<String> list) {

        if(ip.isEmpty()){
            list.add(op);
            return;
        }

        String op1 = op;
        String op2 = op + ip.charAt(0);
        getUniqueSubsets(ip.substring(1),op1,list);
        getUniqueSubsets(ip.substring(1),op2,list);
    }
}
