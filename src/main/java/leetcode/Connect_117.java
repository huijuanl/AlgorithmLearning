package leetcode;

public class Connect_117 {

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
	}

	public Node connect(Node root) {
		connect2(root);
		return root;
	}

	// 我的思路：击败了100%，先序遍历，但是具体处理有点不一样，先递归右子树，然后递归左子树
	public void connect2(Node root) {
		if (root == null) return;
		Node left = root.left;
		Node right = root.right;
		if (left != null) {
			if (right != null) {
				left.next = right;
			} else {
				Node next = root.next; // next 必须为root的下一个子树不空的树
				while (next != null) {
					if (next.left != null) {
						left.next = next.left;
						break;
					} else if (next.right != null) {
						left.next = next.right;
						break;
					} else next = next.next;
				}
			}
		}
		if (right != null) {
			Node next = root.next;
			while (next != null) {
				if (next.left != null) {
					right.next = next.left;
					break;
				} else if (next.right != null) {
					right.next = next.right;
					break;
				} else next = next.next;
			}
		}
		connect2(root.right);  // 先递归右边，再递归左边右子树（让右子树的next指针都产生），
		// 不能先递归左边（否则右子树还没递归完成，next指针没连起来，可能造成结果错误）
		connect2(root.left);
	}


	// leetcode最佳：使用层序遍历但是不使用栈
    // 与第116题通用
	public Node connectOpt(Node root) {
		Node head = root;
		while (head != null) {
			Node level = new Node(); // 每一层的虚拟节点
			Node tail = level;

			while (head != null) {
				if (head.left != null) {
					tail.next = head.left;
					tail = tail.next;
				}
				if (head.right != null) {
					tail.next = head.right;
					tail = tail.next;
				}
				head = head.next;
			}
			head = level.next; // head更新为每一层的头结点

		}
		return root;
	}

}
