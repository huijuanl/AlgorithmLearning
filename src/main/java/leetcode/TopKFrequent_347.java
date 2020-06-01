package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequent_347 {
	// 前k个高频元素
	// 我的思路：
	// 第一种思路：先对原数组进行排序，然后遍历一遍即可获得前k个元素
	// 时间复杂度为O(NlogN)
	// 第二种思路：
	// map + 最小堆 时间复杂度为O(NlogK)，空间复杂度为O(N)
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		PriorityQueue<Integer> queue= new PriorityQueue(k, (a, b) -> (map.get(a) - map.get(b)));

		for (int key: map.keySet()) {
			if (queue.size() < k) {
				queue.add(key);
			} else {
				int top = queue.peek();
				if (map.get(top) < map.get(key)) {
					queue.poll();
					queue.add(key);
				}
			}
		}

		return queue.stream().sorted((a, b) -> (map.get(b) - map.get(a))).mapToInt(a -> a).toArray();
		// Arrays.sort(res, (a, b) -> (map.get(b) - map.get(a))这种写法只有在res为对象数组时才可以，基本类型数组不可以
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,1,1,2,2,3, 3,3,3,3};
		int k = 2;
		int[] res = new TopKFrequent_347().topKFrequent(nums, k);
		for(int each: res) {
			System.out.print(each + ",");
		}
	}

}
