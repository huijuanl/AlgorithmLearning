package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/pascals-triangle-ii/
// 用动态规划来实现，（O(k)复杂度）
public class PascalTriangleII {
	public List<Integer> getRow(int rowIndex) {
		int[] res = new int[rowIndex + 1];
		res[0] = 1;
		for (int i = 1; i < res.length; i++) {
			for (int j = i; j >=0; j--) {
				if (j == i || j == 0) {
					res[j] = 1;
				} else {
					res[j] = res[j - 1] + res[j];
				}
			}
		}

		return Arrays.stream(res).boxed().collect(Collectors.toList());

	}
	public static void main(String[] args) {
		int row = 1;
		List<Integer> res = new PascalTriangleII().getRow(row);
		for (int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i) + ",");
		}
	}
}
