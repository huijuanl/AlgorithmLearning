package heap;
//优先队列+比较器实现大根堆或小根堆；不加比较器时，优先队列表示小根堆
//谈心算法一般用到堆结构，也就是一般用到优先队列
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample {
	//实现Comparator接口并重写比较函数。
	//int compare(Object o1, Object o2) 返回一个基本类型的整型
	//只要记住，小根堆指的是：在compare函数中，如果o1>o2,则返回值为正数就可以
	//大根堆指的是：在compare函数中，如果o1>o2，则返回值为负数。其他不用纠结。

	public static class MinheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2; // < 0  o1 < o2  ����
		}

	}

	public static class MaxheapComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1; // <   o2 < o1
		}

	}
 public static void main(String[] args){
	 //priorityQueue默认为小根堆
	 PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new MinheapComparator());
	 queue.add(3);
	 queue.add(2);
	 queue.add(4);
	 queue.add(1);
	 while(!queue.isEmpty()){
		 System.out.println(queue.poll());
	 }
 }
}
