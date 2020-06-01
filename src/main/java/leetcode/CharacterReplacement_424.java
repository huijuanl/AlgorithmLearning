package leetcode;

// https://leetcode.com/problems/longest-repeating-character-replacement/
// 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
// 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

// 本题一开始没有思路，后来是看了https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
// 上的提示:
// 当K>0时，子串的条件变成了允许我们变换子串中的K个字符使其变成一个连续子串
// 那么这个题的关键点就是我们如何判断一个字符串改变K个字符，能够变成一个连续串
// 如果当前字符串中的出现次数最多的字母个数+K大于=串长度，那么这个串就是满足条件的

public class CharacterReplacement_424 {
	public int characterReplacement(String s, int k) {
		if (s == null || s.length() == 0) return 0;
		int[] mapInit = new int[26];
		int maxCharIndex = s.charAt(0) - 'A';
		mapInit[s.charAt(0) - 'A']++;
		int maxCount = 1;
		int maxWindowLen = 1;
		int start = 0;
		for (int end = 1; end < s.length(); end++) {
			int endKey = s.charAt(end) - 'A';
			mapInit[endKey]++;
			if (endKey != maxCharIndex && mapInit[endKey] > mapInit[maxCharIndex]) {
				maxCount = mapInit[endKey];
				maxCharIndex = endKey;
			} else if (endKey == maxCharIndex) {
				maxCount++;
			}
			if (maxCount + k >= end - start + 1) {
				maxWindowLen = Math.max(maxWindowLen, end - start + 1);
				continue;
			} else {
				mapInit[s.charAt(start) - 'A']--;
				maxCharIndex = findMaxValueOfIndex(mapInit);
				maxCount = mapInit[maxCharIndex];
				start++;
			}
		}
		return maxWindowLen;
	}

	public int findMaxValueOfIndex(int[] map) {
		int maxIndex = 0;
		for (int i = 1; i < map.length; i++) {
			if (map[i] > map[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	public static void main(String[] args) {
//		String s = "AABABBA";
//		int k = 1;
		String s = "BAAA";
		int k = 0;
		int res = new CharacterReplacement_424().characterReplacement(s, k);
		System.out.println(res);
	}
}
