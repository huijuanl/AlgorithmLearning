package leetcode;

// https://leetcode-cn.com/problems/range-sum-query-2d-mutable/
public class NumMatrix_308 {

	int[][]dp;
	// dp[i][j]表示从[0,0]到[i,j]的和
	int[][] m;
	public NumMatrix_308(int[][] matrix) {
		if (matrix.length == 0) return;
		m = matrix;
		dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i > 0 && j > 0) {
					dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i][j];
				} else if (i == 0 && j ==0) {
					dp[i][j] = matrix[i][j];
				} else if (i > 0) {
					dp[i][j] = dp[i - 1][j] + matrix[i][j];
				} else {
					dp[i][j] = dp[i][j - 1] + matrix[i][j];
				}
			}
		}

	}

	public void update(int row, int col, int val) {
		int diff = val - m[row][col];
		m[row][col] = val;
		for (int i = row; i < dp.length; i++) {
			for (int j = col; j < dp[0].length; j++) {
				dp[i][j] += diff;
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (row1 > 0 && col1 > 0) {
			return dp[row2][col2] + dp[row1 - 1][col1 - 1] - dp[row1 - 1][col2] - dp[row2][col1 - 1];
		} else if (row1 == 0 && col1 == 0) {
			return dp[row2][col2];
		} else if (row1 == 0) {
			return dp[row2][col2] - dp[row2][col1 - 1];
		} else {
			return dp[row2][col2] - dp[row1 - 1][col2];
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{3, 0, 1, 4, 2},
				{5, 6, 3, 2, 1},
				{1, 2, 0, 1, 5},
				{4, 1, 0, 1, 7},
				{1, 0, 3, 0, 5}
		};
		NumMatrix_308 numMatrix_308 = new NumMatrix_308(matrix);
		System.out.println(numMatrix_308.sumRegion(2, 1, 4, 3));
		numMatrix_308.update(3,2,2);
		System.out.println(numMatrix_308.sumRegion(2, 1, 4, 3));

	}
}
