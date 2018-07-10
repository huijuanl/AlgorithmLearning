package Recursion;
//斐波那契数列:1,1,2,3,5,.......
public class FibonacciSeries {

	//暴力递归,会有2^n次运算，就是一个指数级运算，计算时间复杂度为O(2^n).(节点要从n一直减到1，相当于一颗高度为n的树，树的高度接近于满二叉树，
	//节点个数相当于递归调用次数，大约有2^n数量级个)
	public static int process1(int n){
		if(n==1||n==2)
			return 1;
		else return process1(n-1)+process1(n-2);
	}
	//复杂度约为o(n)
	public static int process2(int n){
		if(n==1||n==2)
			return 1;
		int[]dp=new int[n+1];
		dp[1]=1;
		dp[2]=1;
		for(int i=3;i<=n;i++){
			dp[i]=dp[i-1]+dp[i-2];
			}
		return dp[n];
	}
	public static void main(String[]args){
		int n =12;
		System.out.println(process1(n));
		System.out.println(process2(n));
	}
}
