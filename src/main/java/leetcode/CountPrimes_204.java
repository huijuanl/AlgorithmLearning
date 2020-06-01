package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/count-primes/
// 统计所有小于非负整数 n 的质数的数量。
public class CountPrimes_204 {
	public int countPrimes(int n) {
		int[]dp = new int[n + 1];
		dp[1] = 0;
		dp[2] = 1;
		for (int i = 3; i <= n; i++) {

			// 判断i-1是不是质数
			if ((i - 1) % 2 == 0) {
				dp[i] = dp[i - 1];
			} else {
				int count = 1;
				for (int k = 3; k <= i / 2; k++) {
					if ((i - 1) % k == 0) {
						count = 0;
						break;
					}
				}
				dp[i] = dp[i - 1] + count;
			}


		}
		return dp[n];
	}

	// leetcode上进行了优化，只需要遍历到sqrt(x)，否则会超时
	public int countPrimes2(int n) {
		if (n <= 1) return 0;
		if (n == 2) return 0;
		int count = n / 2;
		for (int i = 3; i < n; i+=2) {
			for (int k = 3; k * k <= i; k++) {
				if (i % k == 0) {
					count--;
					break;
				}
			}
		}
		return count;
	}

	// 最佳方法,效率最高
    // https://leetcode-cn.com/problems/count-primes/solution/ting-shuo-zhe-jiao-e-la-duo-sai-shai-fa-zhi-xing-y/
	public int countPrimesOpt(int n) {
		int result = 0;
		boolean[] b = new boolean[n];   // 初始化默认值都为 false，为质数标记
		if(2 < n) result++; // 如果大于 2 则一定拥有 2 这个质数

		for(int i = 3; i < n; i += 2){  // 从 3 开始遍历，且只遍历奇数
			if(!b[i]){  // 是质数
				for(int j = 3; i * j < n; j += 2){
					b[i * j] = true;    // 将当前质数的奇数倍都设置成非质数标记 true
				}
				result++;   // 质数个数 +1
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 499979;
		int res = new CountPrimes_204().countPrimesOpt(n);
		System.out.println(res);
	}
}
