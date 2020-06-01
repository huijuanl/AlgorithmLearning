package leetcode;

import java.util.List;

// https://leetcode.com/problems/insertion-sort-list/
public class InsertionSortList_147 {

	public ListNode insertionSortList(ListNode head) {

		if (head == null) return null;
		ListNode initHead = new ListNode(0);
		initHead.next = head;
		ListNode cur = head;
		ListNode tail = initHead;
		while (cur != null) {
			ListNode next = cur.next;
			ListNode pre = initHead; // 已排好序的链表的虚拟表头部分
			ListNode recurse = pre.next;
			if (cur != head && cur.val < tail.val) {
				while (recurse != cur) {
					if (recurse.val > cur.val) {
						pre.next = cur;
						cur.next = recurse;
						tail.next = next;
						if (tail == cur) tail = tail.next;
						break;
					} else {
						pre = pre.next;
						recurse = recurse.next;
					}
				}
			} else {
				tail = tail.next;
			}
			cur = next;
		}

		tail.next = null;
		return initHead.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(3);
		ListNode res = new InsertionSortList_147().insertionSortList(head);
		while (res != null) {
			System.out.print(res.val + ",");
			res = res.next;
		}
	}
}
