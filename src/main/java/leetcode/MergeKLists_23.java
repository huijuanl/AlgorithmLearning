package leetcode;


// https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKLists_23 {

	// 根据leetcode上的提示写出来的解法，用了分而治之的思想。先将k个首尾两两合并，成为k/2个链表
	// 然后继续循环，合并成k/4个链表，直到链表长度为1。
	// 一共需要循环logk次
	// 时间复杂度为kn*logk,时间复杂度的求法见https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
	// 其中n为每个链表的长度，可以理解为kn*logk = N*logk(其中N为所有链表节点的个数)
    // 也就是说每一次合并都要遍历所有的节点一次，一共需要遍历logk次，所以时间复杂度为Nlogk
	public ListNode mergeKLists(ListNode[] lists) {
		//  if (lists == null || k == 0) return null;
		int k = lists.length;
		while (k > 1) {
			for (int i = 0; i < (k + 1) / 2; i++) {
				if (i != k - 1 - i) {
					lists[i] = mergeTwoLists(lists[i], lists[k - 1 - i]);
				}
			}
			k = (k + 1) / 2;
		}
		return lists[0];
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(0);
		ListNode tail = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				tail.next = l1;
				l1 = l1.next;
			} else {
				tail.next = l2;
				l2 = l2.next;
			}
			tail = tail.next;
		}
		if (l1 == null) {
			tail.next = l2;
		}
		if(l2 == null){
			tail.next = l1;
		}
		return head.next;
	}

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1);
		list1.next = new ListNode(4);
		list1.next.next = new ListNode(5);
		ListNode list2 = new ListNode(1);
		list2.next = new ListNode(3);
		list2.next.next = new ListNode(4);
		ListNode list3 = new ListNode(2);
		list3.next = new ListNode(6);

		ListNode res = new MergeKLists_23().mergeKLists(new ListNode[]{list1, list2, list3});

	    while (res != null) {
	    	System.out.print(res.val + ", ");
	    	res = res.next;
		}
	}
}
