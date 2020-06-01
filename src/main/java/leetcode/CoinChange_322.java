package leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/coin-change/
public class CoinChange_322 {
	public int coinChange(int[] coins, int amount) {
		// dp[i] = Math.min(dp[i - coins[k]], dp[i]) + 1
		// 我的思路：动态规划，dp[i]表示amount为i时的最小硬币组成个数
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int k = 0; k < coins.length; k++) {
				if (i - coins[k] >= 0 && dp[i - coins[k]] != -1) {
					dp[i] = dp[i] == -1? dp[i - coins[k]] + 1: Math.min(dp[i], dp[i - coins[k]] + 1);
				}

			}
		}
		return dp[dp.length - 1];
	}

	public static void main(String[] args) {
		int[] coins = new int[]{2};
		int amount = 3;
		int res = new CoinChange_322().coinChange(coins, amount);
		System.out.println(res);
	}
}
