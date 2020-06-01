package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

// https://leetcode-cn.com/problems/next-greater-element-i/
public class NextGreaterElement_496 {

	// 我的思路：739的变形，用了一个单调递减栈
	// 栈里面存储的是还没找到下一个比它大的元素的下标
	// 多用了一个额外的map来进行剪枝
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map = new HashMap<>();
		// key:表示nums2中的下标, value表示nums1中的下标
		int minIndex = Integer.MAX_VALUE;
		for (int i = 0; i < nums2.length; i++) {
			for (int j = 0; j < nums1.length; j++) {
				if (nums1[j] == nums2[i]) {
					map.put(i, j);
					minIndex = Math.min(minIndex, i);
				}
			}
		}

		int[] dp = new int[nums1.length];
		Arrays.fill(dp, -1);
		Stack<Integer> stack = new Stack<>();
		for (int i = minIndex; i < nums2.length; i++) {
			if (stack.isEmpty() && map.containsKey(i)) {
				stack.add(i);
			} else {
				while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
					int index = stack.pop();
					dp[map.get(index)] = nums2[i];
				}
				if (map.containsKey(i)) {
					stack.add(i);
				}
			}
		}
		return dp;

	}

	// leetcode上效率更高的解法：先忽略nums[1]
	public int[] nextGreaterElementOpt(int[] nums1, int[] nums2) {
		Stack < Integer > stack = new Stack < > ();
		HashMap < Integer, Integer > map = new HashMap < > ();
		int[] res = new int[nums1.length];
		for (int i = 0; i < nums2.length; i++) {
			while (!stack.empty() && nums2[i] > stack.peek())
				map.put(stack.pop(), nums2[i]);
			stack.push(nums2[i]);
		}
		while (!stack.empty())
			map.put(stack.pop(), -1);
		for (int i = 0; i < nums1.length; i++) {
			res[i] = map.get(nums1[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums1 = new int[]{4,1,2};
		int[] nums2 = new int[]{1,3,4,2};
		int[] dp = new NextGreaterElement_496().nextGreaterElement(nums1, nums2);
		for(int each: dp) {
			System.out.println(each + ",");
		}
	}
}
