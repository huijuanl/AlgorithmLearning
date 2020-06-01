package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/course-schedule-ii/
public class CanFinish_210 {

	// 这道题基于第207题，只需要每次dfs只会，当前节点放入队头即可
	LinkedList<Integer> res = new LinkedList<>(); // 返回课程的学习顺序
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		List<List<Integer>> adjacency = new ArrayList<>();

		for(int i = 0; i < numCourses; i++)
			adjacency.add(new ArrayList<>());
		int[] flags = new int[numCourses];
		for(int[] cp : prerequisites)
			adjacency.get(cp[1]).add(cp[0]);
		for(int i = 0; i < numCourses; i++)
			if(!dfs(adjacency, flags, i)) return new int[]{};
		return res.stream().mapToInt(i->i).toArray();
	}

	private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
		if(flags[i] == 1) return false; // 等于1本次表示dfs访问过
		if(flags[i] == -1) return true; // 等于-1表示被该节点已经被其他dfs访问过，无需再重复计算
		// 等于0表示还没有被dfs访问过，需要计算
		flags[i] = 1;
		for (Integer j : adjacency.get(i)) {
			boolean result = !dfs(adjacency, flags, j);
			if (result) return false;
		}
		res.addFirst(i); // 放到首位
		flags[i] = -1;
		return true;
	}

	public static void main(String[] args) {
		int numCourse = 4;
		int[][] course = new int[][]{
				{1,0},{2,0},{3,1},{3,2}
		};
		int[] res = new CanFinish_210().findOrder(numCourse, course);
		for (int i: res) {
			System.out.println(i + ",");
		}
	}
}
