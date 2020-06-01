package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combinations/
// 我的思路：用递归的思想，是否加上当前值
// 如果用动态规划的话，需要用二维数组的动态规划
public class Combine_77 {
	List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combine(int n, int k) {
		if (n < k) return res;
		combine(n, k, 1, new ArrayList<>());
		return res;
	}

	public void combine(int n, int k, int i, List<Integer> list) {
		if (list.size() == k) {
			res.add(new ArrayList<>(list));
			return;
		}
		if (i > n || list.size() > k) {
			return;
		}
		combine(n, k, i + 1, list);
		list.add(i);
		combine(n, k, i + 1, list);
		list.remove(list.size() - 1);
	}

	public static void main(String[] args) {
		int n = 1;
		int k = 2;
		List<List<Integer>> res = new Combine_77().combine(n, k);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + ",");
			}
			System.out.println();
		}


	}
}
