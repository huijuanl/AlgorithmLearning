package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/course-schedule/
public class CanFinish_207 {

	// 本题退化为：判断如果图中有环，那么返回false；否则返回true
	// 我的思路：dfs, 用到了邻接矩阵和递归 (判断边的状态)
	// 击败了10%
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses];
		for(int[] each: prerequisites) {
			matrix[each[1]][each[0]] = 1;
		}

		for(int[] each: prerequisites) {
			matrix[each[1]][each[0]] = 1;
			boolean res = search(matrix, each[1], each[0], numCourses);
			if (res) return false;
		}
		return true;
	}

	// matrix[i][j] = 2 表示本次已经遍历过该边(用来判断是否存在环)
	// matrix[i][j] = 0 表示该边不存在或表示其他次遍历已经遍历过该边且从该边开始不存在环
	// 从边i,j开始遍历会存在环,则返回true,否则返回false
	public boolean search(int[][]matrix, int i, int j, int numCourse) {
		if (matrix[i][j] == 0) return false;
		if (matrix[i][j] == 2) return true;
		matrix[i][j] = 2;
		for (int k = 0; k < numCourse; k++) {
			boolean res = search(matrix, j, k, numCourse);
			if (res) {
				return true;
			}
		}
		matrix[i][j] = 0;
		return false;
	}

	// leetcode上dfs最优解法: 击败了87% （判断节点的状态）
    //	https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
	// 用的是边缘列表(存储每个节点的邻接节点)而不是邻接矩阵，节省了空间和时间
	class Solution {
		public boolean canFinish(int numCourses, int[][] prerequisites) {
			List<List<Integer>> adjacency = new ArrayList<>();
			for(int i = 0; i < numCourses; i++)
				adjacency.add(new ArrayList<>());
			int[] flags = new int[numCourses];
			for(int[] cp : prerequisites)
				adjacency.get(cp[1]).add(cp[0]);
			for(int i = 0; i < numCourses; i++)
				if(!dfs(adjacency, flags, i)) return false;
			return true;
		}

		private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
			if(flags[i] == 1) return false; // 等于1本次dfs访问过
			if(flags[i] == -1) return true; // 等于-1表示被该节点已经被其他dfs访问过，无需再重复计算
			// 等于0表示还没有被dfs访问过，需要计算
			flags[i] = 1;
			for(Integer j : adjacency.get(i))
				if(!dfs(adjacency, flags, j)) return false;
			flags[i] = -1;
			return true;
		}
	}

	public static void main(String[] args) {
		int numCourse = 5;
		int[][] course = new int[][]{
				{0,1},{1,2},{2,3},{3,4},{4,0}
		};
		boolean res = new CanFinish_207().canFinish(numCourse, course);
		System.out.println(res);
	}
}
