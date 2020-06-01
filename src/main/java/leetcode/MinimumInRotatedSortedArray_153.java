package leetcode;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// 用了递归的思想,有low, mid, high三个指针
// (1) nums[low] < nums[mid]:
// 那么左边肯定是有序的，右半边可能有序也可能无序，最小值可能在左半边也可能在右半边，
// 需要讨论nums[low]与nums[high]的关系。如果nums[low] < nums[high]，那么nums[low]即为最小值
// 否则最小值在右半边
// (4,5,6,7,1,2), (1,2,3,4,5,6)
// (2) nums[low] > nums[mid]: 那么左边肯定是无序的，右半边有序，最小值肯定在左半边
// 时间复杂度：和二分搜索一样 O(logN)
//空间复杂度：O(1)O(1)
public class MinimumInRotatedSortedArray_153 {
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
			if (nums[low] > nums[high]) {
				return findMin(nums, mid + 1, high);
			} else return nums[low];
		} else {
			return findMin(nums, low + 1, mid);
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{5,1,3};
		int res = new MinimumInRotatedSortedArray_153().findMin(nums);
		System.out.println(res);
	}
}
