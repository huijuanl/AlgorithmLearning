package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
// 这个题和第108题的唯一区别是一个有序数组，一个是有序链表
public class SortedListToBST_109 {
	// 我的思路：用二分递归来实现, 链表转化为数组
	// 空间复杂度: O(n) (没有达到最优)，但是时间复杂度达到最优了，为O(N)

	public TreeNode sortedListToBST(ListNode head) {
		List<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		Integer[] nums = list.toArray(new Integer[0]);
		return buildTree(nums, 0, nums.length - 1);
	}

	public TreeNode buildTree(Integer[] nums, int start, int end) {
		if (start > end) return null;
		int mid = (start + end + 1) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		TreeNode left = buildTree(nums, start, mid - 1);
		TreeNode right = buildTree(nums, mid + 1, end);
		root.left = left;
		root.right = right;
		return root;
	}

}
