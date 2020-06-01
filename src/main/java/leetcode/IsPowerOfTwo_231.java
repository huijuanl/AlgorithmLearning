package leetcode;

// https://leetcode.com/problems/power-of-two/
// 给定一个数n，判断是否是2的幂
public class IsPowerOfTwo_231 {
	// 我的思路：bit位检查，1只出现了一次，击败了100%
	public boolean isPowerOfTwo(int n) {
		if (n <= 0) return false;
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n = n >> 1;
			if (count > 1) return false;
		}
		return true;
	}

	// 我的另一种思路： n & (n - 1) == 0，那么n就是2的幂
	public boolean isPowerOfTwo2(int n) {
		if (n <= 0) return false;
		return (n & (n - 1)) == 0;
	}

	public static void main(String[] args) {
		int n = 218;
		boolean res = new IsPowerOfTwo_231().isPowerOfTwo(n);
		System.out.print(res);
	}
}
