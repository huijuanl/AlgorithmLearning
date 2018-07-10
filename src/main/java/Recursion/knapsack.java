package Recursion;

import java.util.*;
//背包问题有疑问

//给定两个数组w和v，两个数组长度相等，w[i]表示第i件商品的
//重量，v[i]表示第i件商品的价值。 再给定一个整数bag，要求
//你挑选商品的重量加起来一定不能超 过bag，返回满足这个条件下，你能获得的最大价值。
public class knapsack {
	public static int maxValue1(int[]c,int[]p,int bag){
		List<Integer>values=new LinkedList<Integer>();
		return process2(c,p,0,0,0,bag);
//		process(c,p,0,0,0,bag,values);
//		return Collections.max(values);
	}
	public static void process(int[]c,int[]p,int i ,int alreadyw,int alreadyp,int bag,List values){
		if(i==c.length){
			if(alreadyw<=bag)
				values.add(alreadyp);
			return;
		}
		process(c,p,i+1,alreadyw+c[i],alreadyp+p[i],bag,values);
		process(c,p,i+1,alreadyw,alreadyp,bag,values);
	}
	//暴力递归
	public static int process2(int[]c,int[]p,int i ,int alreadyw,int alreadyp,int bag){
		if(i==c.length){
			if(alreadyw<=bag)
				return alreadyp;
			else return 0;
		}
		return Math.max(process2(c,p,i+1,alreadyw+c[i],alreadyp+p[i],bag),process2(c,p,i+1,alreadyw,alreadyp,bag));
	}
	//process3的返回值不是在叶节点输出。注意比较和process2的不同
	public static int process3(int[] weights, int[] values, int i, int alreadyweight, int bag) {
		if (alreadyweight > bag) {
			return 0;
		}
		if (i == weights.length) {
			return 0; 
		}
		if( alreadyweight + weights[i]<=bag)
			return Math.max(
					
					process3(weights, values, i + 1, alreadyweight, bag),
					
					values[i] + process3(weights, values, i + 1, alreadyweight + weights[i], bag));
		else return process3(weights, values, i + 1, alreadyweight, bag);
	}
	//动态规划,没有理解maxValue2什么意思
	public static int maxValue2(int[]c,int[]p,int bag){
		//alreadyw为j，物品为i，alreadyp为value
		//dp[i][j]表示准备取第i件物品之前，已经获得的重量为j，那么从第i件开始，以某一个策略取完第i到第n个，可以获得的最大价值为dp[i][j]
		
		int[][] dp= new int[p.length+1][bag+1];	
		for(int i= dp.length-2;i>=0;i--)
			for(int j= bag;j>=0;j--)
			{
				if((j+c[i])<=bag)
					dp[i][j]=Math.max(dp[i+1][j+c[i]]+p[i],dp[i+1][j]);
				else dp[i][j]= dp[i+1][j];
			}
		return dp[0][0];
	}
	//动态规划，自己想的
	public static int maxValue3(int[]c,int[]p,int bag){
		//d[i][j]表示从d[0][0]开始，选了i个物品之后，且重量为j时的最大value
		int[][]dp= new int[p.length][bag+1];
		if(c[0]<=bag)
			dp[0][c[0]]=p[0];
		else dp[0][0]=0;
		for(int i=1;i<dp.length;i++){
			//若把dp看成一维数组，
		    //若第i个物品被选入背包，那么只要dp[m](m<i)所对应的重量小于bag，那么dp[i]=Max(dp[m]+p[i])
		    //如果第i个物品没有被选入背包，那么dp[i]=dp[i-1]
		    for(int j=0;j<dp[0].length;j++){
		    	dp[i][j]=dp[i-1][j];	
		    }
		    for(int j=0;j<dp[0].length;j++){
		    	for(int m=i-1;m>=0;m--){
		    		if((c[i]+j)<=bag){
		    			dp[i][c[i]+j]=Math.max(dp[i][c[i]+j],dp[m][j]+p[i]);
		    		}
		    	}
		    }
		}
		int max=Integer.MIN_VALUE;
		for(int j=0;j<dp[0].length;j++)
			max=Math.max(dp[c.length-1][j], max);
			

		return max;
	}
    //最容易理解的方法:
	public static int maxValue4(int[]c,int[]p,int bag){
		//d[i][j]表示前0,1...i件物品，重量不超过j时的最大value
		//那么这不大于j的重量可能包含了第i个物品的重量，也可能没有包含
		int[][]dp= new int[p.length][bag+1];
		//第一行初始化
		for(int j=0;j<dp[0].length;j++){
			if(c[0]<=j)
				dp[0][j]=p[0];

		}
		//搭积木
	    for(int i=1;i<dp.length;i++)
	    	for (int j=0;j<dp[0].length;j++)
			{dp[i][j]=dp[i-1][j];
			if(c[i]<=j)
				dp[i][j]=Math.max(dp[i][j],dp[i-1][j-c[i]]+p[i]);
			}
			//print dp
//		for(int i=1;i<dp.length;i++)
//			for (int j=0;j<dp[0].length;j++){
//			System.out.print(dp[i][j]+" ");
//			if(j==dp[0].length-1)
//				System.out.println();
//
//			}
	    return dp[dp.length-1][dp[0].length-1];
	}

	
	public static void main(String[] args) {
		int[] c = { 3, 2, 4, 7 };
		int[] p = { 5, 6, 3, 7 };
		int bag = 21;
		System.out.println(maxValue1(c, p, bag));
		System.out.println(maxValue2(c, p, bag));
		System.out.println(maxValue3(c, p, bag));
		System.out.println(maxValue4(c, p, bag));
	}
}
