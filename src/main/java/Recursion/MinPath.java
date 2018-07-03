package Recursion;
//给你一个二维数组，二维数组中的每个数都是正数，要求从左上角走到右下角，每一步只能向右或者向下。沿途经过的数字要累加起来。返回最小的路径和。
public class MinPath {
    public static int minPath1(int[][] matrix) {
        return process1(matrix,0,0);


    }
    public static int process1(int[][] matrix, int i, int j) {//返回matrix[i][j]点开始的最小路径
        if(i==(matrix.length-1)&&j==(matrix[0].length-1))
            return matrix[i][j];

        if(i==(matrix.length-1))
            return matrix[i][j]+process1(matrix,i,j+1);
        if(j==(matrix[0].length-1))
            return matrix[i][j]+process1(matrix,i+1,j);
        int right = process1(matrix,i,j+1);
        int down = process1(matrix,i+1,j);
        return matrix[i][j]+Math.min(right,down);

    }
    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath1(m));
    }
}
