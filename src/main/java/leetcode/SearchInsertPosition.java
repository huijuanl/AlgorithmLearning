package leetcode;

// https://leetcode.com/problems/search-insert-position/
public class SearchInsertPosition {
	public int searchInsert(int[] nums, int target) {
		return search(nums, target, 0, nums.length - 1);
	}

	public int search(int[] nums, int target, int low, int high) {
		if (low > high) return 0;
		int mid = (low + high) / 2;
		if (nums[mid] == target)
			return mid;
		if (nums[low] > target) return low;
		if (nums[high] < target) return high + 1;
		if (nums[mid] < target) {
			return search(nums, target, mid + 1, high);
		} else {
			return search(nums, target, low, mid);
		}
	}

	public static void main(String[] args) {
		SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
		int[] nums = new int[]{1,3,5,6};
		int target = 0;
		int res = searchInsertPosition.searchInsert(nums, target);
		System.out.println(res);
	}
}
