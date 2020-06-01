package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/reverse-integer/
public class Reverse_7 {

	// 我的思路：击败了12.78%
	// 需要特殊处理翻转之后溢出的问题
	public int reverse(int x) {
		int unsignedN = Math.abs(x);
		int isNegative = x < 0 ? -1: 1;
		List<Integer> buffer = new ArrayList<>();
		while (unsignedN > 0) {
			int res = unsignedN % 10;
			if (res == 0 && buffer.size() > 0) {
				buffer.add(res);
			} else if (res !=0 ) {
				buffer.add(res);
			}
			unsignedN = unsignedN / 10;
		}
		long res = 0;
		for (int i = 0; i < buffer.size(); i++) {
			res = res * 10 + buffer.get(i);
		}

		res = res * isNegative;
		if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
			return 0;
		} else return (int) res;
	}

	// leetCode上更好的解法: if ((newRes - t) / 10 != res) 来判断是否会有数字溢出
	// https://leetcode-cn.com/problems/reverse-integer/solution/tu-wen-xiang-jie-javadai-ma-de-2chong-shi-xian-fan/
	public int reverseOpt(int x) {
		int res = 0;
		while (x != 0) {
			int t = x % 10;
			int newRes = res * 10 + t;
			//如果数字溢出，直接返回0
			if ((newRes - t) / 10 != res)
				return 0;
			res = newRes;
			x = x / 10;
		}
		return res;
	}

	public static void main(String[] args) {
		int input = 1534236469;
		int res = new Reverse_7().reverse(input);
		System.out.println(res);
	}

}
