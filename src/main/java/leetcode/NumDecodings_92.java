package leetcode;

public class NumDecodings_92 {

	// 我的思路：用到了动态规划思想
	// 注意0的特殊处理：如果100这种，是无效的
	public int numDecodings(String s) {
		int[] dp = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			int keyValue = s.charAt(i) - '0';
			if (i == 0) {
				dp[i] = keyValue > 0? 1: 0;
			} else {
				int preValue = s.charAt(i - 1) - '0';
				dp[i] = keyValue > 0? dp[i - 1]: 0;
				if (preValue == 1 || (preValue == 2 && keyValue >= 0 && keyValue <= 6)) {
					dp[i] += i >= 2? dp[i - 2]: 1;
				}
			}
		}
		return dp[s.length() - 1];
	}

	public static void main(String[] args) {
		String s = "10101";
		int res = new NumDecodings_92().numDecodings(s);
		System.out.print(res);
	}
}
