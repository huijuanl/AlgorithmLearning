package leetcode;

import java.util.Stack;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParentheses_32 {

	// 我的思路：二维数组动态规划，OOM了
	public int longestValidParentheses(String s) {
		int maxLen = 0;
		int[][] arr = new int[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				continue;
			}
 			for (int j = i; j < s.length(); j++) {
				if (i == j) {
					arr[i][j] = s.charAt(i) == '('?1: -1;
				} else {
					arr[i][j] = (s.charAt(j) == '('?1: -1) + arr[i][j - 1];
				}
				if (arr[i][j] < 0) {
					break;
				} else if (arr[i][j] == 0) {
					maxLen = Math.max(maxLen, j - i + 1);
				}
 			}
		}
		return maxLen;
	}

	// 关于方法三，我用人话来解释。
	//1、First of All，栈底永远保存着当前有效子串的前一个字符的下标，就像个守门员一样守在那里，所以一开始要将-1放入栈中。
	//2、遇到左括号就入栈；
	//3、遇到右括号就将栈顶元素出栈。此时有两种情况：
	//（1）如果栈顶元素出栈后，栈内剩下的元素不为空，则说明弹出的这个栈顶元素一定是左括号，讲真，因为栈底有保险。
	//（2）如果栈顶元素出栈后，栈内为空，则说明刚刚弹出的这个栈顶元素就是之前的“有效子串前一位的字符下标”，守门员都没了，所以此时应该使用当前的右括号的下标入栈，更新这个“有效子串前一位的字符下标”。
	public int longestValidParenthesesOpt(String s) {
		int maxans = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.empty()) {
					stack.push(i); // 有效子串前一位的字符下标
				} else {
					maxans = Math.max(maxans, i - stack.peek());
				}
			}
		}
		return maxans;
	}

	// leetcode上动态规划，一维数组
	// 其中第 i 个元素表示以下标为 i 的字符结尾的最长有效子字符串的长度
	// 参考见：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
	public int longestValidParenthesesDp(String s) {
		int maxans = 0;
		int dp[] = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}

	public static void main(String[] args) {
		String s = "))()()";
		int maxLen = new LongestValidParentheses_32().longestValidParenthesesOpt(s);
		System.out.println(maxLen);
	}
}
