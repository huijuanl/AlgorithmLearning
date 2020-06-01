package leetcode;

// https://leetcode.com/problems/jump-game-iii/

// 我的思路:
// 利用递归实现: res  = canReach(arr, start + arr[start]) || canReach(arr, start - arr[start])
// 这里有一个需要注意的坑: 可能陷入死循环，比如{3,0,2,1,2}，因此利用了-1来标记一个下标是否已经被访问过了，
// 对value*-1标记已经访问；如果已经访问过了，那么返回false(否则会陷入死循环)；回溯结束之后将value的-1系数去掉，回归原位.
public class JumpGameIII {
	public boolean canReach(int[] arr, int start) {
		if (start < 0 || start >= arr.length) return false;
		if (arr[start] == 0) return true;
		if (arr[start] < 0) return false;
		arr[start] = -arr[start];
		boolean res  = canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
	    arr[start] = -arr[start];
		return res;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{3,0,2,1,2};
		int start = 2;
		boolean res = new JumpGameIII().canReach(nums, start);
		System.out.println("res: " + res);
	}
}
