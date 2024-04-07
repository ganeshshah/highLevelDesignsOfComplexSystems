

public class BubbleSort {

    public static void main(String[] args) {

        int[] arr1 = new int[]{1,5,2,6,3,9};
        int[] arr2 = new int[]{1,5,2,6,3,9};

        iterativeBubbleSort(arr1);
        printArray(arr1);
        System.out.println();
        recursiveBubbleSort(arr2,arr2.length-1,0);
        printArray(arr2);
        System.out.println();
        printTriangle(5,0);
    }

    private static void printTriangle(int i, int j) {

        if(i==0)
            return;
        if(j<i){
            System.out.print("*");
            printTriangle(i,j+1);
        }else{
            System.out.println();
            printTriangle(i-1,0);
        }
    }

    private static void recursiveBubbleSort(int[] arr,int i, int j) {

        if(i == 0)
            return;

        if(j<i){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
            }
            recursiveBubbleSort(arr,i,j+1);
        }else
            recursiveBubbleSort(arr,i-1,0);
    }

    private static void printArray(int[] arr1) {
        for(int i=0;i<arr1.length;i++)
            System.out.print(arr1[i] + " ");
    }

    private static void iterativeBubbleSort(int[] arr1) {

        for(int i=0; i< arr1.length-1; i++)
            for(int j=i+1;j<arr1.length; j++){
                if(arr1[i]>arr1[j]){
                    int temp = arr1[i];
                    arr1[i]=arr1[j];
                    arr1[j]=temp;
                }
            }
    }

}
