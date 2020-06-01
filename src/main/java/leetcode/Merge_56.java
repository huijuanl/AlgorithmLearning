package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
// 根据leetcode上的思路：先按照左区间排序，然后依次进行合并即可
public class Merge_56 {
	public int[][] merge(int[][] intervals) {
		List<int[]> res = new ArrayList<>();
		if (intervals == null || intervals.length == 0) return res.toArray(new int[0][]);
		// 对起点终点进行排序
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 若a < b，则返回负数
		int k = 0;
		res.add(intervals[0]);
		int start1 = res.get(k)[0];
		int end1 = res.get(k)[1];
		for (int i = 1; i < intervals.length; i++) {
			int[] tmp = intervals[i];
			int start2 = tmp[0];
			int end2 = tmp[1];
			if (start2 > end1) {
				res.add(tmp);
				k++;
				start1 = start2;
				end1 = end2;
			} else {
				end1 = Math.max(end1, end2);
				res.set(k, new int[]{start1, end1});
			}
		}
		return res.toArray(new int[0][]);
	}

	public static void main(String[] args) {
		int[][] input = new int[][]{{1,3},{2,6},{8,10},{15,18}};
		int[][] res = new Merge_56().merge(input);
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + ",");
			}
			System.out.println();
		}

	}
}
