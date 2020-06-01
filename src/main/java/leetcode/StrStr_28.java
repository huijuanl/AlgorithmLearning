package leetcode;

// https://leetcode.com/problems/implement-strstr/
// 我的思路: 用了两个指针(暴力法)
// 复杂度: 最坏：O((n-k)k)，最优O(k)
// KMP算法：可以实现复杂度为O(m+n)
// 注意最长前缀：是说以第一个字符开始，但是不包含最后一个字符
// a的前缀是“”， aa的最大前缀是"a"
public class StrStr_28 {
	public int strStr(String haystack, String needle) {
		if (needle.length() == 0) return 0;
		int i = 0;
		while (i <= haystack.length() - needle.length()) {
			int j = 0;
			int k = i;
			int next = i + 1;
			while (j < needle.length() && haystack.charAt(k) == needle.charAt(j)) {
				j++;
				k++;

			}
			if (j== needle.length()) {
				return i;
			}
			i = next;
		}
		return -1;
	}

	// KMP算法
	public int strStrOpt(String haystack, String needle) {
		if (needle.isEmpty()) {
			return 0;
		}
		char[] h = haystack.toCharArray();
		char[] n = needle.toCharArray();
		int lenH = h.length, lenN = n.length;
		if (lenN > lenH) {
			return -1;
		}
		// 主串的下标。
		int i = 0;
		// 搜索串的下标。
		int j = 0;
		// 定义 next 数组，存储 n[j] 前不必重复匹配的最大长度。
		int[] next = getNext(needle);
		// 主串不够用或者搜索串匹配完成则跳出循环。
		while (i < lenH && j < lenN) {
			if (j == -1 || h[i] == n[j]) {
				// 当从头开始匹配搜索串或者当前位置匹配继续下一个位置。
				i++;
				j++;
			} else {
				// 出现不匹配字符则从最大可复用部分的下一个位置开始比较。
				j = next[j];
			}
		}
		// 搜索串的每一位都能匹配成功，则返回匹配的起始位置。
		if (j == lenN) {
			return i - j;
		} else {
			return -1;
		}
	}

	private int[] getNext(String needle) {
		char[] n = needle.toCharArray();
		// 定义 next 数组，存储 n[j] 前不必重复匹配的最大长度。
		int[] next = new int[n.length];
		// 数组第一位设为 -1 ，方便判断当前位置是否为搜索串的最开始。
		next[0] = -1;
		// 搜索串的下标。
		int j = 0;
		// 搜索串匹配的位置。
		int k = -1;
		// 从第二个位置开始计算搜索串每个位置之前不必重复匹配的最大长度。
		while (j < n.length - 1) {
			// 当从头开始匹配搜索串或者当前位置匹配继续下一个位置。
			if (k == -1 || n[j] == n[k]) {
				// 当前位置 j 之前不必重复匹配的最大长度为 k 。
				next[++j] = ++k;
			} else {
				// 回溯直到找到与 j 位置匹配的 k 或者从头开始匹配下一个字符。
				k = next[k]; // 推导公式见: https://blog.csdn.net/qq_37969433/article/details/82947411?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String s = "kmknabcababc";
		String b = "ababc";
		int res = new StrStr_28().strStrOpt(s, b);
		System.out.println(res);
	}
}
