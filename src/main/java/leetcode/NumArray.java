package leetcode;

// https://leetcode-cn.com/problems/range-sum-query-immutable/

public class NumArray {

	// dp[i]保存截止到i为止时的总和
	// (不要用二维数组，会超出内存限制:dp[i][j] = dp[i][j - 1] + nums[j])
	int[] dp;
	public NumArray(int[] nums) {
		dp = new int[nums.length]; // 截止i为止时的和
		for (int i = 0; i < nums.length; i++) {
				dp[i] = i >= 1?dp[i - 1] + nums[i]: nums[i];
		}

	}

	public int sumRange(int i, int j) {
		return i == 0? dp[j]: dp[j] - dp[i - 1];
	}

	public static void main(String[] args) {
		int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
		NumArray numArray = new NumArray(nums);
		System.out.println(numArray.sumRange(2, 5));

	}
}
