package leetcode;

// https://leetcode-cn.com/problems/path-sum-iii/
public class PathSum_437 {

	// 我的思路：双重递归
	int res = 0;
	int init = 0;
	public int pathSum(TreeNode root, int sum) {
		init = sum;
		recursive(root, sum);
		return res;
	}

	public void recursive(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		search(root, sum);
		recursive(root.left, sum);
		recursive(root.right, sum);

	}
	// 从root节点开始找和为sum的所有可能
	public void search(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		if (sum == root.val) {
			res += 1;
		}

		search(root.left, sum - root.val);
		search(root.right, sum - root.val);

	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(-3);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(2);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right.right = new TreeNode(1);
		root.right.right = new TreeNode(11);
//		TreeNode root = new TreeNode(1);
//		root.right = new TreeNode(2);
//		root.right.right = new TreeNode(3);
//		root.right.right.right = new TreeNode(4);
//		root.right.right.right.right = new TreeNode(5);
		int res = new PathSum_437().pathSum(root, 8);
		System.out.println(res);


	}
}
