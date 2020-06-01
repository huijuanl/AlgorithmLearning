package leetcode;

// https://leetcode.com/problems/missing-number/
// 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
public class MissingNumber_268 {

	// 我的思路：nums[i]所在位置需要为i，如果不是i，那么存放n，存放n的下标即为缺失的数
	public int missingNumber(int[] nums) {
		int cur = -1; // n + 1所在的下标
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			if (nums[i] == len) {
				cur = i;
			} else if (nums[i] != i){
				swap(nums, i, nums[i]);
				i--;
			}
		}
		return cur == -1? len: cur;
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// leetcode上更巧妙的方法：用异或
	public int missingNumber2(int[] nums) {
		int len = nums.length;
		int missingNum = len;
		for (int i = 0; i < len; i++) {
			missingNum = missingNum ^ nums[i] ^ i;
		}
		return missingNum;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{3,0,1};
		int res = new MissingNumber_268().missingNumber2(nums);
		System.out.println(res);
	}
}
