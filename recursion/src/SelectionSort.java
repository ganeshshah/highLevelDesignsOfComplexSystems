import java.util.Arrays;

public class SelectionSort {


    public static void main(String[] args) {
        int[] arr1 = new int[]{1,5,2,6,3,9};
        int[] arr2 = new int[]{1,5,2,6,3,9};

        iterativeSeletionSort(arr1);
        System.out.println(Arrays.toString(arr1));
        recursiveSeletionSort(arr2,arr2.length-1,0,0);
        System.out.println(Arrays.toString(arr2));
    }

    private static void recursiveSeletionSort(int[] arr, int i, int j, int max) {

        if(i == 0)
            return;

        if(j<i){
            if(arr[j] > arr[max])
                recursiveSeletionSort(arr,i,j+1,j);
            else
                recursiveSeletionSort(arr,i,j+1,max);
        }else{
            int temp = arr[i-1];
            arr[i-1] = arr[max];
            arr[max] = temp;
            recursiveSeletionSort(arr,i-1,0,0);
        }

    }

    private static void iterativeSeletionSort(int[] arr) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
