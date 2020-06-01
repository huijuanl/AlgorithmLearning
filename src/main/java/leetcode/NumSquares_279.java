package leetcode;

// https://leetcode.com/problems/perfect-squares/
public class NumSquares_279 {

	// 我的思路：用到了动态规划
	// dp[i]表示数i能由完全平方数组成的个数
	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = i;
		}
		for (int i = 0; i <= n; i++) {
			for (int k = 1; k * k <= i; k++) {
				if (dp[i] == 1) break;
				dp[i] = Math.min(dp[i - k * k] + 1, dp[i]);
			}

		}

		return dp[n];
	}

	public static void main(String[] args) {
		int n = 4;
		int res = new NumSquares_279().numSquares(n);
		System.out.println(res);
	}
}
