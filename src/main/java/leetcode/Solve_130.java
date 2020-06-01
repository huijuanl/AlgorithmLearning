package leetcode;

// https://leetcode-cn.com/problems/surrounded-regions/
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
// 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/surrounded-regions


public class Solve_130 {

	// 我的思路：任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'（这句话很重要）
	// 因此我的思路就是从边界开始遍历，标记所有与边界'O'相连的'O'，那么标记完毕之后剩下的O就是需要变为X的
	public void solve(char[][] board) {

		int m = board.length;
		if (m == 0) return;
		int n = board[0].length;
		for(int j = 0; j < n; j++) {
			search(board, 0, j);
			if (m > 1){
				search(board, m - 1, j);
			}
		}

		for(int i = 0; i < m; i++) {
			search(board, i, 0);
			if (n > 1){
				search(board, i, n - 1);
			}
		}

		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'M') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}

			}
		}
	}

	public void search(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;

		if (board[i][j] != 'O') return;

		board[i][j] = 'M';
		search(board, i + 1, j);
		search(board, i, j - 1);
		search(board, i, j + 1);
		search(board, i - 1, j);
	}

	public static void main(String[] args) {
		char[][] board = new char[][]{
				{'X','X', 'X', 'X'},
				{'X', 'O', 'O', 'X'},
				{'X', 'X', 'O', 'X'},
				{'X', 'O', 'X', 'X'},
		};
		new Solve_130().solve(board);

		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + ",");
			}
			System.out.println();
		}
	}
}
