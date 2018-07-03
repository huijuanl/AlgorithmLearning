package Recursion;
//字符串的全排列/不重复全排列
//输入一个字符串，打印这个字符串中字符的全排列。
//eg：
//输入：abc
//输出：abc acb bac bca cab cba
//为方便起见，用123来示例下。123的全排列有123、132、213、231、312、321这六种。首先考虑213和321这二个数是如何得出的。
// 显然这二个都是123中的1与后面两数交换得到的。然后可以将123的第二个数和每三个数交换得到132。同理可以根据213和321来得231和312。
// 因此可以知道——全排列就是从第一个数字起每个数分别与它后面的数字交换。找到这个规律后，递归的代码就很容易写出来了
//char[]str每次交换之后的字符数组，交换第i个位置与第(i,i+1,,,length-1)的值得到新的字符数组，然后新的字符数组再进行重复的交换
//交换停止的条件为i来到了最后一个位置
public class printAllPermutations {


    public static void printAllPermutations1(char[] str,int i){//全排列，输出可以含有重复值
        if(i== str.length-1){
            System.out.println(str);
            return;
        }
        for(int k =i;k<str.length;k++){//交换第i个位置和第k个位置
            swap(str,i,k);
            printAllPermutations1(str,i+1);
            swap(str,i,k);//递归到某一个叶结点输出后，结束本轮递归，那么str要恢复为这次递归之前的值（返回到叶结点的父结点）

        }

    }
    public static void printAllPermutations2(char[] str,int i){//全排列，输出不能含有重复值
        //去重的全排列就是从第一个数字起:第i个数与第j个数交换时，要求[i,j)中没有与第j个数相等的数。
        if(i== str.length-1){
            System.out.println(str);
            return;
        }
        for(int k =i;k<str.length;k++){//交换第i个位置和第k个位置,k=i,i+1....length-1
            boolean flag =false;
            for (int m = i; m<k ; m++) {//第i个数与第k个数交换时，要求[i,k)中没有与第k个数相等的数。
                 if(str[m]== str[k]){
                     flag =true;
                     break;
                    }

                }
            if(!flag) {
                swap(str, i, k);
                printAllPermutations2(str, i + 1);
                swap(str, i, k);//递归到某一个叶结点输出后，结束本轮递归，那么str要恢复为这次递归之前的值（返回到叶结点的父结点）
            }
        }


    }
    public static void main(String[] args) {
//        String test1 = "abc";
//        printAllPermutations1(test1.toCharArray(),0);
//        System.out.println("======");
//        printAllPermutations2(test1.toCharArray(),0);
//        System.out.println("======");
//
        String test2 = "acc";
//        printAllPermutations1(test2.toCharArray(),0);
//        System.out.println("======");
        printAllPermutations2(test2.toCharArray(),0);
        System.out.println("======");
    }
    public static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

}
