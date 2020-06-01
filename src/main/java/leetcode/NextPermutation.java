package leetcode;

// https://leetcode.com/problems/next-permutation/solution/
public class NextPermutation {

    // (1) 找到一个最大逆序数组，将i与逆序数组中比i稍大的元素交换；
    // (2) 翻转逆序数组
    public static void nextPermutation(int[] nums) {
        int start = nums.length - 2;
        int end = nums.length - 1;
        while (start >= 0) {
            if (nums[start] < nums[end]) {
                swap(nums, start, getBiggerIndex(nums, end, nums[start]));
                break;
            } else if (nums[start] == nums[end]){
                start--;
            } else {
                end = start;
                start--;
            }
        }
        if (start < end) {
            for (int i = start + 1; i <= (nums.length + start) / 2; i++) {
                swap(nums, i, nums.length + start - i);
            }
        }
    }

    public static int getBiggerIndex(int[]nums, int i, int val) {
        int low = i;
        while (low < nums.length) {
            if (nums[low] > val) {
                low++;
            } else break;
        }
        return low - 1;
    }
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2};
        nextPermutation(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
