package Recursion;
//母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只
//母牛，假设不会死。求N年后，母牛的数量。
public class Cow {
    private static int cowNumber1(int n){
       if(n<=3)
           return n+1;
       int sum =0;
       sum = cowNumber1(n-1)+cowNumber1(n-3);
       return sum;
    }
    public static void main(String[] args) {
        //n=0,1,2,3..........
        int n = 4;
        System.out.println(cowNumber1(n));
    }
}
