package leetcode;

// https://leetcode.com/problems/distinct-subsequences/
public class NumDistinct_115 {

	public int numDistinct(String s, String t) {
		if (s.length() == 0 || s.length() < t.length()) return 0;
		// dp[i][j]表示s以i结尾的字符串包含T以j结尾的字符串的子序列个数
		// 如果s[i] == s[j], dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
		// 如果s[i] != s[j], dp[i][j] = dp[i-1][j]
		int[][] dp = new int[s.length()][t.length()];

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				if (i > 0) {
					dp[i][j] = dp[i - 1][j];
				}
				if (s.charAt(i) == t.charAt(j)) {
					if (i > 0 && j > 0) {
						dp[i][j] += dp[i - 1][j - 1];
					} else if (j == 0) {
						dp[i][j] += 1;
					}
				}

			}

		}
		return dp[s.length() - 1][t.length() - 1];
	}

	public static void main(String[] args) {
		String s ="babgbag";
		String t = "bag";
		int res = new NumDistinct_115().numDistinct(s, t);
		System.out.println(res);
	}
}
