package leetcode;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
// 维护一个单调栈，栈中总是保存递增元素的索引，当遇到比栈顶元素小的元素时，将栈顶元素依次出栈，
// 获取以第i个柱子为高的左边界和右边界:
// 遍历两次，分别获得左边界和右边界，即左边第一个比第i个柱子矮的柱子，和右边第一个比i矮的柱子
public class LargestRectangleArea_84 {

	// leetcode上的解法:
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		int[] left = new int[n];
		int[] right = new int[n];

		Stack<Integer> mono_stack = new Stack<Integer>();
		for (int i = 0; i < n; ++i) {
			while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
				mono_stack.pop();
			}
			left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
			mono_stack.push(i);
		}

		mono_stack.clear();
		for (int i = n - 1; i >= 0; --i) {
			while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
				mono_stack.pop();
			}
			right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
			mono_stack.push(i);
		}

		int ans = 0;
		for (int i = 0; i < n; ++i) {
			ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
		}
		return ans;
	}
}
