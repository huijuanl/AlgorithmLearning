package leetcode;

import java.util.ArrayList;
import java.util.List;

public class WordBreak_139 {

	// 第一种：暴力递归
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s.equals("")) return true;
		for (int i = 0; i < s.length(); i++) {
			String word = s.substring(0, i + 1);
			boolean res = false;
			if (wordDict.contains(word)) {
				res = wordBreak(s.substring(i + 1), wordDict);
			}
			if (res) {
				return true;
			}
		}

		return false;
	}

	// 第二种：动态规划，dp[i]表示截止i为止是否在字典中 (效率更高)
	public boolean wordBreak2(String s, List<String> wordDict) {
		boolean[] dp = new boolean[s.length()];
		if (s.equals("")) return true;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j >= 0; j--) {
				if (dp[i]) break;
				if (j == i) {
					dp[i] = wordDict.contains(s.substring(0, i + 1));
				} else if (dp[j] && wordDict.contains(s.substring(j + 1, i + 1))){
					dp[i] = true;
				}
			}
		}

		return dp[s.length() - 1];
	}

	public static void main(String[] args) {
		String s = "leetcode";
		List<String> dict = new ArrayList<>();
		dict.add("leet");
		dict.add("code");
		boolean res = new WordBreak_139().wordBreak2(s, dict);
		System.out.println(res);
	}
}
