package leetcode;

// 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
// 假设只有一个重复的整数，找出这个重复的数。
// https://leetcode.com/problems/find-the-duplicate-number/
//不能更改原数组（假设数组是只读的）。
//只能使用额外的 O(1) 的空间。
//时间复杂度小于 O(n2) 。
//数组中只有一个重复的数字，但它可能不止重复出现一次。
public class FindDuplicate_287 {

	// 这道题比较巧妙
	// 数组中有重复的数字 《==》 链表存在环
	// 找到重复的数字 《==》找到环的入口
	// 题解见: https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
	// 与142题检测链表是否有环相似
	public int findDuplicate(int[] nums) {
		int slow = 0;
		int fast = 0;
		slow = nums[slow];
		fast = nums[nums[fast]];
		while(slow != fast){
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		int pre1 = 0;
		int pre2 = slow;

		// 从链表头和相遇点开始走，必然会相遇，且相遇点即为环入口点（有推导公式）
		while(pre1 != pre2){
			pre1 = nums[pre1];
			pre2 = nums[pre2];
		}
		return pre1;
	}
}
