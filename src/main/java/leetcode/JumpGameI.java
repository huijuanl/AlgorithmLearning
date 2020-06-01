package leetcode;

// https://leetcode.com/problems/jump-game/

// 我的思路：动态规划
// res[i]表示由i能经过若干步到达end
// 若存在一个j，使得j > i,且res[j] == true && nums[i] + i > j，那么res[i] = true(即i能够经过若干步到达end)
// 这种时间复杂度是O(n2)
public class JumpGameI {
	public boolean canJump(int[] nums) {
		boolean[]res = new boolean[nums.length];
		res[nums.length-1] = true;
		for (int i = nums.length - 2; i >= 0; i--) {
			for (int j= nums.length - 1; j > i; j--) {
				if (res[j] && (nums[i] + i >= j)) {
					res[i] = true;
					break;
				}
			}
		}
		return res[0];
	}

//	leetcode上给出的时间复杂度为O(n)的思路:(逆向思维)
//	以一个下标lastPos记录最远可以起跳的位置，
//	如果nums[i] + i >= nums[lastPos]，那么当前位置i能跳到lastPos，这也意味着lastPos+1,lastPos+2....都能跳到终点，i即成为了最远可以起跳的位置，更新lastPos为i
//	i--。最终判断lastPos是否为0
//	时间复杂度为O(n)
	public boolean canJumpOpt(int[] nums) {
		int lastPos = nums.length - 1; // lastPos: 记录当前最远可到达的位置
		for (int i = nums.length - 2; i >= 0; i--) {
				if (nums[i] + i >= nums[lastPos]) {
					lastPos = i;
				}
		}
		return lastPos == 0;
	}


	public static void main(String[] args) {
		int[] nums = new int[]{3,2,1,0,4};
		boolean res = new JumpGameI().canJump(nums);
		System.out.println("res: " + res);
	}
}
