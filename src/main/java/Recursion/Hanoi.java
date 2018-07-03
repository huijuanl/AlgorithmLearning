package Recursion;
//汉诺塔问题
//如果有n个盘的话,那么移动次数为 2的n次方-1
//利用递归函数来实现汉诺塔问题
//有三个杆，分别定义为left,mid,right。一开始，有N个盘子在left上，现在休要借助mid杆，使得N个盘子移动到right上（过程中不能大压小，只能小压大）
//利用递归来实现：
//首先，将left上的1,2..N-1个盘子移动从left移动到mid上，可以借助right,这是个规模为N-1的子问题
//移动完成之后，将left上的第N号盘子直接从left移动到right上；
//最后，将help杆上的N-1个盘子从help移动到right上，可借助left盘子
//sum用来计数总共需要多少次移动
//时间复杂度为2的n次方（直接根据步数得出）
public class Hanoi {
	public static int printHanoiProcess(int N,String from,String to,String help){
		int sum = 0;
		if(N==1){
			System.out.println("move 1 from "+from+" to "+to );
			return 1;
		}
		sum += printHanoiProcess(N-1, from, help,to);
		System.out.println("move "+N+" from "+from+" to "+to );
		sum++;
		sum+=printHanoiProcess(N-1, help, to, from);
		return sum;

	}

	public static void main(String[] args) {
		for(int i =1;i<5;i++){
			System.out.println("总共有"+i+"个盘子");
			int SUM = printHanoiProcess(i,"左","右","中");
			System.out.println("一共移动了"+ SUM + "次");
			System.out.println("==============================");
		}
	}
}
