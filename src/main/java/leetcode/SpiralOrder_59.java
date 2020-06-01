package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/spiral-matrix-ii/
// 该题是54题的变形，没有什么难度
public class SpiralOrder_59 {
	public int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		int count = 1;
		for (int k = 0; k <= n - k - 1; k++) {
			for (int j = k; j <= n - k - 1; j++) {
				res[k][j] = count++;
			}

			for (int j = k + 1; j <= n - k - 1; j++) {
				res[j][n - k - 1] = count++;
			}

			// 注意一下边界的问题，防止只剩一行或者一列时扫两遍
			if (n - k - 1 > k) {
				for (int j = n - k - 2; j >= k; j--) {
					res[n - k - 1][j] = count++;
				}
			}

			if (n - k - 1 > k) {
				for (int j = n - k - 2; j > k; j--) {
					res[j][k] = count++;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {

		int[][] res = new SpiralOrder_59().generateMatrix(4);
		for(int i = 0; i < res.length; i++) {
			for(int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + ",");
			}
			System.out.println();
		}
	}
}
