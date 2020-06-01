package leetcode;

// https://leetcode.com/problems/swap-nodes-in-pairs/
// 注意，该题目要求不能只交换val，而是应该进行节点的交换
public class SwapPairs_24 {
	// 我的方法：两两交换奇数节点和偶数节点，击败了100%
	public ListNode swapPairs(ListNode head) {
		ListNode cur = head;
		ListNode newHead = new ListNode(0);
		newHead.next = head;
		ListNode tail = newHead;
		while (cur != null && cur.next != null) {
			tail.next = cur.next;
			cur.next = tail.next.next;
			tail.next.next = cur;
			tail = cur;
			cur = cur.next;
		}
		return newHead.next;
	}

	// leetcode上给出的其他解法: 递归
	public ListNode swapPairs2(ListNode head) {
		ListNode firstNode = head;
		if (firstNode == null || firstNode.next == null) return head;
		ListNode secondNode = head.next;
		ListNode next = swapPairs(secondNode.next);
		firstNode.next = next;
		secondNode.next = firstNode;
		return secondNode;
	}



	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		ListNode head2 = new SwapPairs_24().swapPairs2(head);
		while (head2 != null) {
			System.out.print(head2.val + ",");
			head2 = head2.next;
		}
	}
}
