package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

// https://leetcode.com/problems/add-binary/
// 我的思路(击败了100%)
// 最好不要用stack，只击败35%
// 这里需要注意的使char类型-'0'
public class AddBinary_67 {
	public String addBinary(String a, String b) {

		int i = a.length() - 1;
		int j = b.length() - 1;
		int plus = 0;
		StringBuffer buffer = new StringBuffer();
		while (i >= 0 && j >=0) {
			int tmp = a.charAt(i) - '0' + b.charAt(j) - '0' + plus;
			int res = tmp % 2;
			buffer.append(res);
			plus = tmp / 2;
			i--;
			j--;
		}
		while (i >= 0) {
			int tmp = (a.charAt(i) - '0' + plus);
			int res = tmp % 2;
			buffer.append(res);
			plus = tmp / 2;
			i--;
		}
		while (j >= 0) {
			int tmp = b.charAt(j) - '0' + plus;
			int res = tmp % 2;
			buffer.append(res);
			plus = tmp / 2;
			j--;
		}

		if (plus == 1) {
			buffer.append(plus);
		}

		return buffer.reverse().toString();
	}

	public static void main(String [] args) {
		String a = "12384";
		String b = "998344";
		String res = new AddBinary_67().addBinary(a, b);
		System.out.print(res);
	}

}
