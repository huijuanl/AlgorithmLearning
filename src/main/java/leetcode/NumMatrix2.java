package leetcode;

public class NumMatrix2 {
	int[][] dp;
	// dp[i][j]表示第i行以j结尾的部分和

	// 我的思路
	public NumMatrix2(int[][] matrix) {
		if (matrix.length == 0) return;
		dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				dp[i][j] = j == 0? matrix[i][j]: dp[i][j - 1] + matrix[i][j];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int res = 0;
		for (int i = row1; i <= row2; i++){
			res+= col1 == 0?dp[i][col2]: dp[i][col2] - dp[i][col1 - 1];
		}
		return res;
	}

	// leetcode上更好的思路：dp[i][j]表示[0,0] 到[i.j]形成的矩阵的和
}
