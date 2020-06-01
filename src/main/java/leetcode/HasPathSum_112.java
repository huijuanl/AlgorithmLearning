package leetcode;

// https://leetcode.com/problems/path-sum/submissions/
public class HasPathSum_112 {

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		if (root.left == null && root.right == null && sum == root.val) return true;
		boolean leftBoolean = hasPathSum(root.left, sum - root.val);
		if (leftBoolean) return true;
		return hasPathSum(root.right, sum - root.val);

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		Boolean res = new HasPathSum_112().hasPathSum(root, 26);
		System.out.println(res);
	}
}
