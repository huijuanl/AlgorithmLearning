package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

// https://leetcode.com/problems/plus-one/
public class PlusOne {
	public int[] plusOne(int[] digits) {
		boolean plus = false;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = digits.length - 1; i >= 0; i--) {
			if (i == digits.length - 1 || plus) {
				plus = (digits[i] + 1) / 10 == 1;
				digits[i] = (digits[i] + 1) % 10;
			}
		}

		if (plus) {
			int[] res = new int[digits.length + 1];
			res[0] = 1;
			return res;
		} else {
			return digits;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{9,9};
		int[] res = new PlusOne().plusOne(nums);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + ",");
		}
	}
}
