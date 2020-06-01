package leetcode;

// https://leetcode.com/problems/longest-increasing-subsequence/
// 最长上升子序列长度
public class LengthOfLIS_300 {

	// 我的思路：dp[i]: 以i结尾的最长上升子序列的长度
	// 时间复杂度为O(N2)
	public int lengthOfLIS(int[] nums) {
		if (nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int maxLen = 1;
		dp[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			maxLen = Math.max(dp[i], maxLen);
		}

		return maxLen;
	}

	public static void main(String[] args) {
//		int[] nums = new int[]{1,3,5,4,7};
		int[] nums = {1,2,4,3,5,4,7,2};
		int res = new LengthOfLIS_300().lengthOfLIS(nums);
		System.out.println(res);
	}
}
