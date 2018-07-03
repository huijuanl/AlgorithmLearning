package Recursion;
//打印一个字符串所有的子序列.如abc的子序列为：a,ab,ac,abc,b,bc,c,""（八个），
//相当于每个位置为0或一
//从第一个位置开始（按照二叉树来进行），最后叶节点为输出值
//要把每一层的输出结果给下一层作为前缀：前缀相同的情况下加上第i个位置或不加第i个位置的值，作为新的前缀
public class printAllSubsquence {
	public static void printAllSubsquenceString(char[]str,int i,String result){
		if(i == str.length){
			System.out.println(result);
			return;
		}
		printAllSubsquenceString(str, i+1, result+String.valueOf(str[i]));
		printAllSubsquenceString(str, i+1, result);
	}
	public static void main(String[] args) {
		String test1 = "abc";
		printAllSubsquenceString(test1.toCharArray(),0,"");
		System.out.println("======");

		String test2 = "acc";
		printAllSubsquenceString(test2.toCharArray(),0,"");
		System.out.println("======");

	}
}
