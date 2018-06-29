package ListOperations;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


//对树做序列化，同时根据序列化字符串获得原来的树:
//如果一个结点不为空，输出左右结点（左右结点如果为null也保存该信息）
//序列化的时候可以按照先序或中序或后序序列化
//序列化和遍历有什么区别：我们可以采用先序遍历的思想，只是在这里需要改动。
//                   为了能够在重构二叉树时结点能够插入到正确的位置，在使用先序遍历保存二叉树到文件中的时候需要把NULL结点也保存起来
//                  （可以使用特殊符号如“#”来标识NULL结点）。
//重构的时候使用queue队列结构
//序列化结果可以唯一反序列化为一棵二叉树
//反序列化都用队列来实现
public class SerializeAndReconstructTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static String serialByPre(Node head) {//返回值为字符串，而不是直接输出结果 可以用递归遍历序列或非递归实现
        //*********递归实现*************
        /*
        if (head == null) {
            return "#!";//这儿相比先序遍历多加了一个"#"，表示为Null结点
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
        */
        //*********非递归实现,stack(null也压入)*************
        if(head == null)
            return "#!";
        Stack<Node> stack =new Stack<Node>();
        stack.push(head);
        String res ="";
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            if(cur!=null) {
                res += cur.value + "!";
                stack.push(cur.right);
                stack.push(cur.left);
            }
            else res += "#!";
        }
        return res;
    }
    //先序遍历反序列化非递归实现+栈
//    public static Node reconByPreString(String preStr) { //先序遍历的反序列化也可以用非递归形式实现，加一个栈
//        //思想为：如果当前从字符串数组中取值构成的结点不为空，则将该值入栈，且字符数组中的下一个值为当前结点的左结点，并将左结点作为当前结点；
//        // 否则弹出栈顶，字符数组中的下一个值为栈顶的右结点，将当前指针指向该右结点
//        //进行循环，直到栈为空且当前指针为nul退出循环
//        String[] values = preStr.split("!");
//        int index = 0;
//        Stack<Node> stack = new Stack<Node>();
//        Node head = generateNodeByString(values[index++]);
//        if(head == null)
//            return null;
//        Node cur =head;
//        while (cur !=null||!stack.isEmpty()){
//            if(cur !=null){
//                stack.push(cur);
//                cur.left = generateNodeByString(values[index++]);
//                cur = cur.left;
//            }
//            else {
//                Node parent = stack.pop();
//                parent.right = generateNodeByString(values[index++]);
//                cur = parent.right;
//            }
//        }
//            return  head;
//    }


    //先序遍历反序列化递归实现+队列
 //先序遍历反序列化也可以用递归方法实现，先将字符串数组初始化为队列,
 //执行出队操作，若出队元素非空，说明下一个元素为左节点，执行遍历操作
 //执行完之后，队列缩短，剩下的元素都属于根节点的右子树，因此，调用该方法，得到右子树的根节点
 public static Node reconByPreString(String preStr) {
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}

	public static Node reconPreOrder(Queue<String> queue) {
		String value = queue.poll();
		if (value.equals("#")) {
			return null;
		}
		Node head = new Node(Integer.valueOf(value));
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		return head;
	}

    public static String serialByLevel(Node head) {//层序遍历实现序列化，queue（null也压入）
      if(head == null)
          return "#!";
        Queue<Node>queue = new LinkedList<Node>();
        queue.add(head);
        String res = "";
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur !=null){
                res += cur.value + "!";
                queue.add(cur.left);
                queue.add(cur.right);
            }
            else
                res +="#!";

        }
        return res;
    }
   //层序遍历反序列化+队列
    public static Node reconByLevelString(String levelStr) {//先将字符串切分为字符串数组，与层序遍历的写法类似，
                                                             // queue中用来保存那些新建的不为null的结点，（这些结点根据字符串来建立，还没初始化左右孩子）所以queue中不保存null
        String[] values = levelStr.split("!");
        int index =0;
        Queue<Node> queue = new LinkedList<Node>();
        Node head = generateNodeByString(values[index++]);
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            cur.left= generateNodeByString(values[index++]);
            cur.right = generateNodeByString(values[index++]);
            if(cur.left!=null)
                queue.add(cur.left);
            if(cur.right!=null)
                queue.add(cur.right);

        }

        return head;
    }
    public static Node generateNodeByString(String val) {//根据字符串中的值返回一个引用
        if (val.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }
    //============for test ==================


    public static void main(String[] args) {
        Node head = null;
        System.out.println("====1================================");

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.println("reconstruct tree by pre-order: "+serialByPre(head) );




        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.println("reconstruct tree by level, "+ serialByLevel(head));

        System.out.println("====================================");

        head = new Node(100);
        head.left = new Node(21);
        head.left.left = new Node(37);
        head.right = new Node(-42);
        head.right.left = new Node(0);
        head.right.right = new Node(666);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.println("reconstruct tree by pre-order, " + serialByPre(head));


        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.println("reconstruct tree by level, " + serialByLevel(head));

        System.out.println("====================================");

    }
}
