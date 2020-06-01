package leetcode;

// https://leetcode.com/problems/number-of-1-bits/
public class HammingWeight_191 {

	public int hammingWeight(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			count += n & 1;
			n = n >> 1;

		}
		return count;
	}
	public static void main(String[] args) {
		int n = 11;
		int res = new HammingWeight_191().hammingWeight(n);
		System.out.println(res);
	}
}
