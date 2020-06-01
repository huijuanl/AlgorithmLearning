package leetcode;

public class FindLength_718 {

	public int findLength(int[] A, int[] B) {
		// dp[i][j] 表示以A[i], B[j]结尾的最长公共子数组的长度
		int[][] dp =new int[A.length][B.length];
		int maxLen = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if (A[i] == B[j]) {
					if (i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
					maxLen = Math.max(maxLen, dp[i][j]);
				} else dp[i][j] = 0;
			}

		}
		return maxLen;
	}

	public static void main(String[] args) {
		int[] A = new int[]{1,2,3,2,1};
		int[] B = new int[]{3,2,1,4,7};
		int res = new FindLength_718().findLength(A, B);
		System.out.println(res);
	}
}
