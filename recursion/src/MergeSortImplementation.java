import java.util.Arrays;

public class MergeSortImplementation {

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,7,3,9,4};
        int[] sortedArray = mergeSort(arr);
        System.out.println( Arrays.toString(sortedArray));

    }

    private static int[] mergeSort(int[] arr) {
        if(arr.length == 1)
            return arr;

        int mid = arr.length / 2;

        int[] left = mergeSort(Arrays.copyOfRange(arr,0,mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr,mid,arr.length));

        return merge(left,right);
    }

    private static int[] merge(int[] left, int[] right) {

        int[] mergedArray = new int[left.length + right.length];

        int i=0, j=0, k=0;

        while(i < left.length && j<right.length){
            if(left[i]<right[j])
                mergedArray[k++] = left[i++];
            else
                mergedArray[k++] = right[j++];
        }

        while(i<left.length){
            mergedArray[k++] = left[i++];
        }

        while(j<right.length){
            mergedArray[k++] = right[j++];
        }

        return mergedArray;
    }

}
