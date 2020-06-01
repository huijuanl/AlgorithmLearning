package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class ZigzagLevelOrder_103 {
	// 锯齿形层序遍历，用一个队列来实现，队列的长度来控制每一层，奇翻偶不翻

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		queue.add(root);
		int level = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			if (level % 2 == 1) {
				Collections.reverse(list);
			}
			res.add(list);
			level++;
		}
		return res;
	}

	// 也可以用两个栈来实现，一个栈存储从右到左的节点，一个栈存储从左到右的节点
}
