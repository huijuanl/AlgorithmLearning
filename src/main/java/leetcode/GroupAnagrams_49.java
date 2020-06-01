package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams_49 {

	// 我的思路：用一个HashMap来实现，key为word排序后的词，value为满足条件的排序前的list
	// faster than 96.94%
	// 若n为所有的单词个数，k为每个单词的长度
	// 时间复杂度为O(nklogk)
	// 空间复杂度为O(nk)
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<String, List<String>> res = new HashMap<>();
		for (int i = 0; i < strs.length; i++) {
			char[] arr = strs[i].toCharArray();
			Arrays.sort(arr);

			if (res.containsKey(String.valueOf(arr))) {
				res.get(String.valueOf(arr)).add(strs[i]);
			} else {
				List<String> list = new ArrayList();
				list.add(strs[i]);
				res.put(String.valueOf(arr), list);
			}
		}
		return new ArrayList<>(res.values());
	}

	// leetcode上的另一种思路：
	// 不使用排序，而是用int[26]来计数
	// 如果每个字母出现的字数都相同，说明是字母异位词
	// 若n为所有的单词个数，k为每个单词的长度
	// 时间复杂度为O(nk)
	// 空间复杂度为O(nk)
	public List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0) return new ArrayList();
		Map<String, List> ans = new HashMap<String, List>();
		int[] count = new int[26];
		for (String s : strs) {
			Arrays.fill(count, 0);
			for (char c : s.toCharArray()) count[c - 'a']++;

			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 26; i++) {
				sb.append('#');
				sb.append(count[i]);
			}
			String key = sb.toString();
			if (!ans.containsKey(key)) ans.put(key, new ArrayList());
			ans.get(key).add(s);
		}
		return new ArrayList(ans.values());
	}

	public static void main(String[] args) {
		String[] input = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		List<List<String>> res = new GroupAnagrams_49().groupAnagrams(input);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				System.out.print(res.get(i).get(j) + ",");
			}
			System.out.println();
		}
	}
}
