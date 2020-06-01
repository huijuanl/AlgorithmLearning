package leetcode;

// https://leetcode.com/problems/remove-element/solution/
public class RemoveElements {

    // space complexity: O(1)
    // time complexity: start and end traverse at at most 2n steps.
    public static int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = -1;
        int end = 0;
        while(end < nums.length) {
            if (nums[end] != val) {
                nums[++start] = nums[end];
            }
            end++;
        }
        return start + 1;
    }

    // space complexity: O(1)
    // time complexity: start and end traverse at at most n steps.
    public static int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            if (nums[start] == val) {
                nums[start] = nums[end];
                end--;
            } else {
                start++;
            }
        }
        return end + 1;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{2,2};
        int length = removeElement2(nums,2);
        System.out.println(length);
        for (int i=0; i< length; i++) {
            System.out.println(nums[i]);
        }
    }
}
