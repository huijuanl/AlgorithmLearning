package BinaryTree;
/*已知一棵树是完全二叉树，计算它的所有结点
 (1)如果遍历所有结点的话，复杂度为O(N)
 (2)为了减小复杂度，可以换一种方法，不用遍历
 //如果当前结点所在的层数等于该结点的左边界的高度，那么返回1（也就是只有一个结点）
 否则：如果当前结点的右子树的左边界高度+1=当前结点的左边界高度（那么该结点的左子树完全二叉树，右子树是满二叉树，高度为h-level）
     如果当前结点的右子树的左边界高度==当前结点的左边界高度（那么该结点的左子树是满二叉树，右子树是完全二叉树）
    先遍历左边界，得到完全二叉树的高度；然后遍历右子树的左边界，如果该边界等于完全二叉树的高度，说明左子树是满的，而右子树是一棵完全二叉树
    如果该边界小于完全二叉树的高度，说明左子树
 */
public class CompleteTreeNodeNumber {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static int completeTreeNodeNumber(Node head){
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}
	public static int bs(Node node, int l, int h) {//lӦ����node���ڵĲ�����h���������ĸ߶�
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}
	public static int mostLeftLevel(Node node, int level) {//levelΪnode��ǰ���ڽ��ĸ߶ȣ����ص�����NodeΪ���ڵ㿪ʼ����߽����ȣ��������ģ�
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}
	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(completeTreeNodeNumber(head));

	}
}
