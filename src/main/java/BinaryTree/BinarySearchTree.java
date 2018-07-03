package BinaryTree;

import java.util.Stack;

import BinaryTree.Code_07_IsBSTAndCBT.Node;

//判断是否是二叉搜索树：用中序遍历非递归法，判断是否是升序（用中序遍历的递归法比较难）
//每遍历一个结点，就去判断当前结点的值是否大于前一个结点，用一个变量来记录前一个结点的值，初始值定义为系统最小值
public class BinarySearchTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static boolean isBinarySearchTree(Node head){
     if(head == null)
    	 return false;
     Stack<Node>stack = new Stack<Node>();
     int value = Integer.MIN_VALUE;
     while(!stack.isEmpty()||head!=null){
    	 while(head!=null){
    		 stack.push(head);
    		 head = head.left;
    	 }
    	 head = stack.pop();
    	 if(head.value<value){
    		 return false;
    	 }
    	 value =head.value;
    	 head =head.right;
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
		System.out.println(isBinarySearchTree(head));
	

	}
}

