package ListOperations;

public class CopyListWithRandomField {
    public static class Node {
        public int data;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node CopyListWithRandom(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        // copy node and link to every node
        while (cur != null) {
            Node tmp = new Node(cur.data);
            Node next = cur.next;
            tmp.next = next;
            cur.next = tmp;
            cur = next;
        }
        // set copy node rand
        cur = head;
        while (cur != null) {
            if(cur.rand != null){
                cur.next.rand = cur.rand.next;
            }
            else cur.next.rand = null;
            cur = cur.next.next;

        }
        cur = head;
        head = head.next;
        // split
        //断开新旧表的时候要注意旧表的最后一个结点特殊处理
        while (cur != null) {
            Node next = cur.next.next;
            if (next != null) {
                cur.next.next = next.next;
                cur.next = next;
                cur = next;
            } else {
                cur.next = null;
                break;
            }

        }
        return head;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = CopyListWithRandom(head);
        printRandLinkedList(res1);
        res2 = CopyListWithRandom(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = CopyListWithRandom(head);
        printRandLinkedList(res1);
        res2 = CopyListWithRandom(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }

}