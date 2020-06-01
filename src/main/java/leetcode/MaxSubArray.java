package leetcode;

// https://leetcode.com/problems/maximum-subarray/
public class MaxSubArray {

	// 动态规划的问题
	// 以每个点为起点的最大子序列的和
	// sum[i] = max{sum[i+1] + num[i], nums[i+1]}

	public int maxSubArray(int[] nums) {
		int start = 0;
		int[] maxSum = new int[nums.length];
		int max = nums[nums.length - 1];
		maxSum[nums.length - 1] = nums[nums.length - 1];
		for (int i = nums.length - 2; i >= 0; i--) {
			maxSum[i] = Math.max(maxSum[i+1] + nums[i], nums[i]);
			max = Math.max(max, maxSum[i]);
		}
		return max;

	}

	public static void main(String[] args) {
		MaxSubArray maxSub = new MaxSubArray();
		int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		int res = maxSub.maxSubArray(nums);
		System.out.println(res);
	}
}
