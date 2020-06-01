package leetcode;

// https://leetcode.com/problems/merge-sorted-array/
// 思路: 从末尾开始进行sort merge操作
// 需要注意的事项: 数组1可能比数组2早merge完，这时候end1=-1，那么此后只需要将数组二中
// 的值依次merge入数组一即可
public class MergeSortedArray {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int end1 = m - 1;
		int end2 = n - 1;
		int i = m + n - 1;
		while (i >= 0 && end2 >= 0) {
			if (end1 >=0 && nums1[end1] > nums2[end2]) {
				nums1[i] = nums1[end1];
				end1--;
			} else {
				nums1[i] = nums2[end2];
				end2--;
			}
			i--;
		}
	}

	public static void main(String[] args) {
		int[] nums1 = new int[]{0};
		int[] nums2 = new int[]{1};
		new MergeSortedArray().merge(nums1, 0, nums2, nums2.length);
		for (int i = 0; i < nums1.length; i++) {
			System.out.println(nums1[i] + ",");
		}
	}
}
