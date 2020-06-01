package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
public class FindKthLargest_215 {

	// 时间复杂度为O(n)
	public int findKthLargest(int[] nums, int k) {

		if (k > nums.length) return -1;
		return quikSort(nums, 0, nums.length - 1, k);

	}

	public int quikSort(int[] nums, int start, int end, int k) {
		if (start == end) return nums[start];
		int value = nums[start];
		int pre = start;
		for (int i = start + 1; i <= end; i++) {
			if (nums[i] >= value) {
				pre++;
				swap(nums, pre, i);
			}

		}

		if ((pre - start + 1) == k) return value;
		swap(nums, pre, start);
		if ((pre - start + 1) < k) {
			return quikSort(nums, pre + 1, end, k - pre + start - 1);
		} else return quikSort(nums, start, pre - 1, k);

	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// 适用于海量数据
	// 时间复杂度为O(nlogk)
	// 若堆中元素个数为k，那么插入和删除的时间复杂度为O(logk)
	public int findKthLargest2(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
		if (nums.length < k) return -1;
		int queueSize = 0;
		for (int i = 0; i < nums.length; i++) {
			if (queueSize < k) {
				queue.add(nums[i]);
				queueSize++;
			} else if (queueSize > 0 && queue.peek() < nums[i]){
				queue.poll();
				queue.add(nums[i]);
			}
		}
		return queue.peek();
	}

	public static void main(String[]args) {
		int[] num = new int[]{7,6,5,4,3,2,1};
		int k = 5;
		int res = new FindKthLargest_215().findKthLargest2(num, k);
		System.out.print(res);
	}
}
