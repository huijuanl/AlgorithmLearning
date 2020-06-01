package leetcode;

// https://leetcode.com/problems/maximum-product-subarray/
// 我的思路：用了两个额外的数组maxSum和minSum
// maxSum[i]: 以i为起点的子数组的最大乘积值
// minSum[i]: 以i为起点的子数组的最小乘积值
// 击败18%
public class MaxProductSubarray_152 {
	public int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int[]maxSum = new int[nums.length];
		maxSum[nums.length - 1] = nums[nums.length - 1];
		int[]minSum = new int[nums.length];
		minSum[nums.length - 1] = nums[nums.length - 1];
		int max = maxSum[nums.length-1];
		for (int i = nums.length - 2; i >= 0; i--) {
			maxSum[i] = Math.max(Math.max(maxSum[i+1]* nums[i], minSum[i+1]*nums[i]),nums[i]);
			minSum[i] = Math.min(Math.min(maxSum[i+1]* nums[i], minSum[i+1]*nums[i]),nums[i]);
			max = Math.max(maxSum[i], max);
		}
		return max;
	}

	// leetcode上的优化：https://leetcode-cn.com/problems/maximum-product-subarray/solution/cheng-ji-zui-da-zi-shu-zu-by-leetcode-solution/
	// 不用两个数组，而是用两个变量
	// 击败98%
	public int maxProductOpt(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int maxSum = nums[nums.length - 1];
		int minSum = nums[nums.length - 1];
		int max = maxSum;
		for (int i = nums.length - 2; i >= 0; i--) {
			int m = Math.max(Math.max(maxSum* nums[i], minSum*nums[i]),nums[i]);
			int n = Math.min(Math.min(maxSum* nums[i], minSum*nums[i]),nums[i]);
			maxSum = m;
			minSum = n;
			max = Math.max(maxSum, max);
		}
		return max;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{-4,-3,-2};
		int res = new MaxProductSubarray_152().maxProductOpt(nums);
		System.out.println(res);
	}
}
