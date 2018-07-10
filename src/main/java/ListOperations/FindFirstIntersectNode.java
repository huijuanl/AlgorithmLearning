package ListOperations;

public class FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }
    //得到一个单链表的第一个环结点，如果没有环则返回null
    public static Node getLoopNode(Node head) {//得到第一个环结点
        Node slow = head;
        Node quick = head;
        if (head == null || head.next == null || head.next.next == null)
            return null;
        slow =slow.next;
        quick=quick.next.next;
        while (quick!=slow) {
            if (quick.next == null||quick.next.next == null)//快指针到底了
                return null;
            quick = quick.next.next;
            slow = slow.next;
        }
        //
        quick = head;
        //若链表中存在环，我们从链表头、与两个指针的相遇点分别设一个指针，每次各走一步，两个指针必定相遇，且相遇的第一个点为环的入口点。
        while (quick != slow) {
            quick = quick.next;
            slow = slow.next;

        }
        return slow;

    }

    //判断两个无环单链表是否相交
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int length1 = 1;
        Node cur1 = head1;
        int length2 = 1;
        Node cur2 = head2;
        while (cur1.next != null) {
            length1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            length2++;
            cur2 = cur2.next;
        }
        if (cur1 != cur2)
            return null;

        cur1 = length1 > length2 ? head1 : head2;
        cur2 = length1 > length2 ? head2 : head1;
        int n = Math.abs(length1 - length2);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = head1;
        Node cur2 = head2;
        //情况一:第一个环结点相同，说明第一个相交的点类似于一个“丫”字下接“口”
        if (loop1 == loop2) {
            int length1 = 1;
            int length2 = 1;
            while (cur1 != loop1) {
                length1++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                length2++;
                cur2 = cur2.next;
            }

            cur1 = length1 > length2 ? head1 : head2;
            cur2 = length1 > length2 ? head2 : head1;
            int n = Math.abs(length1 - length2);
            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        //情况二：第一个环结点不同，但是可能属于同一个环的不同环结点(类似于一个电视的形状)
        cur1 = loop1.next;
        while (cur1 != loop1) {//注意这儿是loop1
            if (cur1 == loop2)
                return cur1;
            cur1 = cur1.next;
        }
        //其他情况：都为null
        return null;



    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->7->4->5->6->..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->8 不相交
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = new Node(6);
        head2.next.next.next.next = head2.next.next;
        System.out.println(getIntersectNode(head1, head2));
    }
}
