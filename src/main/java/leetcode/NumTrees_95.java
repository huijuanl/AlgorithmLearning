package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/unique-binary-search-trees-ii/
// 用递归的思想，左子树上所有的val都比root大，有字数上所有的val都比root.val大
public class NumTrees_95 {
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) return new ArrayList<TreeNode>();
		return buildTree(1, n);
	}

	public List<TreeNode> buildTree(int min, int max) {
		if (min > max) return null;
		// i: 做root的节点，从min到max都可选
		List<TreeNode> res = new ArrayList<>();
		for (int i = min; i <= max; i++) {
			List<TreeNode> leftTrees = buildTree(min, i - 1);
			List<TreeNode> rightTrees = buildTree(i + 1, max);
			if (leftTrees == null || rightTrees == null) {
				if (leftTrees == null && rightTrees == null) {
					TreeNode root = new TreeNode(i);
					res.add(root);
				} else if (leftTrees ==null) {
					for (int n = 0; n < rightTrees.size(); n++) {
						TreeNode root = new TreeNode(i);
						root.right = rightTrees.get(n);
						res.add(root);
					}
				} else {
					for (int m = 0; m < leftTrees.size(); m++) {
						TreeNode root = new TreeNode(i);
						root.left = leftTrees.get(m);
						res.add(root);
					}
				}
			} else {
				for (int m = 0; m < leftTrees.size(); m++) {
					for (int n = 0; n < rightTrees.size(); n++) {
						TreeNode root = new TreeNode(i);
						root.left = leftTrees.get(m);
						root.right = rightTrees.get(n);
						res.add(root);
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int n = 3;
		List<TreeNode> res = new NumTrees_95().generateTrees(n);
		System.out.println(res);
	}
}
