package leetcode;

public class DeleteDuplicates_83 {
	class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) return head;
		ListNode low = head;
		ListNode high = head.next;
		while (high != null) {
			if (low.val == high.val) {
				if (high.next == null) { // 注意该边界
					low.next = null;
				}
				high = high.next;
			} else {
				low.next = high;
				low = high;
				high = high.next;
			}
		}
		return head;
	}
}
