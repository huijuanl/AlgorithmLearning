package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) { this.val = val; }
	ListNode(int val, ListNode next) {
		this.val = val; this.next = next;
	}
}
public class DeleteDuplicates_82 {

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode pre = new ListNode();
		pre.next = head;
		ListNode initPre = pre;
		ListNode cur = head;
		ListNode next = cur.next;
		int val = cur.val;
		while (next != null) {
			if (val == next.val) {
				next = next.next;
				if (next == null) pre.next = null;

			} else {
				if (cur.next != next) {
					pre.next = next;
				} else {
					pre = cur;
				}

				cur = next;
				val = cur.val;
				next = cur.next;

			}
		}
		return initPre.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(1);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next = new ListNode(4);
//		head.next.next.next.next.next.next = new ListNode(5);

		ListNode initHead = new DeleteDuplicates_82().deleteDuplicates(head);
		while (initHead!=null) {
			System.out.print(initHead.val + ",");
			initHead = initHead.next;
		}
	}
}
