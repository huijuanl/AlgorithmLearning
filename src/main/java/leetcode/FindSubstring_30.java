package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
// 用HashMap<String , boolean>来判断每个单词是不是在子串中
// words中可能存在相同的单词
// 使用滑动窗口，滑动窗口的长度是固定的
// 滑动窗口如何移动?
// 以eachWordLen为单位进行移动
// 每个start以移动一个位置为单位移动
// 时间复杂度为O(nk)，空间复杂度为O(n)
public class FindSubstring_30 {

	// 30%
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
		HashMap<String , Integer> hashMap = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			hashMap.put(words[i], hashMap.getOrDefault(words[i], 0) + 1);
		}
		HashMap<String , Integer> hashMapInit = new HashMap<>(hashMap);
        int wordLen = words[0].length();
		if (s.length() < wordLen * words.length) return res;

		for (int i = 0; i < wordLen; i++) { // 注意这里是小于wordLen，而不是s.length()
			int start = i;
			int end = start + wordLen;
			int count = 0;
			while (start < end && end <= s.length()) {
					String value = s.substring(end - wordLen, end);
					if (hashMap.containsKey(value)) {
						if (hashMap.get(value) > 0) {
							count++;
							hashMap.put(value, hashMap.get(value) - 1);
						} else {
							String key = s.substring(start, start + wordLen);
							while (!key.equals(value)) {
								hashMap.put(key, hashMap.get(key) + 1);
								count--;
								start = start + wordLen;
								key = s.substring(start, start + wordLen);
							}
							start = start + wordLen;
						}
						if (count == words.length) {
							res.add(start);
							String key = s.substring(start, start + wordLen);
							hashMap.put(key, hashMap.get(key) + 1);
							count--;
							start = start + wordLen;
						}
					} else {
						start = end;
						hashMap = new HashMap<>(hashMapInit);
						count = 0;
					}
				end = end + wordLen;
			}
			hashMap = new HashMap<>(hashMapInit);
		}
		return res;
	}

	// 优化过的，击败了82%，用了两个hashMap，一个map初始化后不变，另一个hashmap变化并及时clear
	// 这个性能比用一个hashmap要快，减少了hashmap clone建立的时间
	public List<Integer> findSubstring2(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
		Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			map.put(words[i], map.getOrDefault(words[i], 0) + 1);
		}

		int wordLen = words[0].length();

		if (s.length() < wordLen * words.length) return res;

		for (int i = 0; i < wordLen; i++) { // 注意这里是小于wordLen，而不是s.length()
			int start = i;
			int end = start + wordLen;
			int count = 0;
			while (start < end && end <= s.length()) {
				String value = s.substring(end - wordLen, end);
				if (map.containsKey(value)) {
					if (curMap.getOrDefault(value, 0) < map.get(value)) {
						count++;
						curMap.put(value, curMap.getOrDefault(value, 0) + 1);
					} else {
						String key = s.substring(start, start + wordLen);
						while (!key.equals(value)) {
							curMap.put(key, curMap.get(key) - 1);
							count--;
							start = start + wordLen;
							key = s.substring(start, start + wordLen);
						}
						start = start + wordLen;
					}
					if (count == words.length) {
						res.add(start);
						String key = s.substring(start, start + wordLen);
						curMap.put(key,  curMap.get(key) - 1);
						count--;
						start = start + wordLen;
					}
				} else {
					start = end;
					curMap.clear();
					count = 0;
				}
				end = end + wordLen;
			}
			curMap.clear();
		}
		return res;
	}

	// Leetcode上给出的解法:https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/javashi-xian-cong-bao-li-fa-dao-hua-dong-chuang-ko/
	// Sliding Window    360ms
	// ask interviewer if words is empty, should I return empty list
	// https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/13656/An-O(N)-solution-with-detailed-explanation%E3%80%82
	public List<Integer> findSubstringLeetCode(String S, String[] L) {
		List<Integer> res = new LinkedList<>();
		if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
		int N = S.length(), M = L.length, K = L[0].length();
		Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
		for (String s : L) {
			if (map.containsKey(s))   map.put(s, map.get(s) + 1);
			else                      map.put(s, 1);
		}
		String str = null, tmp = null;
		for (int i = 0; i < K; i++) {
			int count = 0;  // remark: reset count
			for (int l = i, r = i; r + K <= N; r += K) {
				str = S.substring(r, r + K);
				if (map.containsKey(str)) {
					if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
					else                           curMap.put(str, 1);

					if (curMap.get(str) <= map.get(str))    count++;
					while (curMap.get(str) > map.get(str)) {
						tmp = S.substring(l, l + K);
						curMap.put(tmp, curMap.get(tmp) - 1);
						l += K;
						if (curMap.get(tmp) < map.get(tmp)) count--;
					}
					if (count == M) {
						res.add(l);
						tmp = S.substring(l, l + K);
						curMap.put(tmp, curMap.get(tmp) - 1);
						l += K;
						count--;
					}
				}else {
					curMap.clear();
					count = 0;
					l = r + K;
				}
			}
			curMap.clear();
		}
		return res;
	}

	public static void main(String[] args) {
//		String s = "barfoofoobarthefoobarman";
//		String[] words = new String[]{"foo","bar", "the"};
		String s = "aaaaaaaa";
		String[] words = new String[]{"aa","aa", "aa"};
//		String s = "barfoothefoobarman";
//		String[] words = new String[]{"foo","bar"};
//		String s = "wordgoodgoodgoodbestword";
//		String[] words = new String[]{"word","good","best","good"};
//		String s = "wordgoodgoodgoodbestword";
//		String[] words = new String[]{"word","good","best","word"};
		List<Integer> res = new FindSubstring_30().findSubstring2(s, words);
		for(int i = 0; i < res.size(); i++) {
			System.out.print(res.get(i) + ",");
		}
	}
}
