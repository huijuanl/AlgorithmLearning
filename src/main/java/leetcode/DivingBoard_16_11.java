package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// https://leetcode-cn.com/problems/diving-board-lcci/
public class DivingBoard_16_11 {
	//特殊情况1：k为0
	//特殊情况2：shorter和longer相等
	//其余：实际上就是几个shorter和几个longer相加的情况，k个shorter与0个longer，（k-1）个shorter和1个longer，.....，0个shorter和k个longer
	//协作式子就是(k-i)*shorter+i*longer
	List<Integer> list = new ArrayList<>();
	public int[] divingBoard(int shorter, int longer, int k) {
		if (k == 0) return new int[]{};
		if (shorter == longer) return new int[]{k};

		for(int i = k; i >= 0; i--) {
			list.add(shorter * i + longer * (k - i));
		}
		return list.stream().mapToInt(i -> i).toArray();
	}

	public static void main(String[] args) {
		int shorter = 1;
		int longer = 2;
		int k = 3;
		int res[] = new DivingBoard_16_11().divingBoard(shorter, longer, k);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + ",");
		}
	}



}
