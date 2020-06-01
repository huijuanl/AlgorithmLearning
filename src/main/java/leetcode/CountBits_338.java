package leetcode;

// https://leetcode.com/problems/counting-bits/submissions/
// 用动态规划计算即可
public class CountBits_338 {

	public int[] countBits(int num) {
		int[] arr = new int[num + 1];

		for (int i = 0 ; i<= num; i++) {
			arr[i] =  arr[i >> 1] + (i & 1);
		}
		return arr;
	}

	public static void main(String[] args) {
		int n = 5;
		int[] res = new CountBits_338().countBits(n);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + ",");
		}
	}
}
