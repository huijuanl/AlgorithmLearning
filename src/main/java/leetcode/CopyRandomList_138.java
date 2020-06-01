package leetcode;

// https://leetcode.com/problems/copy-list-with-random-pointer/

class MyNode {
	int val;
	MyNode next;
	MyNode random;

	public MyNode(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}
public class CopyRandomList_138 {


	// A -> A' -> B -> B'
	public MyNode copyRandomList(MyNode head) {
		if (head == null) return head;
		MyNode cur = head;
		while (cur != null) {
			MyNode next = cur.next;
			MyNode copyNext = new MyNode(cur.val);
			cur.next = copyNext;
			copyNext.next = next;
			cur = next;
		}

		cur = head;
		while (cur != null) {
			MyNode copyNext = cur.next;
			if (cur.random != null) copyNext.random = cur.random.next;
			cur = copyNext.next;
		}

		cur = head;
		MyNode copyHead = head.next;
		// 分离两个链表（原来的链表记得也复原）
		while (cur.next != null) {
			MyNode next = cur.next;
			cur.next = next.next;
			cur = next;
		}

		return copyHead;
	}

	public static void main(String[] args) {
//		Node head = new Node(1);
//		head.next = new Node(2);
//		head.next.random = head;
//		head.next.next = new Node(3);
//		[[7,null],[13,0],[11,4],[10,2],[1,0]]
		MyNode head = new MyNode(7);
		head.next = new MyNode(13);
		head.next.random = head;
		head.next.next = new MyNode(11);

		head.next.next.next = new MyNode(10);
		head.next.next.next.random = head.next.next;
		head.next.next.next.next = new MyNode(1);
		head.next.next.next.next.random = head;
		head.next.next.random = head.next.next.next.next;
		MyNode copyHead = new CopyRandomList_138().copyRandomList(head);
		while (copyHead != null) {
			System.out.print(copyHead.random + ",");
			copyHead = copyHead.next;
		}

	}
}
