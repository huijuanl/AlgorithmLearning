package leetcode;

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
//本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

public class SortedArrayToBST_108 {

	// 我的思路：用二分递归来实现
	public TreeNode sortedArrayToBST(int[] nums) {
		return buildTree(nums, 0, nums.length - 1);
	}

	public TreeNode buildTree(int[] nums, int start, int end) {
		if (start > end) return null;
		int mid = (start + end + 1) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		TreeNode left = buildTree(nums, start, mid - 1);
		TreeNode right = buildTree(nums, mid + 1, end);
		root.left = left;
		root.right = right;
		return root;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{-10,-3,0,5,9};
		TreeNode root = new SortedArrayToBST_108().sortedArrayToBST(arr);
		System.out.println(root);
	}
}
