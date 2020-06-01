package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/insert-interval/
// 这个题需要注意的是边界条件
public class Insert_57 {

	public int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> res = new ArrayList<>();
		if (intervals == null || intervals.length == 0) {
			res.add(newInterval);
			return res.toArray(new int[0][]);
		}
		int left = newInterval[0];
		int right = newInterval[1];

		// 找到一个start开始需要合并的区间，找到一个end（结束合并的区间），然后套用56题答案即可
		int k = -1;
		for (int i = 0; i < intervals.length; i++) {
			int start1 = intervals[i][0];
			int end1 = intervals[i][1];
			if (i == 0 && left < start1) {
				res.add(newInterval);
				k++;
				if (start1 > right) {
					res.add(intervals[i]);
					k++;
				} else res.set(k, new int[]{left, Math.max(right, end1)});
			} else {
				if (start1 <= left && end1 >= left) {
					res.add(new int[]{start1, Math.max(right, end1)});
					k++;
				} else if (end1 < left) {
					if (res.size() == 0 || start1 > res.get(k)[1]) {
						res.add(intervals[i]);
						k++;
					} else {
						res.set(k, new int[]{res.get(k)[0], Math.max(res.get(k)[1], end1)});
					}
					if ((i < intervals.length - 1 && intervals[i + 1][0] > left) ||
							(i == intervals.length - 1)) {
						res.add(newInterval);
						k++;
					}
				} else {
					if (start1> res.get(k)[1]) {
						res.add(intervals[i]);
						k++;
					} else {
						res.set(k, new int[]{res.get(k)[0], Math.max(res.get(k)[1], end1)});
					}
				}

			}
		}
		return res.toArray(new int[0][]);
	}

	public static void main(String[] args) {
//		int[][] input = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
//		int[] interval = new int[]{4, 8};
		int[][] input = new int[][]{{0,5},{9,12}};
		int[] interval = new int[]{7,16};
//		int[][] input = new int[][]{{1,5}, {6,8}};
//		int[] interval = new int[]{0,9};
		int[][] res = new Insert_57().insert(input, interval);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + ",");
			}
			System.out.println();
		}

	}
}
