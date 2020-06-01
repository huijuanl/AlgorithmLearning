package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// https://leetcode-cn.com/problems/decode-string/
public class DecodeString_394 {

	// 我的思路：用一个栈来实现
	// 用一个StringBuilder来存储最终结果
	// 入栈：栈为空且s.charAt(i）为字母时；或者栈不为空时入栈
	// 出栈：当遇到]时，连续出栈入栈元素，直到栈顶为数字
	//      当栈顶为数字时，弹出该数字k，且将之前连续出栈的元素复制为k份，重新入栈
	public String decodeString(String s) {
		Stack<Character> stack = new Stack<>();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
//			System.out.println(i);
			if (stack.isEmpty()) {
				if (isChar(s.charAt(i))) {
					stringBuilder.append(s.charAt(i));
				} else {
					stack.add(s.charAt(i));
				}
			} else {
				if (s.charAt(i) == ']') {
					// 开始循环出栈
					Deque<Character> queue = new LinkedList<>();
					while (!stack.isEmpty() && isChar(stack.peek())) {
						queue.addFirst(stack.pop());
					}
					stack.pop();
					// digit可能超过一位数（比如200）
					StringBuilder digitBuffer = new StringBuilder();
					while (!stack.isEmpty() && isDigital(stack.peek())) {
						digitBuffer.append(stack.pop());
					}
					int digit = Integer.parseInt(digitBuffer.reverse().toString());
					int size = digit * queue.size();
					boolean stackIsEmpty = stack.isEmpty();
					for (int k = 0; k < size; k++) {
						Character top = queue.pollFirst();
						if (stackIsEmpty) {
							stringBuilder.append(top);
						} else {
							stack.add(top);
						}
						queue.addLast(top);
					}

				} else {
					stack.add(s.charAt(i));

				}

			}
		}

		return stringBuilder.toString();
	}

	public boolean isDigital(Character s) {
		return s >= '0' && s <= '9';
	}

	public boolean isChar(Character s) {
		return  (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z');
	}

	public static void main(String[] args) {
		String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";

		String ss = "2[b4[F]]";
		String res = new DecodeString_394().decodeString(ss);
		System.out.println(res);
	}
}
