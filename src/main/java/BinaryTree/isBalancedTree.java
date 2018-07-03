package BinaryTree;
/*
 解法的整体过程为后序遍历，对于任意一个结点node，先遍历左子树，遍历过程中收集两个信息，一个是是否平衡，一个是左子树最深遍历到哪一层记为lH。若发现
 node的左子树不是平衡二叉树，无需进行后续过程，此时返回什么已不重要，退出遍历过程。如果左子树是平衡二叉树，再遍历右子树，如果右子树不是平衡二叉树，则返回。
 如果左右子树都平衡，则判断左右子树的高度差，若高度大于1，则res设置为false。最后返回当前树的高度
 res为Boolean型的全局变量
 */
public class isBalancedTree {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static boolean isBalance(Node head) {
	boolean[]res = new boolean[1];
	res[0]=true;
	int height = getHeight(head, 1, res);
	return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
	if(head == null)
		return level;
	int lH =getHeight(head.left,level+1,res);
	if(!res[0]){
		return level;
	}
	int rH = getHeight(head.right, level+1, res);
	if(!res[0]){
		return level;
	}
	if(Math.abs(lH-rH)>1)
		res[0]=false;
	return Math.max(lH, rH);
		
	
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}
}
