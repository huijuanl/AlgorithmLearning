package leetcode;

import java.util.HashMap;

// https://leetcode.com/problems/permutation-in-string/
public class CheckInclusion_567 {
	public boolean checkInclusion(String s1, String s2) {
		if (s1 == null || s2 == null) return false;
		if (s1.length() == 0 || s2.length() == 0 || s1.length() > s2.length()) {
			return false;
		}

		HashMap<Character, Integer> hashMapInit = new HashMap<>();

		for (int i = 0; i < s1.length(); i++) {
			hashMapInit.put(s1.charAt(i) , hashMapInit.getOrDefault(s1.charAt(i), 0) + 1);
		}

		int start = 0;
		int windowLen = s1.length();
		int count = 0;
		HashMap<Character, Integer> curMap = new HashMap<>();

		for (int end = start; end < s2.length(); end++) {

			char key = s2.charAt(end);
			if (hashMapInit.containsKey(key)) {
				if (hashMapInit.get(key) > curMap.getOrDefault(key, 0)) {
					curMap.put(key, curMap.getOrDefault(key, 0) + 1);
					count++;
				} else {
					while (start < end && s2.charAt(start) != s2.charAt(end)) {
						count--;
						curMap.put(s2.charAt(start), curMap.get(s2.charAt(start)) - 1);
						start++;
					}
					start = start + 1;
				}

			} else {
				start = end + 1;
				curMap.clear();
				count = 0;
			}

			if (count == windowLen) {
				return true;
			}

		}

		return false;
	}

	// leetcode上给出的答案：用的是数组（字典）而不是hashMap
	// 本题中窗口大小是固定的，是s1.length()，因此只需要遍历所有大小为k的窗口，检验是否有事排列串即可；
	// 窗口不需要从0滑动到s1.length()
	// 本题中所有的字符均是小写字符，因此共有26个字符，创建常数数组而不是哈希表来


	public boolean checkInclusionLeetCode(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;
		int[] s1map = new int[26];
		int[] s2map = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
			s2map[s2.charAt(i) - 'a']++;
		}

		// s1map和s2map分别统计了s1、s2中前s1.length子串中每个字符出现的频率
		// count统计了两个map中频次相等的字符的个数
		int count = 0;
		for (int i = 0; i < 26; i++)
			if (s1map[i] == s2map[i])
				count++;

		// 如果count数为26个，那么直接返回true

		for (int i = 0; i < s2.length() - s1.length(); i++) {
			int r = s2.charAt(i + s1.length()) - 'a';
			int l = s2.charAt(i) - 'a';
			if (count == 26)
				return true;
			s2map[r]++;
			if (s2map[r] == s1map[r])
				count++;
			else if (s2map[r] == s1map[r] + 1)
				count--;
			s2map[l]--;
			if (s2map[l] == s1map[l])
				count++;
			else if (s2map[l] == s1map[l] - 1)
				count--;
		}
		return count == 26;
	}

	// 我基于leetcode进行的优化实现: 字典数组 + 滑动窗口
	public boolean checkInclusionOpt(String s1, String s2) {
		if (s1.length() > s2.length())
			return false;
		int[] s1map = new int[26];
		int[] s2map = new int[26];
		for (int i = 0; i < s1.length(); i++) {
			s1map[s1.charAt(i) - 'a']++;
			s2map[s2.charAt(i) - 'a']++;
		}

		// s1map和s2map分别统计了s1、s2中前s1.length子串中每个字符出现的频率
		// count统计了两个map中频次相等的字符的个数
		int count = 0;
		for (int i = 0; i < 26; i++)
			if (s1map[i] == s2map[i])
				count++;

		// 如果count数为26个，那么直接返回true
		if (count == 26) return true;
		int start = 0;
		for (int end = start + s1.length(); end < s2.length(); end++) {
			int endKey = s2.charAt(end) - 'a';
			int startKey = s2.charAt(start) - 'a';
			s2map[endKey]++;
			s2map[startKey]--;
		    if (endKey != startKey) {
		    	if (s2map[endKey] == s1map[endKey]) {
		    		count++;
				} else if (s2map[endKey] ==s1map[endKey] + 1) {
		    		count--;
				}
				if (s2map[startKey] == s1map[startKey]) {
					count++;
				} else if (s2map[startKey] == s1map[startKey] - 1) {
					count--;
				}
			}
		    if (count == 26) return true;
		    start++;
		}

		return false;
	}

	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "eidcacbaooo";
		Boolean res = new CheckInclusion_567().checkInclusionOpt(s1, s2);
		System.out.println(res);
	}
}
