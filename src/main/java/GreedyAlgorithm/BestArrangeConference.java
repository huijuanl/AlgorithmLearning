package GreedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数组，里面 是一个个具体的项目)，你来安排宣讲，
//使得一天中宣讲会的数量最多。
//贪心策略：按照最早结束时间来进行选择。
//首先，看最早结束的是哪个项目，然后淘汰掉因为安排了该项目而冲突了的项目，对剩下的项目再以相同的策略进行选择
public class BestArrangeConference {
	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	//�Ƚ�����
	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}
	public static int bestArrange(Program[] programs, int cur) {
		Arrays.sort(programs,new ProgramComparator() );
		int sum =0;
		for(int i=1;i<programs.length;i++){
			if(programs[i].start>=cur){
				cur=programs[i].end;
				sum++;
			}
		}
		return sum;
		
		
	}
	
	
	
}
