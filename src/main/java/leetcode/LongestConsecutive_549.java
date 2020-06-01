package leetcode;

// https://leetcode-cn.com/problems/binary-tree-longest-consecutive-sequence-ii/
public class LongestConsecutive_549 {
	int maxLen = 0;
	public int longestConsecutive(TreeNode root) {
		search(root);
		return maxLen;
	}

	// 记录从root开始的最长下降序列和最长上升序列
	public  int[] search(TreeNode root) {
		if (root == null) return new int[]{0, 0};

		int[] curLen = new int[]{1, 1}; // curLen[0]表示从root开始向下递增的长度，curLen[1]表示递减的长度
		int[] leftRes = search(root.left);
		int[] rightRes = search(root.right);

		if (root.left == null && root.right != null) {
			if (root.val == (root.right.val - 1)) {
				curLen[0] = rightRes[0] + 1;
			} else if (root.val == (root.right.val + 1)) {
				curLen[1] = rightRes[1] + 1;
			}

 		} else if (root.right == null && root.left != null) {
			if (root.val == (root.left.val - 1)) {
				curLen[0] = leftRes[0] + 1;
			} else if (root.val == (root.left.val + 1)) {
				curLen[1] = leftRes[1] + 1;
			}

		} else if (root.left != null && root.right != null){
			if (root.val == (root.left.val -1) && root.val == (root.right.val + 1)) {
				maxLen = Math.max(maxLen, leftRes[0] + rightRes[1] + 1);
			} else if (root.val == (root.right.val -1) && root.val == (root.left.val + 1)) {
				maxLen = Math.max(maxLen, leftRes[1] + rightRes[0] + 1);
			}

			if (root.val == (root.left.val - 1)) {
				curLen[0] = Math.max(curLen[0], leftRes[0] + 1);
			}
			if (root.val == root.right.val - 1) {
				curLen[0] = Math.max(curLen[0], rightRes[0] + 1);
			}

			if (root.val == (root.left.val + 1)) {
				curLen[1] = Math.max(curLen[1], leftRes[1] + 1);
			}
			if (root.val == root.right.val + 1) {
				curLen[1] = Math.max(curLen[1], rightRes[1] + 1);
			}
		}

		maxLen = Math.max(maxLen, Math.max(curLen[0], curLen[1]));
		return curLen;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		int res = new LongestConsecutive_549().longestConsecutive(root);
		System.out.println(res);
	}
}
