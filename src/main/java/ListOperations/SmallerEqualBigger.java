package ListOperations;

import static ListOperations.ReverseList.printLinkedList;

public class SmallerEqualBigger {

    //按照某个值分割,额外空间复杂度为o(1),时间复杂度为O(N)
    public static Node listPartition(Node head, int pivot) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node cur = head;
        Node next = null;
        //只遍历一次，得到三个链表
        while (cur!=null){
            next = cur.next;
            cur.next = null;
            if(cur.data<pivot){
               if(sH==null){
                   sH=cur;
                   sT=sH;
               }
               else {
                   sT.next=cur;
                   sT=sT.next;
               }
            }
            if(cur.data==pivot){
                if(eH==null){
                    eH=cur;
                    eT=eH;
                }
                else {
                    eT.next=cur;
                    eT=eT.next;
                }
            }
            if(cur.data>pivot){
                if(bH==null){
                    bH=cur;
                    bT=bH;
                }
                else {
                    bT.next=cur;
                    bT=bT.next;
                }
            }
            cur=next;
        }
        //将三个链表一次串联起来
        head = MergeTwoList(MergeTwoList(sH,eH),bH);
        //返回生成的链表
        return head;
    }
    //辅助函数:将head1的尾部和head2的头部连接起来
    public static Node MergeTwoList(Node head1,Node head2){
        if(head1==null){
            return head2;
        }
        else if(head2 == null){
            return head1;
        }
        else {
            Node cur = head1;
            while (cur.next!=null) {
                cur = cur.next;
            }
            cur.next=head2;
            return head1;
        }

    }
//测试
    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition(head1, 5);
        printLinkedList(head1);

    }

}
