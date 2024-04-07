package subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateSubsets {
    public static void main(String[] args) {

        String str = "abc";
        generateSubsets("",str);

        int[] arr = new int[]{1,2,3};
        List<List<Integer>> list = allSubsets(arr);

        System.out.println("Subsets:");
        for (List<Integer> subset : list) {
            System.out.println(subset);
        }

        System.out.println("generating subsets");
        generateSubsetsArray(arr,0,arr.length,"");



    }

    private static List<List<Integer>> allSubsets(int[] arr ) {

        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>()); // Add an empty subset initially

        // Iterate through each element of the array
        for (int num : arr) {
            int size = subsets.size();
            // Iterate through existing subsets and create new subsets by adding the current element
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(subsets.get(i));
                subset.add(num);
                subsets.add(subset);
            }
        }

        return subsets;
    }

    private static void generateSubsets(String p, String up) {

        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);
        generateSubsets(p,up.substring(1));
        generateSubsets(p+ch,up.substring(1));
    }


    private static void generateSubsetsArray(int[] arr,int i, int n, String osf) {
        if(i == n){
            System.out.println("output: " + osf);
            return;
        }
        generateSubsetsArray(arr,i+1,n,osf + "," + arr[i]);
        generateSubsetsArray(arr,i+1,n,osf);
    }
}
