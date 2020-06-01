package leetcode;

// https://leetcode.com/problems/length-of-last-word/
// 这里情况较多，有点乱，需要优化一下
public class LengthOfLastWord_58 {
	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0) return 0;
		int lastSpaceIndex = -1; // 最后一个空格位置且左侧为单词
		int preSpaceIndex = -1; // 最后空格位置且右侧为单词
		int spaceIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				spaceIndex = s.charAt(i);
				if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
					lastSpaceIndex = i;
				}
				if (i + 1 < s.length() && s.charAt(i) != s.charAt(i + 1)) {
					preSpaceIndex = i;
				}
			}
		}
		if (spaceIndex == -1) return s.length();
		if (preSpaceIndex == -1 && lastSpaceIndex == -1) return 0;
		if (preSpaceIndex < lastSpaceIndex) return lastSpaceIndex - preSpaceIndex - 1;
		return s.length() - preSpaceIndex - 1;
	}

	// 优化: 从后往前，击败了100%
	public int lengthOfLastWordOpt(String s) {
		int lastSpaceIndex = -1;
		int preSpaceIndex = -1;
		if (s == null || s.length() == 0) return 0;
		int numSpace = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				numSpace++;
				if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
					lastSpaceIndex = i;
					break;
				}
			}
		}

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (i + 1 < s.length() && s.charAt(i) != s.charAt(i + 1)) {
					preSpaceIndex = i;
					break;
				}
			}
		}
		if (numSpace == 0) return s.length();
		if (lastSpaceIndex == -1 && preSpaceIndex == -1) return 0;
		if (preSpaceIndex < lastSpaceIndex) return lastSpaceIndex - preSpaceIndex - 1;
		return s.length() - preSpaceIndex - 1;
	}

	public int lengthOfLastWordLeetcode(String s) {
		if (s == null || s.length() == 0) return 0;
		int end = s.length() - 1;

		// 去掉尾部的空格
		while (end >= 0 && s.charAt(end) == ' ') {
			end--;
		}

		int start = end;
		while (start >= 0) {
			if (s.charAt(start) == ' ') {
				break;
			} else start--;
		}

		return end - start;
	}
	public static void main(String [] args) {
		String s = "a";
		int res = new LengthOfLastWord_58().lengthOfLastWordLeetcode(s);
		System.out.print(res);
	}
}
