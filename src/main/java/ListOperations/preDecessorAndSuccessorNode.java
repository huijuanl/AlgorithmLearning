package ListOperations;
//寻找中序遍历中结点的前驱结点或后继结点(结点的数据结构中多了一个parent结点，用来保存结点的父结点)
public class preDecessorAndSuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {  //得到后继
        if(node ==null)
            return null;
        if(node.right != null){//如果结点的右子树不为空，则后继结点为右子树上最左边的结点
            return getLeftMost(node.right);
        }
        else {             //否则，做循环判断，若该结点为其父结点的左结点，则返回父结点。
            while (node.parent!=null && node.parent.left != node){
                node = node.parent;

            }
            return node.parent;


        }

    }
    public static Node getLeftMost(Node node) {
        if(node == null)
            return null;
        while (node.left !=null){
            node =node.left;

        }
        return  node;
    }
    public static Node getpreDecessorNode(Node node) {
        if(node ==null)
            return null;
        if(node.left != null){//如果结点的左子树不为空，则后继结点为左子树上最右边的结点
            return getRightMost(node.left);
        }
        else {             //否则，做循环判断，若该结点为其父结点的右结点，则返回父结点;否则将当前结点替换为其父结点。最后返回父结点
            while (node.parent!=null && node.parent.right != node){
                node = node.parent;

            }
            return node.parent;


        }

    }
    public static Node getRightMost(Node node) {
        if(node == null)
            return null;
        while (node.right !=null){
            node =node.right;

        }
        return  node;
    }
    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;
        System.out.println("==============getSuccessorNode==============");
        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
        System.out.println("==============getpreDecessorNode==============");
        test = head.left.left;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test));
        test = head.left.left.right;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
        test = head.right.right;
        System.out.println(test.value + " pre: " + getpreDecessorNode(test).value);
    }


}


