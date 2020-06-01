package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverTree_99 {

	// 解法一：我用的是O(n)的时间复杂度
	List<TreeNode> res = new ArrayList<>();
	public void recoverTree(TreeNode root) {
		inOrder(root);
		int pre = -1;

		for (int i = 0; i < res.size() - 1; i++) {
			if (res.get(i).val > res.get(i + 1).val) {
				if (i == 0 || res.get(i).val > res.get(i - 1).val) {
					if (pre == -1) {
						pre = i;
					}
				}
				if (i + 1 == res.size() - 1 || (pre != - 1 && res.get(pre).val < res.get(i + 2).val)) {
					int preNum = res.get(pre).val;
					res.get(pre).val = res.get(i + 1).val;
					res.get(i + 1).val = preNum;
					break;
				}
			}
		}
	}

	void inOrder(TreeNode root) {
		if (root == null) return;
		inOrder(root.left);
		res.add(root);
		inOrder(root.right);
	}

	// 解法二：进行优化，只需要一遍中序遍历就可以进行交换

	TreeNode preNode = null; // 中序遍历的前一个非空节点
	TreeNode postTargetNode = null; // 第二个目标交换节点
	TreeNode preTargetNode = null; // 目标交换点
	TreeNode preTargetNextNode = null; // 第一个目标交换点的下一个中序遍历节点（用于当两个交换点相邻的时候使用）
	public void recoverTreeOpt(TreeNode root) {
		if (root == null) return;
		recoverTreeOpt(root.left);
		if (preNode != null && root.val < preNode.val && preTargetNode == null) {
			preTargetNode = preNode;
			preTargetNextNode = root;
		} else if (preTargetNode != null && root.val < preNode.val) {
			// swap
			int value = preTargetNode.val;
			preTargetNode.val = root.val;
			root.val = value;
			postTargetNode = root;
			return;
		} else if (preTargetNode != null && root.val > preTargetNode.val) {
			return;
		}
		preNode = root;
		if (postTargetNode == null || preTargetNode == null) recoverTreeOpt(root.right);
	}

	public void recoverTree2(TreeNode root) {
		recoverTreeOpt(root);
		if (postTargetNode == null) {
			int value = preTargetNode.val;
			preTargetNode.val = preTargetNextNode.val;
			preTargetNextNode.val = value;
		}
	}

	// 解法三：解法二比较冗余，可以用两个if来判断相邻节点的情况，在中序遍历结束之后再统一进行交换
	TreeNode firstNode = null;
	TreeNode secondNode = null;
	TreeNode preNode3 = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree3(TreeNode root) {
		in_order(root);
		int tmp = firstNode.val;
		firstNode.val = secondNode.val;
		secondNode.val = tmp;
	}

	private void in_order(TreeNode root) {
		if (root == null) return;
		in_order(root.left);
		if (firstNode == null && preNode3.val > root.val) firstNode = preNode3;
		if (firstNode != null && preNode3.val > root.val) secondNode = root;
		preNode3 = root;
		in_order(root.right);
	}

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(10);
//		root.left = new TreeNode(15);
//		root.right = new TreeNode(5);
//		root.right.left = new TreeNode(11);
//		root.right.right = new TreeNode(20);
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.right.left = new TreeNode(2);
		new RecoverTree_99().recoverTree2(root);
		System.out.println(root);
	}

}
