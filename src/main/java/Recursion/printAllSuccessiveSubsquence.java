package Recursion;
//当一个位置可以被选中或不被选中放入前缀，表示这个位置之前前缀为空串或者这个位置的前一个位置已经被选中了
//flag== false表示上一个位置没有被选中，那么下一个位置也不能被选中
public class printAllSuccessiveSubsquence {
	public static void printallSuccessiveSubsquence(char[]str,int i,boolean flag,String result){
		if(i == str.length){
			System.out.println(result);
			return;
		}
		if(flag == true||result.equals("")){
			printallSuccessiveSubsquence(str,i+1, true,result+String.valueOf(str[i]));
			printallSuccessiveSubsquence(str,i+1, false,result);
		}
		else{
			printallSuccessiveSubsquence(str,i+1, false,result);
			
		}
		
	}
	public static void main(String[] args) {
		String test1 = "abc";
		printallSuccessiveSubsquence(test1.toCharArray(),0,true,"");
		System.out.println("======");

		String test2 = "acc";
		printallSuccessiveSubsquence(test2.toCharArray(),0,true,"");
		System.out.println("======");

	}

}
