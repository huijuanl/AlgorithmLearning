package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/meeting-rooms-ii/
public class MinMeetingRooms_253 {

	// leetcode上的解法：最小堆，存储每个房间的最后会议结束时间
	// 先按照开始时间进行排序
	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		for (int i = 0; i < intervals.length; i++) {
			if (queue.isEmpty()) {
				queue.add(intervals[i][1]);
			} else {
				int top = queue.peek();
				if (intervals[i][0] < top) {
					queue.add(intervals[i][1]);
				} else {
					queue.poll();
					queue.add(intervals[i][1]);
				}
			}
		}
		return queue.size();
	}

	public static void main(String[] args) {
		int[][] intervals = new int[][]{
				{0, 30},{5, 10},{15, 20}
		};
		int res = new MinMeetingRooms_253().minMeetingRooms(intervals);
		System.out.println(res);
	}

}
