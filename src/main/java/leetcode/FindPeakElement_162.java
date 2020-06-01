package leetcode;

// https://leetcode.com/problems/find-peak-element/

// 我的解法: 用得二分查找，但是不够优化
public class FindPeakElement_162 {
	public int findPeakElement(int[] nums) {
		if (nums.length == 1) return 0;
		int res = findPeakElement(nums, 0, nums.length - 1);
		if (res != Integer.MIN_VALUE) return res;
		if (nums[0] > nums[1]) return 0;
		if (nums[nums.length - 2] < nums[nums.length - 1]) return  nums.length - 1;
		return res;
	}

	public int findPeakElement(int[] nums, int low, int high) {
		if (low > high || low < 0 || high > nums.length - 1) {
			return  Integer.MIN_VALUE;
		}

		int mid = (low + high) / 2;

		if (mid - 1 >= 0 && mid + 1 <= nums.length - 1 && nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]) {
			return mid;
		}

		int leftRes = findPeakElement(nums, low, mid - 1);
		if (leftRes != Integer.MIN_VALUE) return leftRes;
		return findPeakElement(nums, mid + 1, high);
	}

	// leetcode优化
	// 局部升序，则峰值在一侧
	public int findPeakElementOpt(int[] nums) {
		int res = findPeakElementOpt(nums, 0, nums.length - 1);
//		if (res != Integer.MIN_VALUE) return res;
//		if (nums[0] > nums[1]) return 0;
//		if (nums[nums.length - 2] < nums[nums.length - 1]) return  nums.length - 1;
		return res;
	}

	public int findPeakElementOpt(int[] nums, int low, int high) {
		if (low == high) return low;
		int mid = (low + high) / 2;
		if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
			return findPeakElementOpt(nums, mid + 1, high);
		} else return findPeakElementOpt(nums, low, mid); // 注意这里是mid而不是mid-1，否则会有死循环
	}

	public static void main(String [] args) {
		int[] nums = new int[]{3,4,2,1,4,5,6};
		int res = new FindPeakElement_162().findPeakElementOpt(nums);
		System.out.print(res);
	}
}
