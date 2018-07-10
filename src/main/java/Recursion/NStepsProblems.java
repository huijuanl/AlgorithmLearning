package Recursion;
//有n级台阶，有个人每次上一级或者两级，问有多少种走完n级台阶的方法
public class NStepsProblems {

	public static void process1(int already ,int n,int[]nums){
		if(already>n)
			return;
		if(already==n){
			 nums[0]++;
			 return;
		}
		process1(already+1,n, nums);
		process1(already+2, n, nums);
	}
	public static int process2(int already ,int n){
		if(already>n)
			return 0;
		else if(already==n)
			 return 1;
		else return process2(already+1,n)+process2(already+2, n);
	}
	public static int process3(int n){
		int[]dp =new int[n+1];
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=n;i++){
			dp[i]=dp[i-1]+dp[i-2];
		}
		return dp[n];
		
	}
	
	public static void main (String[]args){
		int n = 4;
		int[]nums=new int[1];
		process1(0,n,nums);
		System.out.println(nums[0]);
		System.out.println(process2(0, n));
		System.out.println(process3(n));
		
		
		
	}
}
