package leetcode;

// https://leetcode.com/problems/search-a-2d-matrix/
// 我的思路：从右上角开始进行比较，比较matrix[i][j]与target的关系，并向左或向右滑动一步
// 这段提前结束的逻辑不适用： if ( j >=1 && target > matrix[i][j -1]) {
//					return false;
//				}
// 二维有序数组中每一行从左到右有序，从上到下有序，但是第i行的数不一定都大于第i-1行
public class SearchMatrixII {
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
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1,   4,  7, 11, 15},
				{2,   5,  8, 12, 19},
				{3,   6,  9, 16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}};
		int target = 3;
		boolean res = new SearchMatrixII().searchMatrix(matrix, target);
		System.out.println(res);
	}
}
