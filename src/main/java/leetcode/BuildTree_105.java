package leetcode;

import java.util.HashMap;

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// 使用了递归的思想，通过找到root所在的位置，且分出子先序数组和子中序数组，时间复杂度O(n^2) (最坏情况下为全是左子树)
// leetcode上的改进: 找root所在位置的话，要从中序数组中遍历一遍，会很麻烦，可以用一个hashMap来存储中序数组及对应的下标
// 时间复杂度: O(n)，空间复杂度: O(n)
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

public class BuildTree_105 {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return buildTree(preorder, 0, preorder.length - 1,
				inorder, 0, inorder.length - 1, map);
	}

	public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
							  int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
		if (preStart > preEnd) {
			return null;
		} else if (preStart == preEnd) {
			return new TreeNode(preorder[preStart], null, null);
		}
		int rootValue = preorder[preStart];
		int i = map.get(rootValue);

		TreeNode left = buildTree
				(preorder,  preStart + 1, i - inStart + preStart,
				inorder, inStart, i - 1, map);
		TreeNode right = buildTree(
				preorder, i - inStart + preStart + 1, preEnd,
				inorder, i + 1, inEnd, map);
		return new TreeNode(rootValue, left, right);
	}

	public static void main(String[] args) {
		int[]preorder = new int[]{3,9,20,15,7};
		int[]inorder = new int[]{9,3,15,20,7};
		TreeNode root = new BuildTree_105().buildTree(preorder, inorder);
        System.out.println(root.val);
	}
}
