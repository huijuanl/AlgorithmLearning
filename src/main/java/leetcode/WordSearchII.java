package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 思路一： 根据前面做的WordSearch问题，对每一个word都去查找是否满足条件
// 时间复杂度较高
// 思路二：
// 时间复杂度仍然较高（只击败了16%）
// leetcode上给出的解法是根据words建立一个Trie树(下面是根据leetcode的提示做的实现)
public class WordSearchII {
	HashSet resSet = new HashSet<String>();
	public List<String> findWords(char[][] board, String[] words) {
		int m = board.length;
		if (m == 0) return new ArrayList<String>();
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		///
		Trie trie = new Trie();
		for (int i = 0; i < words.length; i++) {
			trie.insert(words[i]);
		}

		for (int i = 0; i < board.length; i ++) {
			for (int j = 0; j < board[0].length; j ++) {
				search(board, trie.root, i, j, 0, visited, new StringBuffer());
			}
		}
		return new ArrayList<String>(resSet);
	}

	public void search(
			char[][] board, TrieNode trieNode, int i, int j, int start, boolean[][] visited, StringBuffer buffer) {
		if (trieNode.isEnd && buffer.length() > 0) {
			resSet.add(buffer.toString());
		}
		if (start < 0 || i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return;
		TrieNode next = trieNode.next[board[i][j] - 'a'];

		if (next != null) {
			buffer.append(board[i][j]);
			visited[i][j] = true;
			search(board, next, i + 1, j, start + 1, visited, buffer);
			search(board, next, i - 1, j, start + 1, visited, buffer);
			search(board, next, i, j + 1, start + 1, visited, buffer);
			search(board, next, i, j - 1, start + 1, visited, buffer);
			visited[i][j] = false;
			buffer.deleteCharAt(buffer.length() - 1);
		}
	}

	public static void main(String[] args) {
		char[][] board = new char[][]{
				{'o','a','a','n'},
				{'e','t','a','e'},
				{'i','h','k','r'},
				{'i','f','l','v'}};
		String[] words = new String[]{"a"};
		List<String> res = new WordSearchII().findWords(board, words);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}

	}
}
