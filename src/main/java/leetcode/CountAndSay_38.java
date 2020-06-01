package leetcode;

// https://leetcode.com/problems/count-and-say/
public class CountAndSay_38 {
	public String countAndSay(int n) {
		if (n == 1) return "1";
		String strN_1 = countAndSay(n - 1);
		StringBuffer buffer = new StringBuffer();
		int start = 0;
		for (int i = 0; i <= strN_1.length(); i++) {
			if (i < strN_1.length() && strN_1.charAt(i) == strN_1.charAt(start)) {
				continue;
			} else {
				buffer.append(i - start);
				buffer.append(strN_1.charAt(start));
				start = i;
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		String res = new CountAndSay_38().countAndSay(3);
		System.out.println(res);
	}
}
