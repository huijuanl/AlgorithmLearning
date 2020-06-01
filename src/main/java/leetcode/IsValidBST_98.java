package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/validate-binary-search-tree/
public class IsValidBST_98 {

	// 我的思路: 用的递归，击败100%
	// 注意： 只判断root.left.val < root.val < root.right.val是不一定为一棵二叉搜索树的
	// 需要左子树所有的值都小于root，右子树所有的节点都小于root
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		if (root.val == Integer.MIN_VALUE && root.left != null) return false;
		if (root.val == Integer.MAX_VALUE && root.right != null) return false;
		return isValid(root.left, Integer.MIN_VALUE, root.val - 1)
				&& isValid(root.right, root.val + 1, Integer.MAX_VALUE);
	}

	public boolean isValid (TreeNode root, int minValue, int maxValue) {
		if (root == null) return true;
		if (root.val == Integer.MIN_VALUE && root.left != null) return false;
		if (root.val == Integer.MAX_VALUE && root.right != null) return false;
		if (root.val < minValue || root.val > maxValue) return false;
		return isValid(root.left, minValue, root.val - 1)
				&& isValid(root.right, root.val + 1, maxValue);
	}

	// leetcode上的解法：中序遍历为升序即可,击败100%
	long pre = Long.MIN_VALUE; // 存储上一个中序遍历节点的值
	public boolean isValidBSTOpt(TreeNode root) {
		if (root == null) return true;
		boolean leftRes = isValidBSTOpt(root.left);
		if (!leftRes || root.val <= pre) return false;
		pre = root.val;
		return isValidBSTOpt(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(11);
		root.right.right = new TreeNode(20);
		boolean res = new IsValidBST_98().isValidBST(root);
		System.out.println(res);
	}
}
