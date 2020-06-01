package leetcode;

// https://leetcode.com/problems/interleaving-string/
public class IsInterleave_97 {

	// 我的思路：用到了动态规划，
	// dp[i][j]表示s1中长度为i的子字符串和s2中长度为j的子字符串是否能交叉构成i+j的s3的子字符串（子字符串均以0开头）
	public boolean isInterleave(String s1, String s2, String s3) {

		// dp[i][j]
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		if (s3.length() != (s1.length() + s2.length())) return false;

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 && j ==0) {
					dp[0][0] = true;
				} else if (i == 0) {
					dp[0][j] = dp[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1);
				} else if (j == 0) {
					dp[i][0] = dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
				} else {
					dp[i][j] = dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1);
					dp[i][j] = dp[i][j] || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
				}
			}
		}

		return dp[dp.length - 1][dp[0].length - 1];
	}

	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
//		String s1 = "db";
//		String s2 = "b";
//		String s3 = "cbb";
		boolean res = new IsInterleave_97().isInterleave(s1, s2, s3);
		System.out.println(res);



	}
}

