package leetcode;

// https://leetcode.com/problems/multiply-strings/

// 对于"123" 与 "56"相乘，拆分成123* 50 + 123 * 6，也用了两个string如何相加
public class Multiply_43 {
	public String multiply(String num1, String num2) {
		String sum = "";
		if ("0".equals(num1) || "0".equals(num2)) return "0";
		for (int j = num2.length() - 1; j >= 0; j--) {
			StringBuffer buffer = new StringBuffer();
			int second = num2.charAt(j) - '0';
			int plus = 0;
			int k = j;
			while (k < num2.length() - 1) {
				buffer.append('0');
				k++;
			}
			for (int i = num1.length() - 1; i >= 0; i--) {
				int first = num1.charAt(i) - '0';
				int res = (first * second + plus) % 10;
				plus = (first * second + plus) / 10;
				buffer.append(res);
			}
			if (plus != 0) {
				buffer.append(plus);
			}
			sum = plusTwoStrings(sum, buffer.reverse().toString());
		}

		return sum;
	}

	public String plusTwoStrings(String a, String b) {
        if ("".equals(a)) {
        	return b;
		} else if ("".equals(b)) return a;
		int i = a.length() - 1;
		int j = b.length() - 1;
		int plus = 0;
		StringBuffer buffer = new StringBuffer();
		while (i >= 0 && j >=0) {
			int tmp = a.charAt(i) - '0' + b.charAt(j) - '0' + plus;
			int res = tmp % 10;
			buffer.append(res);
			plus = tmp / 10;
			i--;
			j--;
		}
		while (i >= 0) {
			int tmp = (a.charAt(i) - '0' + plus);
			int res = tmp % 10;
			buffer.append(res);
			plus = tmp / 10;
			i--;
		}
		while (j >= 0) {
			int tmp = b.charAt(j) - '0' + plus;
			int res = tmp % 10;
			buffer.append(res);
			plus = tmp / 10;
			j--;
		}

		if (plus == 1) {
			buffer.append(plus);
		}

		return buffer.reverse().toString();
	}

	public static void main(String[] args) {
		String num1 = "123";
		String num2 = "9999";
		String res = new Multiply_43().multiply(num1, num2);
		System.out.println(res);
	}
}
