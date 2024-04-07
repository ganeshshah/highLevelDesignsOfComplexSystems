package unacademy;

public class MazePath {


    public static void main(String[] args) {



        printTotalPath(0,0,3,3,"");




    }

    static void printTotalPath(int i,int j, int n, int m, String osf){

        if(i== n-1 && j == m-1){
            System.out.println(osf);
        }

        if(i>= n || j >= m)
            return;

        printTotalPath(i,j+1,n,m,osf + "R"); // right movement
        printTotalPath(i+1,j,n,m,osf + "D"); // downward movement
        printTotalPath(i+1,j+1,n,m,osf + "<Diagonal>"); // diagonal movement
    }
}
