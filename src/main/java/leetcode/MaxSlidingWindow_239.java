package leetcode;

// https://leetcode.com/problems/sliding-window-maximum/
// 我的思路：用一个双端队列来存储当前窗口内的相关下标
// 以一个指针i在nums数组中移动

// 易错点：
// 队列中存储的当前所有元素中，队头下标所对应的元素永远是最大的，后面的元素可能比队头元素要小，因为要存储
// 后面的信息
// 双端队列要做两个方向的删除：
// (1) 队头元素的删除：队头元素下标已经滑出了当前窗口
// (2) 队尾元素的循环删除: 当当前下标i所对应的元素比queue的队尾元素要大时，循环删除队尾元素，直到队尾元素大于i所对应的元素。最后加入当前i
// (不管队尾元素是否需要循环删除，i下标肯定要入队列的)。典型例子：int[] nums = new int[]{1,3,1,2,0,5};
// int k = 3;
// 其他特殊情况处理: if (k == 1) return nums;
import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow_239 {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k <= 0) return null;
		if (k == 1) return nums;
		int[] res = new int[nums.length - k + 1];
		Deque<Integer> queue = new LinkedList<Integer>();

		for (int i = 0; i < nums.length; i++) {

			if (queue.isEmpty()) {
				queue.addFirst(i);
			} else {
				if (i - queue.getFirst() >= k) {
					queue.removeFirst();
				}

				while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
					queue.pollLast();
				}
				queue.addLast(i);
			}

			if (i >= k - 1) {
				res[i - k + 1] = nums[queue.getFirst()];
			}
		}
		return res;
	}

	public static void main(String[] args) {
//		int[]nums = new int[]{1,3,-1,-3,5,3,6,7};
//		int k = 3;
//		int[] nums = new int[]{1, -1};
//		int k = 1;
		int[] nums = new int[]{1,3,1,2,0,5};
		int k = 3;
		int[] res = new MaxSlidingWindow_239().maxSlidingWindow(nums, k);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + ",");
		}
	}
}
