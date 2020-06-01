package leetcode;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
public class Flatten_114 {

	public void flatten(TreeNode root) {
		flattenWithLastNode(root);

	}

	// return lastNode of ListNode
	public TreeNode flattenWithLastNode(TreeNode root) {
		if (root == null) return null;
		if (root.left == null && root.right == null) return root;

		TreeNode lastOfLeft = flattenWithLastNode(root.left);
		TreeNode lastOfRight = flattenWithLastNode(root.right);
		// 记得把左子树清零(null)
		if (lastOfRight == null) {
			root.right = root.left;
			root.left = null;
			return lastOfLeft;
		} else if (lastOfLeft != null){
			lastOfLeft.right = root.right;
			root.right = root.left;
			root.left = null;
		}
		return lastOfRight;
	}

	public static void main(String[] args) {
		TreeNode head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.right = new TreeNode(5);
//		head.left.left = new TreeNode(3);
		head.left.right = new TreeNode(4);
		head.right.right = new TreeNode(6);
		new Flatten_114().flatten(head);
		while (head != null) {
			System.out.println(head.val);
			head = head.right;
		}

	}
}
