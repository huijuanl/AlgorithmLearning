package leetcode;

// https://leetcode.com/problems/sort-colors/
public class SortColors {

	// 思路1：需要遍历两次数组
	public void sortColors(int[] nums) {
		int count0 = 0;
		int count1 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				count0++;
			} else if (nums[i] == 1) {
				count1++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (i < count0) {
				nums[i] = 0;
			} else if (i < count1 + count0) {
				nums[i] = 1;
			} else nums[i] = 2;
		}
	}

	public void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// 思路2： 只遍历一遍数组，边遍历边进行交换
	// 三指针:
	// start: 已排好序的0的最右边界
	// end: 已排好序的2的最左边界
	// i: current pointer
	// 需要注意的两点：
	// (1) 当i和start交换之后，i位置处还需要进行比较value是否为0或者为2
	// (2) 当i=start时,start不需要自增，i++即可
	public void sortColors2(int[] nums) {
		int start = -1;
		int end = nums.length;
		int i = 0;
		while (start < end && i < end) {
			if (nums[i] == 0) {
				if (start != i) {
					start++;
					swap(nums, i, start);
				} else i++;

			} else if (nums[i] == 1) {
				i++;
			} else {
				end--;
				swap(nums, i, end);
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,0};
		new SortColors().sortColors2(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ",");
		}
	}
}
