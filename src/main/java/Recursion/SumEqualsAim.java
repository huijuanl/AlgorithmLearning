package Recursion;


//给你一个数组arr，和一个整数aim。如果可以任意选择arr中的
//数字，能不能累加得到aim，返回true或者false
//process(i,sum)表示从第i个位置开始判断是否能不能累加得到aim,sum为之前获得的累加和
//从第i个位置开始，每个位置都可以加或者不加，相当于一颗二叉树，最后比较叶节点的值
public class SumEqualsAim {
	public static boolean IsSum1(int[]arr,int aim){
		return process(arr,0,0,aim);
	}
	
	public static boolean process(int[]arr,int i,int sum,int aim){
		if(i==arr.length)
			return sum==aim;
		return process(arr, i+1,sum+arr[i],aim)||process(arr, i+1, sum,aim);
		
		
	}
	//动态规划，根据暴力递归来写，这个函数有点边界方面的问题（不可取）
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
	//自己写的动态规划(用类似于背包问题来求解,在这里，每个物品的重量就等于它的价值。最后比背包问题多一个判断返回值)
	////dp[i][j]表示前0,1...i个数中，任意取k个数，且不超过j时的最大sum为dp[i][j]，若最后的sum=aim，说明为true
	public static boolean IsSum3(int[] arr, int aim) {
		int[][] dp = new int[arr.length][aim+1];
		//第一行初始化
		for(int j=0;j<dp[0].length;j++) {
			if(arr[0]<=j)//j的最大值为aim
			dp[0][j]=arr[0];

		}
		//搭积木
		for(int i=1;i<dp.length;i++)
			for (int j=0;j<dp[0].length;j++)
			{dp[i][j]=dp[i-1][j];
			if(j-arr[i]>=0)
				dp[i][j]=Math.max(dp[i][j],dp[i-1][j-arr[i]]+arr[i]);
			}
		if(dp[dp.length-1][dp[0].length-1]==aim)
			return true;
		else return false;

	}
	public static void main(String[] args) {
		int[] arr = { 1, 4, 8 ,5};
		int aim = 18;
		System.out.println(IsSum1(arr, aim));
//		System.out.println(IsSum2(arr, aim));//有问题
		System.out.println(IsSum3(arr, aim));
	
	}
}
