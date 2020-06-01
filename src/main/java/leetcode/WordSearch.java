package leetcode;

// https://leetcode.com/problems/word-search/

// 我的思路:
// 以board[i][j]开头的是否是否是word，如果是的话则返回，否则遍历所有可能.
// 使用下标start而不是用buffer存储已经满足条件的前缀更好，提升性能
// start: 比较第start + 1个前缀，此前[0, start -1]已经符合start中的前缀
// 如果一个矩阵中涉及到上下左右都可能发生递归时，注意不要陷入死循环（
// 最好是固定一个起点，然后遍历这个矩阵所有的起点
// 判断从一个起点开始能否找到符合条件的word）
public class WordSearch {
	public boolean exist(char[][] board, String word) {
		int m = board.length;
		if (m == 0) return false;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < board.length; i ++) {
			for (int j = 0; j < board[0].length; j ++) {
				boolean res = search(board, word, i, j, 0, visited);
				if (res) return true;
			}
		}
		return false;
	}

	public boolean search(
			char[][] board, String word, int i, int j, int start, boolean[][] visited) {
		if (start == word.length()) {
			return true;
		}
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return false;

		if (word.charAt(start) == board[i][j]) {
			visited[i][j] = true;
			boolean res =
					search(board, word, i + 1, j, start + 1, visited) ||
					search(board, word, i - 1, j, start + 1, visited) ||
					search(board, word, i, j + 1, start + 1, visited) ||
					search(board, word, i, j - 1, start + 1, visited);
			visited[i][j] = false;
			return res;
		}
		return false;
	}

	public static void main(String[] args) {
		String word = "AAB";
//		StringBuffer buffer = new StringBuffer();
//		System.out.println(buffer.length());
		char[][] board = new char[][]
				{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
		boolean res = new WordSearch().exist(board, word);
		System.out.println(res);
	}
}