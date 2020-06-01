package leetcode;

// https://leetcode.com/problems/valid-palindrome/
// 验证是否为回文串，跳过空格等特殊字符，只判断字母和数字
public class IsPalindrome_125 {
	public boolean isPalindrome(String s) {
		int start = 0;
		int end = s.length() - 1;
		String lowerStr = s.toLowerCase();
		while (start <= end) {
			char startValue = lowerStr.charAt(start);
			char endValue = lowerStr.charAt(end);
			if ((startValue > 'z' || startValue < 'a') && (startValue < '0' || startValue > '9') ) {
				start++;
			} else if ((endValue > 'z' || endValue < 'a') && (endValue < '0' || endValue > '9')) {
				end--;
			} else if (startValue != endValue) {
				return false;
			} else {
				start++;
				end--;
			}
		}

		return true;
	}

	public static void main(String[] args) {
//		String word1 = "A man, a plan, a canal: Panama";
		String word1 = "0P0";
		boolean res = new IsPalindrome_125().isPalindrome(word1);
		System.out.println(res);
	}
}
