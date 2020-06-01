package leetcode;

// https://leetcode.com/problems/sudoku-solver/
public class SolveSudoku_37 {
	int[] row = new int[81];
	int[] col = new int[81];
	int[] sudoku = new int[81];

	// 我的思路：用到了回溯法
	public void solveSudoku(char[][] board) {
		// init
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') continue;
				int k = board[i][j] - '0';
				row[(k -1)* 9 + i] = 1;
				col[(k -1)* 9 + j] = 1;
				sudoku[(i/3 * 3 + j/3) * 9 + k - 1] = 1;
			}
		}
		search(board, 0, 0);
	}

	public boolean search(char[][] board, int i, int j) {
		if (board[i][j] != '.') {
			if (j == 8) {
				if (i < 8) return search(board, i + 1, 0);
				else return true;
			}
			else {
				return search(board, i, j + 1);
			}
		} else {
			boolean res = false;
			for (int k = 1; k <= 9; k++) {
				if (row[(k - 1) * 9 + i] == 0 &&
						col[(k - 1) * 9 + j] == 0 &&
						sudoku[(i / 3 * 3 + j / 3) * 9 + k - 1] == 0) {
					board[i][j] = (char) (k + '0');
					row[(k - 1) * 9 + i] = 1;
					col[(k - 1) * 9 + j] = 1;
					sudoku[(i / 3 * 3 + j / 3) * 9 + k - 1] = 1;
					if (j == 8) {
						if (i < 8) res = search(board, i + 1, 0);
						else return true;
					} else res = search(board, i, j + 1);
					if (!res) {
						board[i][j] = '.';
						row[(k - 1) * 9 + i] = 0;
						col[(k - 1) * 9 + j] = 0;
						sudoku[(i / 3 * 3 + j / 3) * 9 + k - 1] = 0;
					}
				}
			}
			return res;
		}
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

		new SolveSudoku_37().solveSudoku(board);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + ",");
			}
			System.out.println();
		}
	}
}
