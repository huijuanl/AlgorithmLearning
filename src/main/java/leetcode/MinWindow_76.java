package leetcode;

import java.util.HashMap;

// https://leetcode.com/problems/minimum-window-substring/
// 需要考虑t中有多个相同字符
// 我的思路:
// (1)用一个HashMap<Character, Integer>来装t中所有字符及出现的个数
// (2)start, i 两个指针对s进行遍历，count用来判断当前窗口是否包含了所有的字符,
// minWindowLength用来保存最小窗口的值，minStart用来保存最小窗口的起始位置。
// 遍历方法为: 当当前窗口内还没包含所有的字符时，i向右移动，直到当前窗口包含所有的字符
// 当当前窗口已经包含了所有字符时，start向右移动，直到当前窗口不包含所有字符，i再向右移动
public class MinWindow_76 {
	// 打败了72%
	public String minWindow(String s, String t) {
		if (s.length() < t.length()) return "";
		HashMap<Character, Integer> hashMap = new HashMap<>();
		int count = 0;
		for (int i = 0; i < t.length(); i++) {
			char key = t.charAt(i);
			if (hashMap.containsKey(key)) {
				hashMap.put(key, hashMap.get(t.charAt(i)) + 1);
			} else hashMap.put(key, 1);
		}

		int minWindowLength = Integer.MAX_VALUE;
		int minStart = -1;
		int start = 0;
		int i = 0;
		while (i <= s.length()) {
			while (start < i && count == t.length()) {
				if (i - start < minWindowLength) {
					minStart = start;
					minWindowLength = i - start;
				}

				char keyStart = s.charAt(start);
				if (hashMap.containsKey(keyStart)) {
					if (hashMap.get(keyStart) == 0) count--;
					hashMap.put(keyStart, hashMap.get(keyStart) + 1);
				}
				start++;
			}

			if (i < s.length() && hashMap.containsKey(s.charAt(i))) {
				int value = hashMap.get(s.charAt(i));
				if (value > 0) {
					count++;
				}
				hashMap.put(s.charAt(i), value - 1);
			}
			i++;
		}

		return minStart == -1 ?"": s.substring(minStart, minStart + minWindowLength);

	}

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
//		String s = "aa";
//		String t = "bb";
		String res = new MinWindow_76().minWindow(s, t);
		System.out.println(res);

	}

}
