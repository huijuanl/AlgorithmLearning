package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// 优化思路： 用两个指针来实现
// margin: [0,margin-1]表示已经符合条件的排好序的区间
// [margin, start) 表示乱序区间，这些区间的数据可以去掉
// 从start开始，nums[margin] = nums[start], 然后start和margin各移动一位，
// 如果start处的值和之前的value相同，则继续使得nums[margin] = nums[start]，且start++;margin++
// start跳到下一个不同的值
// （这里不需要进行swap，直接覆盖掉就好了）
public class RemoveDuplicatesII {
	public int removeDuplicates(int[] nums) {
		if (nums.length <= 2) return nums.length;
		int margin = 0;
		int start = 0;
		while (start < nums.length) {
			int value = nums[start];
			nums[margin] = nums[start];
			margin++;
			start++;
			if (start < nums.length && nums[start] == value) {
				nums[margin] = nums[start];
				margin++;
				start++;
			}
			while (start < nums.length && nums[start] == value) {
				start++;
			}
		}
		return margin;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,1,1,2,2,2,3,3};
		int len = new RemoveDuplicatesII().removeDuplicates(nums);
		for (int i = 0; i < len; i++) {
			System.out.println(nums[i] + ",");
		}

	}
}
