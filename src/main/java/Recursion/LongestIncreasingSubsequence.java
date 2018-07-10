package Recursion;
import java.util.*;
//返回最长的递增子序列长度（注意，不是子串）
//比如，arr={2,1,5,3,6,4,8,9,7}.最长子序列为{1,3,4,8,9},所以最长递增子序列长度为5
public class LongestIncreasingSubsequence {
	//暴力递归
	public static int process1(int[]arr,int i,int alreadylength,int last){
		if(i==arr.length)
			return alreadylength;
		//last表示arr[0,,,i-1]选出的子序列中的结尾的数
		if(arr[i]>=last)
			return Math.max(
					process1(arr,i+1,alreadylength+1,arr[i]),
					process1(arr,i+1,alreadylength,last)
					);
		else return process1(arr,i+1,alreadylength,last);
			
		}
	//动态规划
	//返回从第i个位置开始的最长递增子序列长度
	public static int process2(int[]arr){
		//生成长度为N的数组dp,dp[i]表示arr[0,..i]中的最大递增子序列长度
		//也可以倒序（生成长度为N的数组dp,dp[i]表示arr[i,..n-1]中的最大递增子序列长度）
		int dp[]=new int[arr.length];
		dp[0]=1;
		for(int i=1;i<dp.length;i++){
			dp[i]=dp[i-1];
			//如果最长子序列以arr[i]结尾，那么在[0,,i-1]中所有比arr[i]小的数都作为倒数第二大的数
			for(int j=i-1;j>=0;j--){
				if((arr[j]<=arr[i])&&((dp[j]+1)>=dp[i]))
					dp[i]=dp[j]+1;
			}
		}
		return dp[dp.length-1];
		
		
	}
	
	
	
		
	
	public static void main(String[]args){
		int[]arr={2,1,5,3,6,4,8,9,7};
		System.out.println(process1(arr, 0,0, Integer.MIN_VALUE));
		System.out.println(process2(arr));
		
	}
}
