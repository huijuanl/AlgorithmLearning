package leetcode;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// 中序遍历的第k个
public class KthSmallest_230 {

	// 中序遍历递归
	int num;
	public int kthSmallest(TreeNode root, int k) {
		search(root, k);
		return num;

	}

	public int search(TreeNode root, int k) {
		if (root == null) return 0;
		int leftRes = search(root.left, k);
		if (leftRes == k - 1) {
			num = root.val;
			return k;
		}
		if (leftRes >= k) return leftRes;
		return leftRes + 1 + search(root.right, k - leftRes - 1);
	}

	public static void main(String[] args){
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.left.left.left = new TreeNode(1);
		int res = new KthSmallest_230().kthSmallest(root, 3);
		System.out.println(res);
	}

}
