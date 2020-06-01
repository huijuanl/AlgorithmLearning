package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/restore-ip-addresses/
// 使用递归，回溯法
public class RestoreIpAddresses_93 {
	List<String> res = new ArrayList<>();
	public List<String> restoreIpAddresses(String s) {
		if (s.length() < 4 || s.length() > 12) {
			return res;
		}
		split(s, 0, s.length() - 1, 3, new StringBuffer());
		return res;
	}

	public void split(String s, int start, int end, int spotNum, StringBuffer buffer) {

		if (start > end || spotNum < 0 ) return;
		int len = end - start + 1;
		if ((spotNum + 1) > len || (spotNum + 1) * 3 < len) return;
		if (spotNum == 0) {
			if (isValidStr(s, start, end, buffer)) {
				res.add(buffer.toString());
			}
		} else {
			for (int i = start; i <= end && i <= (start + 2); i++) {
				int bufferSize = buffer.length();
				if (isValidStr(s, start, i, buffer)) {
					buffer.append(".");
					split(s, i + 1, end, spotNum - 1, buffer);
					while (buffer.length() > bufferSize) {
						buffer.deleteCharAt(buffer.length() - 1);
					}
				}
			}
		}
	}

	// 判断[start, end]区间是否使有效的数字，如果使的话，返回true，同时buffer.append该数字
	public boolean isValidStr(String s, int start, int end, StringBuffer buffer) {
		boolean isValid = false;
		if (end == start) {
			// 1位
			isValid = true;
			buffer.append(s.charAt(start));
		} else if (end == (start + 1) && s.charAt(start) != '0') {
			isValid = true;
			buffer.append(s.charAt(start)).append(s.charAt(end));
		} else if (end == (start + 2)) {
			isValid = s.charAt(start) == '1';
			if (!isValid && s.charAt(start) == '2') {
				if (s.charAt(start + 1) < '5' ||
						(s.charAt(start + 1) == '5' && s.charAt(end) >= '0' && s.charAt(end) <= '5')) {
					isValid = true;
				}
			}
			if (isValid) {
				buffer.append(s.charAt(start))
						.append(s.charAt(start + 1))
						.append(s.charAt(end));
			}
		}

		return isValid;
	}
	public static void main(String[] args) {
		String s = "25525511135";
		List<String> res = new RestoreIpAddresses_93().restoreIpAddresses(s);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}

		int n = 64;
		System.out.println(n ^ 1);

	}

}
