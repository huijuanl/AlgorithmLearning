package leetcode;

import java.util.Stack;

// https://leetcode.com/problems/maximal-square/
public class MaximalSquare_221 {

	// 我的思路：复用了第85题，并做了一处修改来判断是否为正方形 (打败了7%..)
	// int width = right[i] - left[i] - 1;
	//    if (width >= nums[i]) {
	//		maxValue = Math.max(nums[i], maxValue);
	//	  }
	public int maximalSquare(int[][] matrix) {
		int maxRes = 0;
		if (matrix.length == 0) return maxRes;
		int[] nums = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					nums[j] = 0;
				} else {
					nums[j] += 1;
				}
			}
			int res = maximalEachLevel(nums);
			maxRes = Math.max(res, maxRes);
		}
		return maxRes;
	}

	public int maximalEachLevel(int[] nums) {
		Stack<Integer> stack = new Stack<>();
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		left[0] = -1;
		right[right.length - 1] = right.length;
		stack.push(-1);
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() != -1 && nums[stack.peek()] >= nums[i]) {
				stack.pop();
			}
			left[i] = stack.peek();
			stack.push(i);
		}

		stack.clear();
		stack.push(nums.length);
		for (int i = nums.length - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() != nums.length && nums[stack.peek()] >= nums[i]) {
				stack.pop();
			}
			right[i] = stack.peek();
			stack.push(i);
		}

		int maxValue = 0;
		for (int i = 0; i < nums.length; i++) {
			int width = right[i] - left[i] - 1;
			if (width >= nums[i]) {
				maxValue = Math.max(nums[i], maxValue);
			}


		}
		return maxValue * maxValue;
	}

	// leetcode上的思路：用动态规划实现
	// dp[i][j]表示以节点[i,j]结尾的正方形的边长
	public int maximalSquare2(int[][] matrix) {
		if (matrix.length == 0) return 0;
		int maxRes = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					dp[i][j] = 0;
				} else if (i == 0 || j == 0) {
					dp[i][j] = matrix[i][j];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]),
							dp[i - 1][j]) + 1;
				}
				maxRes = Math.max(maxRes, dp[i][j]);
			}
		}
		return maxRes * maxRes;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1,0,1,0,0},
				{1,0,1,1,1},
				{1,1,1,1,1},
				{1,0,1,1,1}
		};

//		char[][] matrix = new char[][]{{'0', '1'}, {'1', '0'}};
		int res = new MaximalSquare_221().maximalSquare2(matrix);
		System.out.println(res);
	}
}
