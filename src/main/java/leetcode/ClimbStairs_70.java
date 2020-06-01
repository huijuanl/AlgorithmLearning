package leetcode;

// https://leetcode.com/problems/climbing-stairs/
// f(n) = f(n - 1) + f(n - 2)
public class ClimbStairs_70 {
	public int climbStairs(int n) {
		if (n <= 0) return 0;
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 2;
		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n - 1];
	}

	public static void main(String[] args) {
		System.out.println(new ClimbStairs_70().climbStairs(1));
	}
}
