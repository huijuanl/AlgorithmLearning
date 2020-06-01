package leetcode;

public class IsPowerOfFour {

	// n的比特位上只有一个1，且n-1的bit位上1的个数位偶数
	public boolean isPowerOfFour(int num) {
		if (num <= 0 || (num & (num - 1)) != 0) return false;

		int k = num - 1;
		int count = 0;
		while (k != 0) {
			count += k & 1;
			k = k >> 1;
		}
		return count % 2 == 0;
	}

	// leetcode上给出的一个更简洁的做法:
	// https://leetcode-cn.com/problems/power-of-four/
	public boolean isPowerOfFourOpt(int num) {
		if (num <= 0 || (num & (num - 1)) != 0) return false;
		return num % 3 == 1;
	}

	public static void main(String[] args) {
		int n = 2;
		boolean res = new IsPowerOfFour().isPowerOfFour(n);
		System.out.print(res);
	}
}
