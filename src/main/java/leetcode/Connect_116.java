package leetcode;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// 填充每个节点的右侧指针

public class Connect_116 {
	class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	};
	// 我的思路：层序遍历
	// 这种方法用到了栈，因此空间复杂度不满足O(1)的要求
	public Node connect(Node root) {
		if (root == null) return null;
		Queue<Node> queue = new LinkedList();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node top = queue.poll();
				if (i == size - 1) {
					top.next = null;
				} else {
					top.next = queue.peek();
				}
				if (top.left != null) {
					queue.add(top.left);
					queue.add(top.right);
				}
			}


		}
		return root;
	}

	// leetcode上的解法:新的一种遍历方式
    // 演示地址：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/dong-hua-yan-shi-san-chong-shi-xian-116-tian-chong/
	public Node connectOpt(Node root) {
		if (root == null) return null;
		connect2(root);
		return root;
	}
	public void connect2(Node root) {
		if (root == null) return;
		Node left = root.left;
		Node right = root.right;
		while (left != null) {
			left.next = right;
			left = left.right;
			right = right.left;
		}
		connect2(root.left);
		connect2(root.right);
	}
}
