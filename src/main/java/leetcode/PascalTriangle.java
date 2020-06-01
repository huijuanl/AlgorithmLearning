package leetcode;

import java.util.ArrayList;
import java.util.List;

//
public class PascalTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if (numRows == 0) return res;
		List<Integer> low = new ArrayList<>();
		List<Integer> high = new ArrayList<>();
		low.add(1);
		res.add(low);
		for (int i = 1; i < numRows; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (j == 0) {
					high = new ArrayList<>();
					high.add(1);
				} else if (j == i) {
					high.add(1);
					res.add(high);
					low = high;
				} else {
					high.add(low.get(j - 1) + low.get(j));
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int row = 5;
		List<List<Integer>> res = new PascalTriangle().generate(row);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
