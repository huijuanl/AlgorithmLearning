package BinaryTree;
//层序遍历的方式
//如果一个结点有右孩子，没有左孩子，肯定不是完全二叉树，返回false
//如果一个结点不是左右孩子都全，那么后面的结点必须都是叶节点(有个变量初始设置为false，当发现有一个结点不是左右孩子都全时，开启这个阶段为true)

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static boolean isCompleteBinaryTree(Node head) {
		if(head == null)
			return false;
		Queue<Node>queue = new LinkedList<Node>();
		queue.add(head);
		boolean leaf =false;
		while (!queue.isEmpty()) {
	     Node cur = queue.poll();
	     if(leaf == false){
	    	 if(cur.right!=null && cur.left == null)
	    	 return false;
	    	 if(cur.right==null||cur.left==null){
	    		 leaf = true;
	    	 }
	     }
	     else {
	    	 if(cur.left!=null||cur.right!=null)
	    		 return false;
	    	 
	     }
	     if(cur.left!=null)
	    	 queue.add(cur.left);
	     if(cur.right!=null)
	    	 queue.add(cur.right);
			
		}
		return true;
		
	}
	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		System.out.println(isCompleteBinaryTree(head));

	}
}
