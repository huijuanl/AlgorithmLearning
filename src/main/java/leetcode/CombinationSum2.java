package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/
public class CombinationSum2 {
	List<List<Integer>> res = new ArrayList<>();
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
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
		if (cur >= candidates.length) return;
		int value = candidates[cur];

		int nextCur = cur + 1;
	    while (nextCur < candidates.length) {
	    	if (candidates[nextCur] > candidates[cur])
	    		break;
	    	else nextCur++;
		}
		int nums = nextCur - cur;
		for (int i = 0; i <= nums; i++) {
			if (i == 0) {
				combination(candidates, target, nextCur, already);
			} else {
				for (int k = 0; k < i; k++) {
					already.add(value);
				}
				combination(candidates, target - i * value, nextCur, already);
				for (int k = 0; k < i; k++) {
					already.remove(already.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{2,5,2,1,2};
		int target = 5;
		List<List<Integer>> res = new CombinationSum2().combinationSum2(nums, target);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.println(res.get(i).get(j) + ",");
			}
			System.out.println();

		}

	}
}
