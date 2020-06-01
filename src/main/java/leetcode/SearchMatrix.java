package leetcode;

// https://leetcode.com/problems/search-a-2d-matrix/
// 我的思路：从右上角开始进行比较，比较matrix[i][j]与target的关系，并向左或向右滑动一步
// 重要的优化（提前结束循环）: if ( j >=1 && target > matrix[i][j -1]) {
//					return false;
//				}
// 二维有序数组中每一行从左到右有序，从上到下有序，且第i行的数都大于第i-1行 （因此也可以将该二维数组用二分查找来实现）
public class SearchMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		if (m == 0) return false;
		int n = matrix[0].length;

		int i = 0;
		int j = n - 1;
		while (j >= 0 && i < m) {
			if (matrix[i][j] == target) {
				return true;
			} else if (target < matrix[i][j]) {
				if ( j >=1 && target > matrix[i][j -1]) {
					return false;
				}
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1, 3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
		int target = 3;
		boolean res = new SearchMatrix().searchMatrix(matrix, target);
		System.out.println(res);
	}
}
