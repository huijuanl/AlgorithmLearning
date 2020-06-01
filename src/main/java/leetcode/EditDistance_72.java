package leetcode;

// https://leetcode.com/problems/edit-distance/
public class EditDistance_72 {

	// 二维数组动态规划:
	// D[i][j]表示word1的前i个字母到word2的前i个字母的编辑距离
	// https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/
	public int minDistance(String word1, String word2) {
		if (word1.equals("") || word2.equals("")) {
			return Math.max(word1.length(), word2.length());
		}
		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); i++) {
			for (int j = 0; j <= word2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 0;
				} else if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				}
				else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
							dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));

				}
			}
		}
		return dp[word1.length()][word2.length()];
	}

	public static void main(String[] args) {
		String word1 = "horse";
		String word2 = "ros";
		int res = new EditDistance_72().minDistance(word1, word2);
		System.out.println(res);
	}
}
