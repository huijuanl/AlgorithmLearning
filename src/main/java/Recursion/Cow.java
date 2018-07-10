package Recursion;
//母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只
//母牛，假设不会死。求N年后，母牛的数量。f(n)=f(n-1)+f(n-3)
public class Cow {
	 //暴力递归,O(2^n)
    private static int process1(int n){
       if(n<=3)
           return n+1;
       int sum =0;
       sum = process1(n-1)+process1(n-3);
       return sum;
    }
    //O(N)的算法
    private static int process2(int n){
    	if(n<=3)
    		return n+1;
    	int[]dp =new int[n+1];
    	for(int i=1;i<=3;i++)
    		dp[i]=i+1;
    	for(int i=4;i<=n;i++){
    		dp[i]=dp[i-1]+dp[i-3];
    		
    	}
    	return dp[n];
    }
    public static void main(String[] args) {
        //n=0,1,2,3..........
        int n = 5;
        System.out.println(process1(n));
        System.out.println(process2(n));
    }
   
    
}
