package leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/jump-game-ii/

// 我的思路：动态规划，时间复杂度为O(n2),空间复杂度为O(n)
// leetcode上的思路：贪心算法，时间复杂度是O(n), 空间复杂度是O(1)  (为什么贪心算法是最优解？)
public class JumpGameII {
	public int jump(int[] nums) {
		int []minNumber = new int[nums.length];
		Arrays.fill(minNumber, Integer.MAX_VALUE);
		minNumber[minNumber.length-1] = 0;
		for (int i = minNumber.length - 2; i >= 0; i--) {
			int maxIndex = Math.min(i + nums[i], minNumber.length -1);
			for (int j = maxIndex; j > i; j--) {
				if (minNumber[j] != Integer.MAX_VALUE) {
					minNumber[i] = Math.min(minNumber[i], 1 + minNumber[j]);
				}
			}
		}
        return minNumber[0];
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,0,1,4};
		int res = new JumpGameII().jump(nums);
		System.out.println("res: " + res);
	}
}
