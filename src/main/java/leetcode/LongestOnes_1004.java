package leetcode;

// https://leetcode.com/problems/max-consecutive-ones-iii/

public class LongestOnes_1004 {
	// 我的思路：
	// 采用滑动窗口以及一个map用来存储窗口内出现0和1的次数
	// 设n为窗口的大小，当窗口中出现0的次数k0 > k时，即n - k1 < k 时，left++，直到出现0的次数=k
	// 出现1的次数k1 + k >= n 时, right++
	// 击败了43%
	public int longestOnes(int[] A, int K) {
		int[] freq = new int[2];
		int start = 0;
		int maxLength = 0;
		for (int i = 0; i < A.length; i++) {
			freq[A[i]]++;
			while (freq[0] > K) {
				freq[A[start]]--;
				start++;
			}
			maxLength = Math.max(maxLength, i - start + 1);
		}
		return maxLength;
	}

	public static void main(String[] args) {
//		int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
//		int k = 2;
		int[] nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
		int k = 3;
		int res = new LongestOnes_1004().longestOnes(nums, k);
		System.out.println(res);
	}
}
