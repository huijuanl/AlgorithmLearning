package leetcode;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
// 数组中可能包含重复元素
// 用了递归的思想,有low, mid, high三个指针
// 对153题做了一点点改进
// 这道题与153的区别在于：最差情况下时间复杂度为O(n)，平均复杂度为O(logn)，因为可能包含完全相同的数字
// 需要遍历这个数组(1,1,1,1,1,1)这种。153可以看做是154的一个特例。

public class MinimumInRotatedSortedArray_154 {
	public int findMin(int[] nums) {
		return findMin(nums, 0, nums.length - 1);
	}

	public int findMin(int[] nums, int low, int high) {
		if (low == high) {
			return nums[low];
		}
		if (low + 1 == high) {
			return Math.min(nums[low], nums[high]);
		}
		int mid = (low + high) / 2;
		if (nums[low] < nums[mid]) {
			if (nums[low] >= nums[high]) {
				return findMin(nums, mid + 1, high);
			} else return nums[low];
		} else if (nums[low] > nums[mid]) {
			return findMin(nums, low + 1, mid);
		} else {
			if (nums[low] == nums[high]) {
				return findMin(nums, low + 1, high - 1); // //这里记得-1，效率会从20%提示至100%（因为nums[low] == nums[mid] == nums[high]）
			} else if (nums[low] > nums[high]) {
				return  findMin(nums, mid + 1, high);
			} else return nums[low];
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,2,2,0,1,1};
		int res = new MinimumInRotatedSortedArray_154().findMin(nums);
		System.out.println(res);
	}
}
