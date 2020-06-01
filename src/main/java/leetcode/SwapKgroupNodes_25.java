package leetcode;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class SwapKgroupNodes_25 {

	// 我的思路：使用递归，前k个节点变为反向链表，然后递归串起来
	public ListNode reverseKGroup(ListNode head, int k) {
        ListNode firstNode = head;
        ListNode endNode = firstNode;
        int n = k;
		while (endNode != null && n > 0) {
			endNode = endNode.next;
			n--;
		}
		if (n > 0) return head;
		int m = k;
		ListNode secondNode = firstNode.next;
		ListNode initHead = firstNode;
		ListNode thirdNode;
		while (m > 1) {
			thirdNode = secondNode.next;
			secondNode.next = firstNode;
			firstNode = secondNode;
			secondNode = thirdNode;
			m--;
		}

		initHead.next = reverseKGroup(endNode, k);
		return firstNode;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		ListNode head2 = new SwapKgroupNodes_25().reverseKGroup(head, 3);
		while (head2 != null) {
			System.out.print(head2.val + ",");
			head2 = head2.next;
		}
	}
}
