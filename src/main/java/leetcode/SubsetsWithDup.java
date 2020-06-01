package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode-cn.com/problems/subsets-ii/
// nums数组中可能包含重复的元素；输出的所有子集需要时不重复的（去重）
public class SubsetsWithDup {
	List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<Integer> set = new ArrayList<>();
		Arrays.sort(nums);
		search(nums, 0, set);
		return res;
	}

	public void search(int[] nums, int cur, List<Integer> set) {
		if (cur > nums.length) return;
		if (cur == nums.length) {
			List<Integer> tmp = new ArrayList<>(set);
			res.add(tmp);
			return;
		}

		int end = cur + 1;
		while (end < nums.length && nums[end] == nums[cur]) {
			end++;
		}
		int maxNum = end - cur;
		for (int i = 1; i <= maxNum; i++) {
			for (int k = 1; k <= i; k++) {
				set.add(nums[cur]);
			}
			search(nums, end, set);
			for (int k = 1; k <= i; k++) {
				set.remove(set.size() - 1);
			}
		}
		search(nums, end, set);
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,2};
		List<List<Integer>> res = new SubsetsWithDup().subsetsWithDup(nums);
		System.out.println("size of res is: " + res.size());
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.println(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
