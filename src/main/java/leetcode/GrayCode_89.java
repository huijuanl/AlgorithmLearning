package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/gray-code/
public class GrayCode_89 {

	// 用递归的思想:
	// n-1位的结果赋值一份逆序到末尾，然后前半个长度都补0，后半个长度都补1 (后半个长度的首位都补1相当于加上了 1<< (n - 1))
	public List<Integer> grayCode(int n) {
		if (n == 0) {
			List<Integer> res = new ArrayList<>();
			res.add(0);
			return res;
		}
		int num = 1 << (n - 1);

		List<Integer> subRes = grayCode(n - 1);
		int size = subRes.size();
		for (int i = 0; i < size; i++) {
			subRes.add(subRes.get(size - i - 1) + num);
		}
		return subRes;
	}

	public static void main(String[] args) {
		int n = 3;
		List<Integer> res = new GrayCode_89().grayCode(n);
		for (int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i) + ",");
		}
	}
}
