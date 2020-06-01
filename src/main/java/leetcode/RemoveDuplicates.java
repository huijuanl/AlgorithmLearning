package leetcode;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        int start = 0;
        while (max < nums.length) {
            if (nums[max] != nums[start]) {
                nums[++start] = nums[max];
            }
            max++;
        }
        return start + 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = removeDuplicates(nums);
        System.out.println(length);
        // {0,1,2,3,4}
        for (int i=0; i< length; i++) {
            System.out.println(nums[i]);
        }
    }
}
