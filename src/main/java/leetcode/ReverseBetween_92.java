package leetcode;

// https://leetcode.com/problems/reverse-linked-list-ii/
public class ReverseBetween_92 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (m == n) return head;
		ListNode initHead = new ListNode(0);
		initHead.next = head;
		ListNode beforeSwap = initHead;
		ListNode cur = head;
		int count = 0;
		while (cur != null) {
			count++;
			if (count == m) break;
			beforeSwap = cur;
			cur = cur.next;
		}

		ListNode pre = cur;
		cur = cur.next;
		while (cur != null) { // 这里条件不能是next != null ，否则代码会写的非常冗余
			ListNode next = cur.next;
			cur.next = pre;
			count++;
			if (count == n) {
				beforeSwap.next.next = next;
				beforeSwap.next = cur;
				break;
			}
			pre = cur;
			cur = next;
		}
		return initHead.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		int m = 2;
		int n = 5;
		ListNode res = new ReverseBetween_92().reverseBetween(head,m, n);
		while (res != null) {
			System.out.print(res.val + ",");
			res = res.next;
		}
	}
}
