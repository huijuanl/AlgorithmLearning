package leetcode;

public class FirstMissingPositive_41 {

	public int firstMissingPositive(int[] nums) {
		int missing = 1;
		for (int i = 0; i < nums.length; i++) {
			// 注意条件: (nums[nums[i] - 1] != nums[i])不能少，同一个数可能会出现多次
			while (nums[i] != i + 1 && (0 < nums[i] && nums[i] <= nums.length) && (nums[nums[i] - 1] != nums[i])) {
				swap(nums, i , nums[i] - 1);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == (i + 1)) {
				 if (missing == (i + 1)) {
					 missing = missing + 1;
				}
			}
		}

		return missing;
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;

	}
	public static void main(String[] args) {
		int[] num = new int[]{3,4,-1,1};
		int res = new FirstMissingPositive_41().firstMissingPositive(num);
		System.out.println(res);
	}
}
