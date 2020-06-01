package leetcode;

// https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
public class FindSecondMinimumValue_671 {

	// 用了递归和剪枝
	int second = -1;
	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) return second;
		search(root);
		return second;
	}

	public void search(TreeNode root) {
		if (root == null || root.left == null) return;
		if (root.val == root.left.val) {
			search(root.left);
			if (root.val < root.right.val) {
				second = second == -1 ? root.right.val: Math.min(second, root.right.val);
			} else {
				search(root.right);
			}
		} else {
			search(root.right);
			second = second == -1? root.left.val: Math.min(second, root.left.val);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(1);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(2);
		int res = new FindSecondMinimumValue_671().findSecondMinimumValue(root);
		System.out.println(res);
	}
}
