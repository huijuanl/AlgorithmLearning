package leetcode;

// https://leetcode.com/problems/sort-list/

public class SortList_148 {

	// leetCode上的提示：用归并排序是最好的
	// merge两个有序链表
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;

		// split two list
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		ListNode head1 = head;
		ListNode head2 = slow.next;
		slow.next = null;
		ListNode sortedHead1 = sortList(head1);
		ListNode sortedHead2 = sortList(head2);
		return merge(sortedHead1, sortedHead2);
	}

	public ListNode merge(ListNode head1, ListNode head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;
		ListNode initHead = new ListNode(0);
		ListNode tail = initHead;
		while (head1 != null && head2 != null) {
			if (head1.val <= head2.val) {
				tail.next = head1;
				tail = tail.next;
				head1 = head1.next;
			} else {
				tail.next = head2;
				tail = tail.next;
				head2 = head2.next;
			}
		}

		if (head1 != null) {
			tail.next = head1;
		}

		if (head2 != null) {
			tail.next = head2;
		}

		return initHead.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(0);
		ListNode newHead = new SortList_148().sortList(head);
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
}
