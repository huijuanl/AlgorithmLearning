package leetcode;

// https://leetcode.com/problems/rotate-list/
public class RotateRight_61 {

	// 我的思路：移动k步相当于把倒数k个节点平移到链表头部
	// 其他处理： k需要对链表长度取模
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) return head;
		int length = 0;
		ListNode cur = head;
		ListNode tail = head;
		while (cur != null) {
			length++;
			tail = cur;
			cur = cur.next;
		}
		int modK = k % length;
		if (modK == 0) return head;

		int count = 0;
		cur = head;
		ListNode pre = head;
		while ((length - count) > modK) {
			pre = cur;
			cur = cur.next;
			count++;
		}
		tail.next = head;
		pre.next = null;
		return cur;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		ListNode head2 = new RotateRight_61().rotateRight(head, 2);
		while (head2 != null) {
			System.out.print(head2.val + ",");
			head2 = head2.next;
		}
	}
}
