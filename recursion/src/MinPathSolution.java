class MinPathSolution {

    private static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1,2,3},{4,5,6}};
        minPathSum(arr);

        System.out.println(minResult);

    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;


        findMinimumPathSum(grid,0,0,m,n,0);
        return minResult;

    }

    static void findMinimumPathSum(int[][] grid, int i, int j, int m, int n, int min){

        if(i == m-1 && j==n-1 ){
            minResult =  Math.min(minResult,min+grid[i][j]);
            return;
        }

        if(i >= m || j >= n)
            return;

        findMinimumPathSum(grid,i+1,j,m,n,min+grid[i][j]);
        findMinimumPathSum(grid,i,j+1,m,n,min+grid[i][j]);

    }
}