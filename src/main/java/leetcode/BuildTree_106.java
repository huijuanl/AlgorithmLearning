package leetcode;

import java.util.HashMap;

// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
// 思路和前面一道题一样，都是递归 + hashMap
public class BuildTree_106 {
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		HashMap map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildTree(postorder, 0, postorder.length - 1,
				inorder, 0, inorder.length - 1, map);
	}

	public TreeNode buildTree(int[] postorder, int postStart, int postEnd,
									   int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {

		System.out.println(postStart + "," + postEnd + "," + inStart + "," + inEnd);
		if (postStart > postEnd) {
			return null;
		} else if (postStart == postEnd) {
			return new TreeNode(postorder[postStart], null, null);
		}
		int rootValue = postorder[postEnd];
		int i = map.get(rootValue);

		TreeNode left = buildTree
				(postorder,  postStart, i - inStart + postStart - 1,
						inorder, inStart, i - 1, map);
		TreeNode right = buildTree(
				postorder, i - inStart + postStart, postEnd - 1,
				inorder, i + 1, inEnd, map);
		return new TreeNode(rootValue, left, right);
	}

	public static void main(String[] args) {
		int[]postorder = new int[]{9,15,7,20,3};
		int[]inorder = new int[]{9,3,15,20,7};
		TreeNode root = new BuildTree_106().buildTree(inorder, postorder);
		System.out.println(root.val);
	}
}
