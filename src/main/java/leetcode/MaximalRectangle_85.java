package leetcode;

import java.util.Stack;

// https://leetcode.com/problems/maximal-rectangle/
public class MaximalRectangle_85 {

	// 每一层看做是第84题
	public int maximalRectangle(char[][] matrix) {
		int maxRes = 0;
		if (matrix.length == 0) return maxRes;
		int[] nums = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '0') {
					nums[j] = 0;
				} else {
					nums[j] += matrix[i][j] - '0';
				}
			}
			int res = maximalEachLevel(nums);
			maxRes = Math.max(res, maxRes);
		}
		return maxRes;
	}

	// 官方题解法二用了一个二维动态规划: https://leetcode-cn.com/problems/maximal-rectangle/solution/zui-da-ju-xing-by-leetcode/
    // 是暴力法的优化，时间复杂度仍然比较高 （实际上等价于一个三维数组）
	// dp[i][j]表示以(i,j)为矩形右边界(右下角)的最长宽度 （高度是不确定的）
	// 时间复杂度为O(N^2*M)
	public int maximalRectangleDP(char[][] matrix) {

		if (matrix.length == 0) return 0;
		int maxarea = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];

		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if (matrix[i][j] == '1'){

					// compute the maximum width and update dp with it
					dp[i][j] = j == 0? 1 : dp[i][j-1] + 1;

					int width = dp[i][j];

					// compute the maximum area rectangle with a lower right corner at [i, j]
					for(int k = i; k >= 0; k--){
						width = Math.min(width, dp[k][j]);
						maxarea = Math.max(maxarea, width * (i - k + 1));
					}
				}
			}
		} return maxarea;
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
			maxValue = Math.max((right[i] - left[i] - 1) * nums[i], maxValue);

		}
		return maxValue;
	}

	public static void main(String[] args) {
		char[][] matrix = new char[][]{
				{'1','0','1','0','0'},
				{'1','0','1','1','1'},
				{'1','1','1','1','1'},
				{'1','0','0','1','0'}
		};

//		char[][] matrix = new char[][]{{'0', '1'}, {'1', '0'}};
		int res = new MaximalRectangle_85().maximalRectangle(matrix);
		System.out.println(res);
	}
}
