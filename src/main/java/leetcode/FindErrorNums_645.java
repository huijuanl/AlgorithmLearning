package leetcode;

// https://leetcode.com/problems/set-mismatch/
public class FindErrorNums_645 {

	// 该题不能用第287题的思路，下标不一样，可能会造成数组溢出
	// 我的思路:
	public int[] findErrorNums(int[] nums) {

		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i + 1) {
				if (nums[nums[i] - 1] == nums[i]) {
					res[0] = nums[i];
					break;
				} else {
					swap(nums, i, nums[i] - 1);
				}
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				res[1] = i + 1;
			}
		}
		return res;
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// leetcode上的思路：不用实际交换，而是标记为负数
	// 可以根据访问一个数字时它是否为负数找出重复数字
	public int[] findErrorNumsOpt(int[] nums) {

		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (nums[Math.abs(nums[i]) - 1] < 0) {
				res[0] = Math.abs(nums[i]);
			} else {
				nums[Math.abs(nums[i]) - 1] *= (-1);
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				res[1] = i + 1;
			}
		}
		return res;
	}


	public static void main(String[] args) {
		int[] nums = new int[]{1,3,2,2};
		int[] res = new FindErrorNums_645().findErrorNumsOpt(nums);
		System.out.println(res[0] + "," + res[1]);

	}
}
