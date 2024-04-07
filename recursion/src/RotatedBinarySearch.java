public class RotatedBinarySearch {


    public static void main(String[] args) {

        int[] arr = new int[]{5,6,7,1,2,3,4};
        System.out.println("Element found : " + rotatedBinarySearch(arr,0,arr.length-1,2));
    }

    private static Boolean rotatedBinarySearch(int[] arr, int left, int right, int element) {

        if(left > right)
            return false;

        int mid = (left + right)/2;

        if(arr[mid] == element)
            return true;

        if(arr[left] <= arr[mid]){
            if(element >= arr[left] && element <= arr[mid])
                return rotatedBinarySearch(arr,left,mid-1,element);
            else
                return rotatedBinarySearch(arr,mid+1,right,element);
        }


        if(element >= arr[mid] && element <= arr[right])
            return rotatedBinarySearch(arr,mid+1,right,element);

        return rotatedBinarySearch(arr,left,mid-1,element);

    }
    

    private static boolean binarySearch(int[] array, int left,int right, int search) {

        int mid = (right + left) / 2;

        if(left>right)
            return false;

        if(array[mid] == search)
            return true;
        else if(array[mid]>search)
            return binarySearch(array,left,mid-1,search);
        else
            return binarySearch(array,mid+1,right,search);
    }
}
