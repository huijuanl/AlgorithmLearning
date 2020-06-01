package leetcode;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class SearchRange {

	public int[] search(int[] nums, int target, int low, int high) {

		if (low > high) {
			return new int[]{-1, -1};
		}

		int mid = (low + high) / 2;

		if (nums[mid] < target) {
			return search(nums, target, mid + 1, high);
		} else if (nums[mid] > target) {
			return search(nums, target, low, mid - 1);
		} else {
			// nums[mid] == target
			int[] leftRes = search(nums, target, low, mid - 1);
			int[] rightRes = search(nums, target, mid + 1, high);
			// merge
			if (leftRes[0] != -1) {
				return new int[]{
						leftRes[0],
						rightRes[0] == -1 ? mid : rightRes[1] == -1 ? rightRes[0] : rightRes[1]
				};
			} else {
				return new int[]{
						leftRes[1] == -1 ? mid : leftRes[1],
						rightRes[0] == -1 ? mid : rightRes[1] == -1 ? rightRes[0] : rightRes[1]
				};
			}
		}
	}

	public int[] searchRange(int[] nums, int target) {
		return search(nums, target, 0, nums.length - 1);
	}

	public int searchLeftOrRight(int[] nums, int target, int low, int high, boolean isLeft) {
		if (low > high) return -1;
		int mid = (low + high) / 2;
		if (isLeft) {
			if (nums[mid] == target) {
				int index = searchLeftOrRight(nums, target, low, mid - 1, isLeft);
				return index == -1? mid: index;
			} else {
				return searchLeftOrRight(nums, target, mid + 1, high, isLeft);
			}
		} else {
			if (nums[mid] == target) {
				int index = searchLeftOrRight(nums, target, mid + 1, high, isLeft);
				return index == -1?mid: index;
			} else return searchLeftOrRight(nums, target, low, mid - 1, isLeft);
		}

	}

	public int[] searchOpt(int[] nums, int target, int low, int high) {
		if (low > high) {
			return new int[]{-1, -1};
		}

		int mid = (low + high) / 2;

		if (nums[mid] < target) {
			return search(nums, target, mid + 1, high);
		} else if (nums[mid] > target) {
			return search(nums, target, low, mid - 1);
		} else {
			// nums[mid] == target
			int leftIndex = searchLeftOrRight(nums, target, 0, mid - 1, true);
			int rightIndex = searchLeftOrRight(nums, target, mid + 1, nums.length - 1, false);
			return
					new int[]{
							leftIndex == -1? mid: leftIndex,
							rightIndex == -1? mid: rightIndex
					};

		}
	}

	public static void main(String[] args) {
		SearchRange searchRange = new SearchRange();
		int[] nums = new int[]{5,6,7,7,7,10};
		int target = 9;
//		int[] res = searchRange.searchRange(nums, target);
		int[] res = searchRange.searchOpt(nums, target, 0, nums.length - 1);
		System.out.println("res[0]: " + res[0]);
		System.out.println("res[1]: " + res[1]);
	}
}