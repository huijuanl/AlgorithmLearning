package leetcode;

// https://leetcode.com/problems/set-matrix-zeroes/submissions/
// 我的思路: 新建一个长度为 m + n 的boolean类型 res数组，用来保存第i行或者第i列是否要都设置为0
// （如果为true的话表示设置为0）
// 第一次遍历原矩阵，对res进行更新;第二次遍历原矩阵，根据res进行修改原矩阵满足条件的元素为0
// 时间复杂度: O(mn),
// 空间复杂度: O(m + n)

public class SetZeroes {
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
	    boolean[] res = new boolean[m + n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					res[i] = true;
					res[m + j] = true;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (res[i] || res[m + j]) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	// 我优化之后：时间复杂度为O(mn), 空间复杂度为O(1)
	// 不用res存某一行或者某一列是否为0，而是直接基于matrix进行保存0的信息
	// 用第一行和第一列来存储行为0 还是列为0 的信息，
	// matrix[i][0]表示第i行为0(i >=0); matrix[0][j]表示第j列为0(j > 0)
	// 特殊处理第0列: 新建一个变量column0=true表示第0列为0
    // 关键思想: 用matrix第一行和第一列记录该行该列是否有0,作为标志位
	public void setZeroesOpt(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		boolean column0 = false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					if (j == 0) {
						column0 = true;
					} else  matrix[0][j] = 0;
				}

			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		if (matrix[0][0] == 0) {
			for (int j = 1; j < n; j++) {
				matrix[0][j] = 0;
			}
		}

		if (column0) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1,1,1}, {0,1,2}};
		new SetZeroes().setZeroesOpt(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println();
		}
	}
}
