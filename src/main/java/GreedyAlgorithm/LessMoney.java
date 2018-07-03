package GreedyAlgorithm;

import java.util.PriorityQueue;


//一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的 金条，不管切成长度多大的两半，都要花费20个铜板。一群人想整分整块金 条，怎么分最省铜板
//例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60. 金条要分成10,20,30三个部分。 如果， 先把长度60的金条分成30和30，花费60 再把长度30
//金条分成10和20，花费30 一共花费90铜板。
//输入一个数组，返回分割的最小代价。
//最小代价指的是霍夫曼树的结果
//建立好霍夫曼树之后，自上往下的数据表示分割的长度
public class LessMoney {
	public static int lessMoney(int[] arr) {
		PriorityQueue<Integer>queue = new PriorityQueue<Integer>();
		//�Ƚ���һ��С����
		for(int i=0;i<arr.length;i++){
			queue.add(arr[i]);
		}
		int sum = 0;
		int min1=0;
		while(!queue.isEmpty()){
			min1 =queue.poll();
			if(!queue.isEmpty()){
				int min2 = queue.poll();
				queue.add(min1+min2);
				sum +=(min1+min2);
			}
		}
		return sum;	
		//����Min1��ֵΪ���ڵ��ֵ
	}
	public static void main(String[] args) {
		// solution
		int[] arr = { 6, 7, 8, 9 };
		System.out.println(lessMoney(arr));

		int[] arrForHeap = { 3, 5, 2, 7, 0, 1, 6, 4 };

		// min heap��Ĭ��Ϊ��С��
		PriorityQueue<Integer> minQ1 = new PriorityQueue<>();
		for (int i = 0; i < arrForHeap.length; i++) {
			minQ1.add(arrForHeap[i]);
		}
		while (!minQ1.isEmpty()) {
			System.out.print(minQ1.poll() + " ");
		}
		System.out.println();

	}

}
