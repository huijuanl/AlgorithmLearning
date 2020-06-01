package leetcode;

// https://leetcode.com/problems/power-of-three/
public class IsPowerOfThree_326 {

	// 递归模式
	public boolean isPowerOfThree(int n) {
		if (n <= 0) return false;
		if (n == 1) return true;
		int res = n % 3;
		if (res != 0) return false;
		return isPowerOfThree(n / 3);
	}

	// 非递归模式
	public boolean isPowerOfThree2(int n) {
		if (n <= 0) return false;
		if (n == 1) return true;

		while (n > 1) {
			if (n % 3 != 0) return false;
			n = n / 3;
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 27;
		boolean res = new IsPowerOfThree_326().isPowerOfThree2(n);
		System.out.print(res);
	}
}
