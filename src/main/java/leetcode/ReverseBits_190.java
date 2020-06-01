package leetcode;

// https://leetcode.com/problems/reverse-bits/
public class ReverseBits_190 {

	// 我的思路: 击败了19%
	public int reverseBits(int n) {
		int[] array = new int[32];
		boolean isNegative = n < 0;
		int unsignedN = Math.abs(n);
		int plus = 1;
		for (int i = 0; i < 32; i++) {
			int m = unsignedN % 2;
			unsignedN = unsignedN / 2;
			array[i] = m;
			if (isNegative) {
				if (i == 31) {
					array[i] = 1;
				} else {
					array[i] = array[i] == 1 ? 0 : 1;
					int res = (array[i] + plus) % 2;
					plus = (array[i] + plus) / 2;
					array[i] = res;
				}
			}
		}

		int res = 0;
		for (int i = 31; i >= 0; i--) {
			res += array[i] << (31 - i);
		}
		return res;
	}

	// leetcode上的思路:
	public int reverseBitsOpt(int n) {
		int a=0;
		for(int i=0; i<=31; i++){
			a=a+((1&(n>>i))<<(31-i));
		}
		return a;
	}
	public static void main(String[] args) {
		int n = -3;
		int res = new ReverseBits_190().reverseBits(n);
		System.out.println(Integer.toUnsignedLong(res));
	}
}
