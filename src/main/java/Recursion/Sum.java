package Recursion;


//给你一个数组arr，和一个整数aim。如果可以任意选择arr中的
//数字，能不能累加得到aim，返回true或者false
//process(i,sum)表示从第i个位置开始判断是否能不能累加得到aim,sum为之前获得的累加和
//从第i个位置开始，每个位置都可以加或者不加，相当于一颗二叉树，最后比较叶节点的值
public class Sum {
	public static boolean IsSum1(int[]arr,int aim){
		return process(arr,0,0,aim);
	}
	
	public static boolean process(int[]arr,int i,int sum,int aim){
		if(i==arr.length)
			return sum==aim;
		return process(arr, i+1,sum+arr[i],aim)||process(arr, i+1, sum,aim);
		
		
	}
	//动态规划，根据暴力递归来写
	public static boolean IsSum2(int[] arr, int aim) {
		int total = 0;
		for(int i = 0;i<arr.length;i++){
			total+=arr[i];
		}
		boolean[][] dp = new boolean[arr.length + 1][total + 1];
		for (int j = 0; j < dp[0].length; j++) {
			dp[arr.length][j] = false;
		}
		dp[arr.length][aim] = true;
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j =total; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + arr[i] <= aim) {
					dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
				}
			}
		}
		return dp[0][0];
	}
	public static void main(String[] args) {
		int[] arr = { 1, 4, 8 };
		int aim = 7;
		System.out.println(IsSum1(arr, aim));
		System.out.println(IsSum2(arr, aim));
	
	}
}
