package leetcode;

import java.util.Deque;
import java.util.PriorityQueue;

public class MedianFinder {

	PriorityQueue<Integer> minHeap = new PriorityQueue(); // 小根堆
	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a)); // 大根堆
	public MedianFinder() {

	}

	public void addNum(int num) {
		if (maxHeap.isEmpty()) {
			maxHeap.add(num);

		} else if (num <= maxHeap.peek()) {
			maxHeap.add(num);
		} else {
			minHeap.add(num);
		}
		adjustHeap();
	}

	public void adjustHeap() {
		if (maxHeap.size() > (minHeap.size() + 1)) {
			minHeap.add(maxHeap.poll());

		} else if (minHeap.size() > maxHeap.size()) {
			maxHeap.add(minHeap.poll());
		}
	}

	public double findMedian() {
		if (maxHeap.size() == minHeap.size() + 1) {
			return maxHeap.peek().doubleValue();
		} else {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		}

	}
}
