package leetcode;

// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
public class LongestConsecutive_298 {

	int longestLenTree = 0;
	public int longestConsecutive(TreeNode root) {
		search(root);
		return longestLenTree;
	}

	// 以节点root为起点的最长连续子序列的长度
	public int search(TreeNode root) {
		int maxLen = 0;
		if (root == null) return 0;

		int leftRes = search(root.left);
		int rightRes = search(root.right);

		if (leftRes == 0 && rightRes == 0) {
			maxLen = 1;
		} else if (leftRes == 0) {
			if (root.val == (root.right.val - 1))
				maxLen = rightRes + 1;
		} else if  (rightRes == 0) {
			if (root.val == (root.left.val - 1))
				maxLen = leftRes + 1;
		} else if (root.val == (root.right.val - 1) || root.val == (root.left.val - 1)) {
			if (root.val == (root.right.val - 1) && (root.val == (root.left.val - 1))) {
				maxLen = Math.max(rightRes, leftRes) + 1;
			} else if (root.val == (root.right.val - 1)) {
				maxLen = rightRes + 1;
			} else maxLen = leftRes + 1;
		} else maxLen = 1;
		longestLenTree = Math.max(maxLen, longestLenTree);
//		System.out.println(longestLenTree + ", root.val=" + root.val);
		return maxLen;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		int len = new LongestConsecutive_298().longestConsecutive(root);
		System.out.println(len);
	}
}
