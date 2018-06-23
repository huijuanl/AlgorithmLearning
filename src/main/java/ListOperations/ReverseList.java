package ListOperations;
//内部类
class  Node { //若放在类内部，为内部类，设置成static（表示生成一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。）
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

}

class DoubleNode {
    public int data;
    public DoubleNode pre;
    public DoubleNode next;
    public DoubleNode(int data) {
        this.data = data;
    }

}

public class ReverseList {
//     //内部类
//    public static class  Node { //若放在类内部，为内部类，设置成static（表示生成一个静态内部类不需要外部类成员：这是静态内部类和成员内部类的区别。）
//        public int data;
//        public Node next;
//
//        public Node(int data) {
//            this.data = data;
//        }
//
//    }
//
//    public static class DoubleNode {
//        public int data;
//        public DoubleNode pre;
//        public DoubleNode next;
//        public DoubleNode(int data) {
//            this.data = data;
//        }
//
//    }
    public static Node ReverseSingleList(Node head) {
        //cur指向当前结点，while循环用到了三个指针：pre,cur,next;该链表没有头节点
        Node pre = null;//初始化为null是为了把第一个结点的next赋值为null
        Node next = null;
        Node cur = head;
        while (cur != null) {
            next = cur.next; //在做翻转之前，将当前结点的后继保存到next，防止丢失
            cur.next = pre; //包含了将第一个结点的后继设置为null，因为初始pre==null
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static Node ReverseSingleListWithHeadNOde(Node head) {
        //该链表有头节点
        Node pre = null;//初始化为null是为了把第一个结点的next赋值为null
        Node next = null;
        Node cur = head.next;//cur仍然指向第一个结点
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //循环结束之后，pre指向最后一个结点，cur指向null
        head.next = pre;//相比没有头结点，多做一步，将头节点的后继设为最后一个结点
        return head;
    }

    public static DoubleNode ReverseDoubleList(DoubleNode head) {
        //双链表(不循环)
        //cur指向当前结点，while循环用到了三个指针：pre,cur,next;该链表没有头节点
        DoubleNode pre = null;//初始化为null是为了把第一个结点的next赋值为null
        DoubleNode next = null;
        DoubleNode cur = head;
        while (cur != null) {
         next=cur.next;
         cur.next=pre;
         cur.pre=next;//相比单链表多加了一行:将该结点的后继设置为该结点的前驱
         pre=cur;
         cur=next;
        }
        return pre;
    }

    public static DoubleNode ReverseDoubleListWithHeadNode(DoubleNode head) {
        //双链表(不循环)
        //cur指向当前结点，while循环用到了三个指针：pre,cur,next;该链表没有头节点
        DoubleNode pre = null;//初始化为null是为了把第一个结点的next赋值为null
        DoubleNode next = null;
        DoubleNode cur = head.next;
        while (cur != null) {
            next=cur.next;
            cur.next=pre;
            cur.pre=next;//相比单链表多加了一行:将该结点的后继设置为该结点的前驱
            pre=cur;
            cur=next;
        }
        head.next=pre;
        return head;
    }

    public static void printLinkedList(Node head) {
        System.out.print("Linked List without HeadNode: ");
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("Double Linked List without HeadNode: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.data + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.data + " ");
            end = end.pre;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printLinkedList(head1);
        head1 = ReverseSingleList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.pre = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.pre = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.pre = head2.next.next;
        printDoubleLinkedList(head2);
        printDoubleLinkedList(ReverseDoubleList(head2));

    }
}
