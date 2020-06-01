package leetcode;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode-cn.com/problems/daily-temperatures/
public class DailyTemperatures_739 {
	// 我只想到了暴力法，leetcode上运用了单调递减栈
	// 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
	// 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
	public int[] dailyTemperatures(int[] T) {
		int[] dp = new int[T.length];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < T.length; i++) {
			if (stack.isEmpty()) {
				stack.add(i);
			} else {
				while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
					int index = stack.pop();
					dp[index] = i - index;
				}
				stack.add(i);
			}
		}

		return dp;
	}
}
