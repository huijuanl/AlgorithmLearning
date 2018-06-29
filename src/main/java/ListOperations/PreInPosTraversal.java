package ListOperations;

import java.util.Stack;

public class PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static void preOrderRecur(Node head) {
        if(head == null)
            return;
        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);

    }
    public static void inOrderRecur(Node head) {
        if(head == null)
            return;

        inOrderRecur(head.left);
        System.out.print(head.value+" ");
        inOrderRecur(head.right);


    }
    public static void posOrderRecur(Node head) {
        if(head == null)
            return;
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }
    public static void preOrderUnRecur(Node head) {//非递归先序遍历
        if(head == null)
            return;
        Stack<Node> stack =new Stack<Node>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            System.out.print(cur.value+" ");
            if(cur.right!=null)
                stack.push(cur.right);

            if(cur.left!=null)
                stack.push(cur.left);
        }
    }
    public static void inOrderUnRecur(Node head) {//非递归中序遍历
    if(head == null)
        return;
    Stack<Node> stack = new Stack<Node>();
    while (!stack.isEmpty()||head != null){
        while (head != null){
            stack.push(head);
            head = head.left;

        }
        Node cur = stack.pop();
        System.out.print(cur.value+" ");
        head = cur.right;

     }

    }

    public static void posOrderUnRecur(Node head) {//非递归后序遍历
       if(head == null)
           return;
       Stack<Node> stack1 = new Stack<Node>();
       Stack<Node> stack2 = new Stack<Node>();
       stack1.push(head);//stack1.add(head)和 stack1.push(head)的效果是一样的，add是stack类继承自vector的方法
       while (!stack1.isEmpty()){
           Node cur = stack1.pop();
           stack2.push(cur);
           if(cur.left!=null)
               stack1.push(cur.left);
           if(cur.right!=null)
               stack1.push(cur.right);
       }
       while (!stack2.isEmpty()){
           System.out.print(stack2.pop().value+" ");

       }

    }
    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        System.out.print("pre-order: ");
        preOrderUnRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderUnRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderUnRecur(head);
        System.out.println();

    }
}
