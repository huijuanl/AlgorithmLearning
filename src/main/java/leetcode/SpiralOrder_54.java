package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix/
public class SpiralOrder_54 {
	public List<Integer> spiralOrder(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<Integer> res = new ArrayList<>();
		for (int k = 0; k <= Math.min(m, n) - k - 1; k++) {
			for (int j = k; j <= n - k - 1; j++) {
				res.add(matrix[k][j]);
			}

			for (int j = k + 1; j <= m - k - 1; j++) {
				res.add(matrix[j][n - k - 1]);
			}

			// 注意一下边界的问题，防止只剩一行或者一列时扫两遍
			if (m - k - 1 > k) {
				for (int j = n - k - 2; j >= k; j--) {
					res.add(matrix[m - k - 1][j]);
				}
			}

			if (n - k - 1 > k) {
				for (int j = m - k - 2; j > k; j--) {
					res.add(matrix[j][k]);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[][] input = new int[][]{
				{1,2,3},{4,5,6},{7,8,9},{10,11,12}
		};
		List<Integer> res = new SpiralOrder_54().spiralOrder(input);
		for(int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i) + ",");
		}
	}
}
