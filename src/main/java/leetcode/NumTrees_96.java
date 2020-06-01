package leetcode;

// https://leetcode.com/problems/unique-binary-search-trees/

// G(n) = ∑ G(k)G(n-k-1)
// 推导见:https://blog.csdn.net/baidu_38815750/article/details/84757351
// 有一个重要的假设: 当前节点的左子树都小于当前节点，右子树都大于当前节点，这样能确保不会有重复
public class NumTrees_96 {
	public int numTrees(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < n; j++) {
				dp[i]+=dp[j]*dp[n- j - 1];
			}
		}
		return dp[n];
	}
}
