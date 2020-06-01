package leetcode;

// https://leetcode.com/problems/search-in-rotated-sorted-array/submissions/
// 需要记住：总有一边是有序的
public class SearchInRotatedSortedArray {

    public int subSearch(int[] nums, int target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        /*
        注意边界值： num[low]或者num[high]恰好为target的时候，直接return，否则执行后续逻辑会报错
        eg: [3,1] , 1 返回1
        */
        if (nums[low] == target) {
            return low;
        }
        if (nums[high] == target) {
            return high;
        }
        if (nums[low] < nums[mid]) {
            if (nums[low] <= target && target < nums[mid]) {
                return subSearch(nums, target, low, mid - 1);
            } else {
                return subSearch(nums, target, mid + 1, high);
            }
        } else {
            if (nums[mid] < target && target <= nums[high]) {
                return subSearch(nums, target, mid + 1, high);
            } else {
                return subSearch(nums, target, low, mid - 1);
            }
        }
    }
    public int search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        return subSearch(nums, target, low, high);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        int[] nums = new int[]{3,1};
        int target = 1;
        int res = s.search(nums, target);
        System.out.println(res);
    }
}
