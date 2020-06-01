package leetcode;

// https://leetcode.com/problems/partition-list/
// 分隔列表，使得比x小的元素都在x的左边，比x大的元素无所谓。需要保持相对位置不变
public class PartitionList_86 {

	// 这是我的思路，也可以换成两个虚拟头结点+两个指针的形式减少条件判断
	public ListNode partition(ListNode head, int x) {
		ListNode head1 = null; // 存储比x大或等于的链表
		ListNode tail1 = null;
		ListNode head2 = null; // 存储比x小的链表
		ListNode tail2 = null;
		ListNode initHead = head;
		while (head != null) {
			if (head.val >= x) {
				if (head1 == null) {
					head1 = head;
					tail1 = head1;
				} else {
					tail1.next = head;
					tail1 = tail1.next;
				}
			} else {
				if (head2 == null) {
					head2 = head;
					tail2 = head2;
				} else {
					tail2.next = head;
					tail2 = tail2.next;
				}
			}
			head = head.next;
		}

		// 串起来
		if (head1 != null && head2 != null) {
			tail2.next = head1;
			tail1.next = null;
			return head2;
		} else return initHead;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(2);
		int x = 3;
		ListNode res = new PartitionList_86().partition(head, 3);
		while (res != null) {
			System.out.print(res.val + ",");
			res = res.next;
		}
	}
}
