package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-ii/
public class HasPathSum_113 {
	List<List<Integer>> res= new ArrayList<>();

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		hasPathSum(root, sum, new ArrayList<>());
		return res;
	}

	public void hasPathSum(TreeNode root, int sum, List<Integer> list) {
		if (root == null) return;

		if (root.left == null && root.right == null) {
			if (sum == root.val) {
				list.add(root.val);
				res.add(new ArrayList<>(list));
				list.remove(list.size() - 1);
			}
		} else {
			list.add(root.val);
			hasPathSum(root.left, sum - root.val, list);
			hasPathSum(root.right, sum - root.val, list);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(12);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		List<List<Integer>> res = new HasPathSum_113().pathSum(root, 22);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
