package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/subsets/
// 递归思想(回溯法)
// 时间复杂度：O(N*2^N)
// 空间复杂度: O(N*2^N)
// 给定的输入数组中不包含重复元素
public class Subsets {
	List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> subsets(int[] nums) {
		List<Integer> set = new ArrayList<>();
		search(nums, 0, set);
		return res;
	}

	public void search(int[] nums, int i, List<Integer> set) {
		if (i > nums.length) return;
		if (i == nums.length) {
			List<Integer> tmp = new ArrayList<>(set);
			res.add(tmp);
			return;
		}

		set.add(nums[i]);
		search(nums, i + 1, set);
		set.remove(set.size() - 1);
		search(nums, i + 1, set);
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3};
		List<List<Integer>> res = new Subsets().subsets(nums);
		System.out.println("size of res is: " + res.size());
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.println(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
