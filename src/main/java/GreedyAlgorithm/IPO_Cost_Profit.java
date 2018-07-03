package GreedyAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
//输入： 参数1，正数数组costs 参数2，正数数组profits 参数3，正数k 参数4，正数m
//costs[i]表示i号项目的花费 profits[i]表示i号项
//说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个 项目。
//输出： 你最后获得的最大钱数。
//过程：先准备一个小根堆，按照谁的花费低，谁来到小根堆的根部建堆
//取小根堆堆顶，若小根堆堆顶不大于启动资金，则弹出堆顶到大根堆中（小根堆表示未解锁项目）
//大根堆建堆：按照收益最高的放于堆顶来建堆（大根堆表示已解锁项目）
//弹出大根堆堆顶，表示启动相应的项目
//更新启动资金为上一个项目获得收益之后的资金，然后从小根堆继续弹出不大于启动资金的项目放入大根堆
//循环，直到大根堆中没有项目或者已弹出了K个项目时停止
public class IPO_Cost_Profit {
	public static class Node {
		public int p;
		public int c;

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
	public static class MinheapComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c; // < 0  o1 < o2  ����
		}

	}
	public static class MaxheapComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p-o1.p; // < 0  o1 < o2  ����
		}

	}
	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Costs) {
		PriorityQueue<Node>MinHeapqueue = new PriorityQueue<Node>(new MinheapComparator());
		PriorityQueue<Node>MaxHeapqueue = new PriorityQueue<Node>(new MaxheapComparator());
		for(int i =0;i<Costs.length;i++){
			MinHeapqueue.add(new Node(Profits[i], Costs[i]));
		}
		int i =1;
		while(i<=k){
			while(!MinHeapqueue.isEmpty()&&MinHeapqueue.peek().c<=W){
				MaxHeapqueue.add(MinHeapqueue.poll());
			}
			if(MaxHeapqueue.isEmpty())
				return W;
			W +=MaxHeapqueue.poll().p;
			i++;
		}
		return W;
	}
}
