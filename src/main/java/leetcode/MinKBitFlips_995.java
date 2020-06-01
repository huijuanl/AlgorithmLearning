package leetcode;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
// 求最小翻转次数，使得翻转之后整个字符串中的元素均为1
public class MinKBitFlips_995 {
	// 贪心法，从左到右，只要遇到0,就开始长度为k的翻转
	// 时间复杂度为O(nk)
	public int minKBitFlips(int[] A, int K) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				if (i + K < A.length) return -1;
				for (int j = 0; j < K ; j++) {
					A[i + j] = A[i + j] ^ 1;
				}
				count++;
			}
		}
		return count;
	}

	// leetcode上基于贪心法进行改进，利用了滑动窗口的最优解法
	// https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips/solution/guan-fang-da-an-tai-nan-dong-la-hua-dong-chuang-ko/
	// 我们再考虑每个位置上的元素，他只会被前面K - 1个元素是否被反转所影响
	// 所以我们只需要知道前面k - 1个元素总共反转了多少次(更进一步的说我们只关系反转次数的奇偶性)。
	public int minKBitFlipsOpt(int[] A, int K) {
		Deque<Integer> deque = new LinkedList<>();
		int count = 0;
		int queueSize = 0;
		for (int i = 0; i < A.length; i++) {
			while(!deque.isEmpty() && deque.getFirst() + K <= i) {
				deque.removeFirst();
				queueSize--;
			}

			if ((queueSize % 2 == 1 && A[i] == 1) || (queueSize % 2 == 0 && A[i] == 0)) {
				if (i > A.length - K) {
					return -1;
				}
				deque.add(i);
				queueSize++;
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{0,0,0,1,0,1,1,0};
		int k = 3;
		int res = new MinKBitFlips_995().minKBitFlipsOpt(nums, k);
		System.out.println(res);
	}
}
