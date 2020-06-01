package leetcode;

// https://leetcode.com/problems/minimum-path-sum/
// 典型的动态规划类型
public class MinPathSum {
	public int minPathSum(int[][] grid) {
		int[][] sum = new int[grid.length][grid[0].length];
		for (int i = grid.length - 1; i >= 0; i--) {
			for (int j = grid[0].length - 1; j >= 0; j--) {
				if (i == grid.length - 1 && j == grid[0].length - 1) {
					sum[i][j] = grid[i][j];
				} else if (j == grid[0].length - 1) {
					sum[i][j] = sum[i + 1][j] + grid[i][j];
				} else if (i == grid[0].length - 1) {
					sum[i][j] = sum[i][j + 1] + grid[i][j];
				} else {
					sum[i][j] = Math.min(sum[i + 1][j], sum[i][j + 1]) + grid[i][j];
				}
			}
		}
		return sum[0][0];
	}

	public static void main(String[] args) {
		int[][] grid = new int[][]{{1,3,1}, {1,5,1}, {4,2,1}};
		int res = new MinPathSum().minPathSum(grid);
		System.out.println(res);
	}
}
