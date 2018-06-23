package ListOperations;


import java.util.Stack;

import static ListOperations.ReverseList.printLinkedList;

public class IsPalindromeList {
    // need n extra space
    public static boolean isPalindrome1(Node head) {
        Node Cur = head;
        Stack<Node> stack = new Stack<Node>();
        while (Cur != null) {
            stack.push(Cur);
            Cur = Cur.next;
        }

        while (!stack.isEmpty()) {
            if (stack.pop().data != head.data)
                return false;
            head = head.next;

        }
        return true;
    }

    // need n/2 extra space
    public static boolean isPalindrome2(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node slow = head;
        Node quick = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;

        }
//        while(quick.next!=null&&quick.next.next==null){//这个循环可以不要，对慢指针没有影响
//            quick=quick.next;
//        }
        //此时慢指针指向了中点或中点中的前一个(偶数情况下)
        while (slow.next != null) {
            stack.push(slow.next);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (stack.pop().data != head.data)
                return false;
            head = head.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean isPalindrome3(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node slow = head;
        Node quick = head;
        while (quick.next != null && quick.next.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        Node head_Midtmp = slow.next;
        Node head_2 = ReverseList.ReverseSingleList(head_Midtmp);
        quick = head_2;
        while (head_2 != null) {
            if (head.data != head_2.data) {
                return false;
            }
            head_2 = head_2.next;
            head = head.next;
        }
        ReverseList.ReverseSingleList(quick);
        return true;
    }

    public static void main(String[] args) {

        Node head = null;
        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }

}
