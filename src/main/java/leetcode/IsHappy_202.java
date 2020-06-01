package leetcode;

import java.util.HashMap;
import java.util.HashSet;

// https://leetcode.com/problems/happy-number/
public class IsHappy_202 {
	public boolean isHappy(int n) {
		HashSet set = new HashSet();
		while (n != 1 ) {
			set.add(n);
			int res = 0;
			while (n > 0) {
				res += (n % 10) * (n % 10);
				n = n / 10;
			}
			n = res;
			if (set.contains(n)) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int n = 2;
		boolean res = new IsHappy_202().isHappy(n);
		System.out.println(res);
	}
}
