package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/implement-trie-prefix-tree/
// Trie树的应用 (下面这种设计只击败了6.5%的其他用户)
// 官方答案: https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/

class TrieNode { // 每个节点最多有26个不同的小写字母
	public boolean isEnd;
	public TrieNode[] next;

	public TrieNode() {
		isEnd = false;
		next = new TrieNode[26];
	}

}

class Trie {
	public TrieNode root;

	/** Initialize your data structure here. */
	public Trie() {
		root = new TrieNode();
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		TrieNode cur = root;
		for (int i = 0, len = word.length(), ch; i < len; i++) {
			ch = word.charAt(i) - 'a';
			if (cur.next[ch] == null)
				cur.next[ch] = new TrieNode();
			cur = cur.next[ch];
		}
		cur.isEnd = true; // 加上一个标记，表示为一个单词
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode cur = root;
		for (int i = 0, len = word.length(), ch; i < len; i++) {
			ch = word.charAt(i) - 'a';
			if (cur.next[ch] == null)
				return false;
			cur = cur.next[ch];
		}
		return cur.isEnd;
	}

	/**
	 * Returns if there is any word in the trie that starts with the given prefix.
	 */
	public boolean startsWith(String prefix) {
		TrieNode cur = root;
		for (int i = 0, len = prefix.length(), ch; i < len; i++) {
			ch = prefix.charAt(i) - 'a';
			if (cur.next[ch] == null)
				return false; // 若还没遍历完给定的前缀子串，则直接返回false
			cur = cur.next[ch];
		}
		return true; // 直接返回true
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		boolean res = trie.search("apple");   // returns true
		System.out.println(res);
		res = trie.search("app");     // returns false
		System.out.println(res);
		res = trie.startsWith("app"); // returns true
		System.out.println(res);
		trie.insert("app");
		res = trie.search("app");     // returns true
		System.out.println(res);
	}
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */