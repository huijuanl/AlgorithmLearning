package Recursion;

import Hash.Hashmap;

//经典动态规划问题
//给定两个字符串str1和str2，返回两个字符串中的最长公共子序列。
//例如 str1="1A2C3D4B56", str2="B1D23CA45B6A";
//"123456"或者"12C4B6"都是最长公共子序列的长度，返回哪一个都行
//如果是要获得最长公共子序列的话，可以增加一个hashmap保存长度以及对应的序列?
public class LongestCommonSubsequence{

    public static  int process1(char[]str1,int i,char[]str2,int j){
        if(i==str1.length||j==str2.length)
            return 0;
        if(str1[i]!=str2[j])
            return Math.max(process1(str1,i+1,str2,j),process1(str1,i,str2,j+1));
        else return process1(str1,i+1,str2,j+1)+1;


    }

    public static  int findLongestCommonSubsequence(char[]str1,char[]str2){
        int[][]dp = new int[str1.length][str2.length];
        //dp[i][j]表示str1[0...i]和str2[0...j]的最长公共子序列长度
        //一旦dp[0][j]被设置为1,那么dp[0][j,...n-1]均为1
        boolean flag =false;
        for(int j =0;j<str2.length;j++){
            if(str1[0]==str2[j]||flag) {
                dp[0][j] = 1;
                flag = true;
            }
            else dp[0][j]=0;
        }
        flag =false;
        for(int i =0;i<str1.length;i++){
            if(str1[i]==str2[0]||flag){
                dp[i][0]=1;
                flag=true;
            }
            else dp[i][0]=0;
        }
        ////其他位置，dp[i][j]的可能值来自于以下三种情况:
        //  （1）(str1[i]!=str2[j])dp[i][j]=dp[i][j-1]
        //  (2) (str1[i]!=str2[j]) dp[i][j]=dp[i-1][j]
        //  (3)如果str1[i]=str2[j]，那么dp[i][j]=dp[i-1][j-1]+1
        for(int i=1;i<str1.length;i++)
            for(int j=1;j<str2.length;j++){
            if(str1[i]!=str2[j])
             dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
            else
                dp[i][j] =dp[i-1][j-1]+1;
            }

        return dp[str1.length-1][str2.length-1];



    }
    public static void main(String[]args){
        String str1="ABCBDAB";
        String str2="BDCABA";
        String str3="1A2C3D4B56";
        String str4="B1D23CA45B6A";
        String str5="13456778";
        String str6="13456688";
        String str7="DJWIAQWAGAA";
        String str8="JWSJIQDWFWFGEDWQAAA";
        System.out.println(findLongestCommonSubsequence(str1.toCharArray(),str2.toCharArray()));
        System.out.println(findLongestCommonSubsequence(str3.toCharArray(),str4.toCharArray()));
        System.out.println(findLongestCommonSubsequence(str5.toCharArray(),str6.toCharArray()));
        System.out.println(findLongestCommonSubsequence(str7.toCharArray(),str8.toCharArray()));
        System.out.println("=========================================================");
        System.out.println(process1(str1.toCharArray(),0,str2.toCharArray(),0));
        System.out.println(process1(str3.toCharArray(),0,str4.toCharArray(),0));
        System.out.println(process1(str5.toCharArray(),0,str6.toCharArray(),0));
        System.out.println(process1(str7.toCharArray(),0,str8.toCharArray(),0));
        System.out.println("=========================================================");
    }





}