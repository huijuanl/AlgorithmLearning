package leetcode;

// https://leetcode.com/problems/combination-sum/
import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		combination(candidates, target, 0, new ArrayList<>());
		return res;

	}

	public void combination(
			int[] candidates, int target, int cur, List<Integer> already) {
		if (target < 0) return;
		if (target == 0) {
			List<Integer> tmp = new ArrayList<>(already);
			res.add(tmp);
			return;
		}

		if (cur == candidates.length) return;
		int value = candidates[cur];
		int num = target / value;
		for (int i = 0; i <= num; i++) {
			if (i == 0) {
				combination(candidates, target, cur + 1, already);
			} else {
				for (int k = 0; k < i; k ++) {
					already.add(value);
				}
				int reduce = target - i * value;
				combination(candidates, reduce, cur + 1, already);
				for (int k = 0; k < i; k ++) {
					already.remove(already.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,6,7};
		int target = 7;
		List<List<Integer>> res = new CombinationSum().combinationSum(nums, target);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.println(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
