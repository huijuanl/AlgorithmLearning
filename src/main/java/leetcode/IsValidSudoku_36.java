package leetcode;

// https://leetcode-cn.com/problems/valid-sudoku/
// 我的思路：
// int[81] row 存储某一行是否出现了某个值i
// 如何判断值i是否已经存在于行j中：row[(i -1)* 9 + j] == 0|1, 1表示存在，0表示不存在
// 如何判断值i是否已经存在于列j中: col[(i -1)* 9 + j] == 0|1
// sudoku[81], 0-9表示第一个九宫格的范围
// 如何判断某个位置(i,j)处的值k是否已经存在于某个九宫格中sudoku[(i/3 * 3 + j/3) * 9 + k + 1] == 0 | 1
// 时间复杂度O(1)，空间复杂度O(1)
public class IsValidSudoku_36 {
	public boolean isValidSudoku(char[][] board) {
		int[] row = new int[81];
		int[] col = new int[81];
		int[] sudoku = new int[81];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') continue;
				int k = board[i][j] - '0';
				System.out.println("k=" + k + ", i=" + i + ", j=" + j);
				if (row[(k -1)* 9 + i] == 1 || col[(k -1)* 9 + j] == 1 || sudoku[(i/3 * 3 + j/3) * 9 + k - 1] == 1) {
					return false;
				}
				row[(k -1)* 9 + i] = 1;
				col[(k -1)* 9 + j] = 1;
				sudoku[(i/3 * 3 + j/3) * 9 + k - 1] = 1;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		String[][] input = new String[][]{
				{"5", "3", ".", ".", "7", ".", ".", ".", "."},
				{"6", ".", ".", "1", "9", "5", ".", ".", "."},
				{".", "9", "8", ".", ".", ".", ".", "6", "."},
				{"8", ".", ".", ".", "6", ".", ".", ".", "3"},
				{"4", ".", ".", "8", ".", "3", ".", ".", "1"},
				{"7", ".", ".", ".", "2", ".", ".", ".", "6"},
				{".", "6", ".", ".", ".", ".", "2", "8", "."},
				{".", ".", ".", "4", "1", "9", ".", ".", "5"},
				{".", ".", ".", ".", "8", ".", ".", "7", "9"}
		};

	    char[][] board = new char[9][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = input[i][j].toCharArray()[0];
			}
		}

		boolean res = new IsValidSudoku_36().isValidSudoku(board);
		System.out.println(res);
	}
}
