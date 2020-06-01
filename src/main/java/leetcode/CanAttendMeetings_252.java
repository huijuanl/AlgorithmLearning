package leetcode;

import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms/
public class CanAttendMeetings_252 {
	// 我的思路：等价于判断是否有区间重叠
	// 先按照左端点排序，然后判断右端点值是否小于等于下一个区间的左端点值
	public boolean canAttendMeetings(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i][1] > intervals[i + 1][0]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
//		int[][] intervals = new int[][]{
//				{0,30},{5,10},{15,20}
//		};

		int[][] intervals = new int[][]{
				{7, 10},{2,4}
		};
		boolean res = new CanAttendMeetings_252().canAttendMeetings(intervals);
		System.out.println(res);
	}
}
