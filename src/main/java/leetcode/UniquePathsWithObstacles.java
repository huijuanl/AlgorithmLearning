package leetcode;

// https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsWithObstacles {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] res = new int[m][n];
		if(obstacleGrid[m-1][n-1] == 1) {
			return 0;
		}
		res[m-1][n-1] = 1;
		for (int i = (m-2); i >= 0; i--) {
			if (obstacleGrid[i][n-1] == 1) {
				res[i][n-1] = 0;
			} else {
				res[i][n-1] = res[i+1][n-1];
			}
		}
		for (int j = (n-2); j >= 0; j--) {
			if (obstacleGrid[m-1][j] == 1) {
				res[m-1][j] = 0;
			} else {
				res[m-1][j] = res[m-1][j+1];
			}
		}
		// res[i][j] = res[i+1][j] + res[i][j+1]
		for (int i = m-2; i >= 0; i--) {
			for (int j = n-2; j >= 0; j--) {
				if (obstacleGrid[i][j] == 1) {
					res[i][j] = 0;
				} else {
					res[i][j] = res[i + 1][j] + res[i][j + 1];
				}
			}
		}
		return res[0][0];
	}
	public static void main(String[] args) {
		int[][]nums = new int[][]{{0,0,0}, {0,1,0}, {0,0,0}};

		int res = new UniquePathsWithObstacles().uniquePathsWithObstacles(nums);
		System.out.println("res: " + res);
	}
}
