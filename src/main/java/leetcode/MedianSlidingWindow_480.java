package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

import heap.PriorityQueueExample;

// https://leetcode.com/problems/sliding-window-median/
public class MedianSlidingWindow_480 {

	// leetcode上的解法:
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Collections.reverseOrder())); // 最大堆，堆顶是最大的
	PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 默认为小根堆（堆顶是最小的）

	public double[] medianSlidingWindow(int[] nums, int k) {
		double[] result = new double[nums.length - k + 1];
		for (int i = 0; i < nums.length; i++) {
			if (maxHeap.size() == 0 || maxHeap.peek() >= nums[i]) {
				maxHeap.add(nums[i]);
			} else {
				minHeap.add(nums[i]);
			}
			balanceHeaps();

			if (i - k + 1 >= 0) {
				if (maxHeap.size() == minHeap.size()) {
					result[i - k + 1] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
				} else {
					result[i - k + 1] = maxHeap.peek();
				}

				int elementToBeRemoved = nums[i - k + 1];
				if (elementToBeRemoved <= maxHeap.peek()) {
					maxHeap.remove(elementToBeRemoved);
				} else {
					minHeap.remove(elementToBeRemoved);
				}
				balanceHeaps();
			}
		}
		return result;
	}

	private void balanceHeaps() {
		if (maxHeap.size() > minHeap.size() + 1)
			minHeap.add(maxHeap.poll());
		else if (maxHeap.size() < minHeap.size())
			maxHeap.add(minHeap.poll());
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
		int k = 3;
		double[] res = new MedianSlidingWindow_480().medianSlidingWindow(nums, k);
		for (int j = 0; j < res.length; j++) {
			System.out.print(res[j] + ",");
		}
	}
}
