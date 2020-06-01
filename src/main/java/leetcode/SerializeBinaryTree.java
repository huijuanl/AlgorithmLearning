package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 二叉树的序列化和反序列化： 先序遍历更好写
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class SerializeBinaryTree {
	// Encodes a tree to a single string.
	// 层序遍历, 以','分隔
	 public String serialize(TreeNode root) {
		if (root == null) return "null";
		StringBuilder builder = new StringBuilder();
		Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				TreeNode top = queue.poll();
				if (top == null) {
					builder.append(",null");
				} else {
					if (builder.length() == 0) {
						builder.append(top.val);
					} else {
						builder.append(",").append(top.val);
					}
					queue.add(top.left);
					queue.add(top.right);
				}
			}
		}
		return builder.toString();
	}

	// Decodes your encoded data to tree.
	// 层序遍历的反序列化：也要有一个queue
	// 还要有一个指针start在字符串上向后滑动
	public TreeNode deserialize(String data) {
	 	if (data.equals("null")) return null;
	 	int start = 1;
	 	String[] str = data.split(",");
	 	TreeNode root = new TreeNode(Integer.parseInt(str[0]));

	 	Queue<TreeNode> queue = new LinkedList<>();
	 	queue.add(root);
	 	while (!queue.isEmpty()) {
	 		int size = queue.size();
	 		for (int i = 0; i < size; i++) {
	 			TreeNode node = queue.poll();
	 			if (str[start].equals("null")) {
	 				node.left = null;

				} else {
	 				node.left = new TreeNode(Integer.parseInt(str[start]));
	 				queue.add(node.left);
				}
	 			start++;
				if (str[start].equals("null")) {
					node.right = null;
				} else {
					node.right = new TreeNode(Integer.parseInt(str[start]));
					queue.add(node.right);
				}
				start++;
			}
		}

	 	return root;
	}

	// 先序遍历

	public String serializePre(TreeNode root) {
		if (root == null) return "null";
		StringBuilder builder = new StringBuilder();
		searchPre(root, builder);
		return builder.deleteCharAt(0).toString();
	}

	public void searchPre(TreeNode root, StringBuilder builder) {
	 	if (root == null) {
	 		builder.append(",null");
	 		return;
		}
	 	builder.append(",").append(root.val);
	 	searchPre(root.left, builder);
	 	searchPre(root.right, builder);
	}

	// Decodes your encoded data to tree.
	// 先序遍历的反序列化：也要有一个queue
	// 并逐步删除queue头部元素
	public TreeNode deserializePre(String data) {
		if (data.equals("null")) return null;
		String[] str = data.split(",");
		Deque<String> queue = new LinkedList<>();
		for(String k: str) {
			queue.add(k);
		}
		return deserializePreRec(queue);
	}

	public TreeNode deserializePreRec(Deque<String> queue) {
	 	String s = queue.pollFirst();
	 	if (s.equals("null")) {
	 		return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(s));
		root.left = deserializePreRec(queue);
		root.right = deserializePreRec(queue);
		return root;
	}


	public static void main(String[] args) {
	 	TreeNode root = new TreeNode(1);
	 	root.left = new TreeNode(2);
	 	root.right = new TreeNode(3);
	 	root.right.left = new TreeNode(4);
	 	root.right.right = new TreeNode(5);
	 	String s = new SerializeBinaryTree().serializePre(root);
	 	System.out.println(s);
	 	TreeNode rootDer = new SerializeBinaryTree().deserializePre(s);
	 	System.out.println(rootDer.val);
	}
}
