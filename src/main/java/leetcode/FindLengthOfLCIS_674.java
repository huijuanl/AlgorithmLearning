package leetcode;

// https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
public class FindLengthOfLCIS_674 {
	// 我的思路：两个辅助数组
	// 时间复杂度为O(N2)
	// dp[i]表示以i结尾的最大长度的个数
	// max[i]表示以i结尾的最大长度
	// 本题基于第300题多了一个辅助数组记录以i结尾的最大长度的个数
	public int findNumberOfLIS(int[] nums) {
		if (nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		int[] max = new int[nums.length];
		int maxLen = 0;
		for (int i = 0; i < nums.length; i++) {
			max[i] = 1;
			dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					if (max[j] + 1 > max[i]) {
						dp[i] = dp[j];
						max[i] = max[j] + 1;
					} else if (max[j] + 1 == max[i]) {
						dp[i] += dp[j];
					}

				}
			}
			maxLen = Math.max(max[i], maxLen);
		}
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (max[i] == maxLen) {
				count+=dp[i];
			}
		}
		return count;
	}

	public static void main(String[] args) {
//		int[] nums = new int[]{1,3,5,4,7};
		int[] nums = {1,2,4,3,5,4,7,2};
		int res = new FindLengthOfLCIS_674().findNumberOfLIS(nums);
		System.out.println(res);
	}
}
