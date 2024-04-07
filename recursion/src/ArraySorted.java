public class ArraySorted {


    public static void main(String[] args) {

        int[] arr = new int[]{1,2,5,15,7,9};
        System.out.println("Array is sorted in ascending order : " + isArraySorted(arr,0));

    }

    private static Boolean isArraySorted(int[] arr, int indexChecked) {

        if(indexChecked >= arr.length-1)
            return true;

        if(arr[indexChecked]<=arr[indexChecked+1])
            return isArraySorted(arr,indexChecked+1);


        return false;
    }
}
