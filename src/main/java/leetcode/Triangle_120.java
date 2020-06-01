package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 用动态规划
// 我的思路： 自顶向下，二维数组
// leetcode思路：自底向上，一维数组
public class Triangle_120 {
	public int minimumTotal(List<List<Integer>> triangle) {
		int levelNum = triangle.size();
		if (levelNum == 0) return 0;
		int[] minSum = new int[(levelNum+1)*levelNum/2];
		minSum[0] = triangle.get(0).get(0);
		int minMum = minSum[0];
		for (int i = 1; i < levelNum; i++) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				int cur = i*(i+1)/2 + j;
				int pre = (i-1)*i/2 + j;
				int prePre = pre - 1;
				if (j == 0) {
					minSum[cur] = minSum[pre] + triangle.get(i).get(j);
				} else if (j == triangle.get(i).size() - 1) {
					minSum[cur] = minSum[prePre] + triangle.get(i).get(j);
				} else {
					minSum[cur] = Math.min(minSum[pre],
							minSum[prePre]) + triangle.get(i).get(j);
				}
				if (i == levelNum - 1) {
					if (j == 0) {
						minMum = minSum[cur];
					} else {
						minMum = Math.min(minSum[cur], minMum);
					}
				}
			}
		}
		return minMum;
	}

	//
	public int minimumTotalOpt(List<List<Integer>> triangle) {
		int levelNum = triangle.size();
		if (levelNum == 0) return 0;
		int[] minSum = new int[levelNum];
		for (int i = levelNum - 1; i >= 0; i--) {
			for (int j = 0; j < i + 1; j++) {
				if (j == levelNum - 1) {
					minSum[j] = triangle.get(i).get(j);
				} else {
					minSum[j] = Math.min(minSum[j], minSum[j + 1]) + triangle.get(i).get(j);
				}
			}
		}
		return minSum[0];
	}

	public static void main(String[] args) {
		List list1= Arrays.asList(2);
		List list2= Arrays.asList(3,4);
		List list3= Arrays.asList(6,5,7);
		List list4= Arrays.asList(4,1,8,3);
		List<List<Integer>> list = new ArrayList();
//				List list1= Arrays.asList(-1);
//		List list2= Arrays.asList(-2,-3);
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		int minMum = new Triangle_120().minimumTotalOpt(list);
		System.out.println(minMum);
	}
}
