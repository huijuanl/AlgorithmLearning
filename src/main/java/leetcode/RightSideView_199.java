package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-right-side-view/
public class RightSideView_199 {


	// 层序遍历
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		if (root == null) return res;
		res.add(root.val);

		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				TreeNode top = queue.poll();
				if (top.left != null){
					queue.addLast(top.left);
				}
				if (top.right != null) {
					queue.addLast(top.right);
				}
				if (i == queueSize - 1) {
					res.add(top.val);
				}
			}
		}
		return res;
	}

}
