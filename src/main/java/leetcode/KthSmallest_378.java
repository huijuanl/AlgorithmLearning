package leetcode;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class KthSmallest_378 {

	// 我的思路：用一个大小为k的大根堆
	// 时间复杂度: 最差O(n2)，最好O(nk)
	// 空间复杂度: O(k)
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix[0].length;

		PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> (b - a));
		int queueSize = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < Math.min(k, n); j++) {
				if (queue.isEmpty() || queueSize < k) {
					queue.add(matrix[i][j]);
					queueSize++;
				} else if (queue.peek() > matrix[i][j]) {
					queue.poll();
					queue.add(matrix[i][j]);
				} else break;
			}
		}
		return queueSize == k? queue.peek(): -1;
	}

	// leetcode上的解法：利用了二分查找方法,先给定一个值mid
	// 并从左下角开始统计整个矩阵中大于等于mid的数量（统计方式利用了矩阵的性质，时间复杂度为O(n)）
	// 总时间复杂度为O(log(r -l) * n)
	// 空间复杂度为O(1)
	public int kthSmallestOpt(int[][] matrix, int k) {
		int n = matrix.length;
		int left = matrix[0][0];
		int right = matrix[n - 1][n - 1];
		while (left < right) {
			int mid = left + ((right - left) >> 1);
			if (check(matrix, mid, k, n)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public boolean check(int[][] matrix, int mid, int k, int n) {
		int i = n - 1;
		int j = 0;
		int num = 0;
		while (i >= 0 && j < n) {
			if (matrix[i][j] <= mid) {
				num += i + 1;
				j++;
			} else {
				i--;
			}
		}
		return num >= k;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{ 1,  5,  9},
				{10, 11, 13},
				{12, 13, 15}
		};
		int k = 4;
		int res = new KthSmallest_378().kthSmallest(matrix, k);
		System.out.println(res);
	}
}
