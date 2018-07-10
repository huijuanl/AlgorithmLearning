package Recursion;

//题目：给定数组arr,arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，
//再给定一个整数aim代表要找的钱数，求组成aim的最少货币数
//如：当arr=[5,2,3],aim=20时，返回4,因为4张5元组成20元
public class LeastChangeMoney {

    public static int[] tmpArray(int[] arr, int aim) {
        int[] brr = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < brr.length; i++) {
            brr[i] = aim / arr[i];
            sum += brr[i];
        }

        int[] crr = new int[sum];
        int k = 0;
        for (int i = 0; i < brr.length; i++) {
            for (int j = 1; j <= brr[i]; j++) {
                crr[k] = arr[i];
                k++;
            }


        }
        for (int i = 0; i < crr.length; i++) {
            System.out.print(crr[i] + " ");
        }
        System.out.println();
        return crr;

    }

    public static int process1(int[] crr, int i, int alreadyMoney, int aim) {
        if (i == crr.length) {
            if (alreadyMoney == aim) {
                return 0;
            } else return aim + 1;//表示非法，该组方法凑不出来aim
        }
        return Math.min(process1(crr, i + 1, alreadyMoney + crr[i], aim) + 1,
                process1(crr, i + 1, alreadyMoney, aim));


    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2};
        int aim = 30;
        int[] crr = tmpArray(arr, aim);
        int nums = process1(crr, 0, 0, aim);
        if (nums > aim)
            nums = 0;
        System.out.println(nums);
        System.out.println(process2(crr, aim));

    }

    public static int process2(int[] crr, int aim) {
        //dp[i][j]表示和为j时，crr[0...i]上的最小张数
        int[][] dp = new int[crr.length][aim + 1];
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < dp[0].length; j++)
            if (crr[0] == j)
                dp[0][j] = 1;

        int leftup;
        for (int i = 1; i < dp.length; i++)
            for (int j = 0; j < dp[0].length; j++) {
                 leftup= Integer.MAX_VALUE;//Integer.MAX_VALUE表示无法拼出和为j
                //如果加上第[i][j]个位置可以拼出j,那么可能是dp[i - 1][j - crr[i]]加上crr[i]正好能拼出j
                //也可能是前dp[i - 1][j - crr[i]]什么也没拼出，只靠crr[i]拼出了j
                //还可能是前dp[i - 1][j]已经拼出来了
                //还可能是拼不出j(=Integer.MAX_VALUE)
                if (((j - crr[i]) >= 0) && (dp[i - 1][j - crr[i]] != Integer.MAX_VALUE))
                    leftup = Math.min(leftup, dp[i - 1][j - crr[i]] + 1);
                //dp[i - 1][j - crr[i]]不能拼出j，但是第[i][j]个位置正好能拼出j
                if (((j - crr[i]) == 0) && (dp[i - 1][j - crr[i]] == Integer.MAX_VALUE))
                    leftup = Math.min(leftup,1);
                dp[i][j] = Math.min(leftup, dp[i - 1][j]);
            }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");

            }
            System.out.println();
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}


