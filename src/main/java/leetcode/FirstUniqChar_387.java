package leetcode;

// https://leetcode.com/problems/first-unique-character-in-a-string/
// 我的思路：用一个字典数组来存储各个字母出现的频率。
// 时间复杂度O(n)
// 空间复杂度O(1)
public class FirstUniqChar_387 {
	public int firstUniqChar(String s) {

		int[] nums = new int[26];
		for (int i = 0; i < s.length(); i++) {
			nums[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if (nums[s.charAt(i) - 'a'] == 1) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		String s = "loveleetcode";
		int res = new FirstUniqChar_387().firstUniqChar(s);
		System.out.println(res);
	}
}
