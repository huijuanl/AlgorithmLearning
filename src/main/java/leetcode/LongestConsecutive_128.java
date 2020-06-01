package leetcode;

import java.util.HashSet;

// https://leetcode.com/problems/longest-consecutive-sequence/
public class LongestConsecutive_128 {

	public int longestConsecutive(int[] nums) {

		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
		}

		int maxLen = 0;
		for (int i = 0; i < nums.length; i++) {
			if (!set.contains(nums[i] - 1)) { // 判断set不包含当前元素-1的值，跳过已经计算的最长递增序列

				int curValue = nums[i];
				int len = 1;
				while (set.contains(curValue + 1)) {
					len++;
					curValue++;
				}
				maxLen = Math.max(len, maxLen);

			}
		}

		return maxLen;
	}
}
