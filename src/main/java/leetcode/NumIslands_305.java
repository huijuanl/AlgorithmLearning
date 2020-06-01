package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/number-of-islands-ii/
public class NumIslands_305 {

	// 每次添加新的大陆，都检查其邻居是否已经是大陆，如果已经是大陆了，比较与自身大陆归属的岛屿是否相同

	// 每次增加一个元素之后，原来的岛屿数量可能增加，也可能减少多个
	// 下面是leetcode上的思路：
		class UnionFind {
			int count; // # of connected components
			int[] parent;
			int[] rank;

			public UnionFind(char[][] grid) { // for problem 200
				count = 0;
				int m = grid.length;
				int n = grid[0].length;
				parent = new int[m * n];
				rank = new int[m * n];
				for (int i = 0; i < m; ++i) {
					for (int j = 0; j < n; ++j) {
						if (grid[i][j] == '1') {
							parent[i * n + j] = i * n + j;
							++count;
						}
						rank[i * n + j] = 0;
					}
				}
			}

			public UnionFind(int N) { // for problem 305 and others
				count = 0;
				parent = new int[N];
				rank = new int[N];
				for (int i = 0; i < N; ++i) {
					parent[i] = -1;
					rank[i] = 0;
				}
			}

			public boolean isValid(int i) { // for problem 305
				return parent[i] >= 0;
			}

			void setParent(int i)
			{
				if(parent[i] != i)
					++count;
				parent[i] = i;
			}

			public int find(int i) { // path compression
				if (parent[i] != i) parent[i] = find(parent[i]);
				return parent[i];
			}

			public void union(int x, int y) { // union with rank
				int rootx = find(x);
				int rooty = find(y);
				if (rootx != rooty) {
					if (rank[rootx] > rank[rooty]) {
						parent[rooty] = rootx;
					} else if (rank[rootx] < rank[rooty]) {
						parent[rootx] = rooty;
					} else {
						parent[rooty] = rootx; rank[rootx] += 1;
					}
					--count;
				}
			}

			public int getCount() {
				return count;
			}
		}

		public List<Integer> numIslands2(int m, int n, int[][] positions) {
			List<Integer> ans = new ArrayList<>();
			UnionFind uf = new UnionFind(m * n);

			for (int[] pos : positions) {
				int r = pos[0], c = pos[1];
				List<Integer> overlap = new ArrayList<>();

				if (r - 1 >= 0 && uf.isValid((r-1) * n + c)) overlap.add((r-1) * n + c);
				if (r + 1 < m && uf.isValid((r+1) * n + c)) overlap.add((r+1) * n + c);
				if (c - 1 >= 0 && uf.isValid(r * n + c - 1)) overlap.add(r * n + c - 1);
				if (c + 1 < n && uf.isValid(r * n + c + 1)) overlap.add(r * n + c + 1);

				int index = r * n + c;
				uf.setParent(index);
				for (int i : overlap) uf.union(i, index);
				ans.add(uf.getCount());
			}

			return ans;
		}


		// 用HashMap的方式比较好理解，但是耗时长
		public List<Integer> numIslands2HashMap(int m, int n, int[][] positions) {
			List<Integer> ans = new ArrayList<>();
			HashMap<Integer, Integer> land2id = new HashMap<Integer, Integer>();
			int num_islands = 0;
			int island_id = 0;

			for (int[] pos : positions) {

				int r = pos[0], c = pos[1];
				if (land2id.containsKey(r * n + c)) {
					ans.add(num_islands);
					continue;
				}
				Set<Integer> overlap = new HashSet<Integer>();

				if (r - 1 >= 0 && land2id.containsKey((r-1) * n + c)) {
					overlap.add(land2id.get((r-1) * n + c));
				}
				if (r + 1 < m && land2id.containsKey((r+1) * n + c)) {
					overlap.add(land2id.get((r+1) * n + c));
				}
				if (c - 1 >= 0 && land2id.containsKey(r * n + c - 1)) {
					overlap.add(land2id.get(r * n + c - 1));
				}
				if (c + 1 < n && land2id.containsKey(r * n + c + 1)) {
					overlap.add(land2id.get(r * n + c + 1));
				}

				if (overlap.isEmpty()) {
					++num_islands;
					land2id.put(r * n + c, island_id++);
				} else if (overlap.size() == 1) {
					land2id.put(r * n + c, overlap.iterator().next());
				} else {
					int root_id = overlap.iterator().next();
					for (Map.Entry<Integer, Integer> entry : land2id.entrySet()) {
						int k = entry.getKey();
						int id = entry.getValue();
						if (overlap.contains(id)) {
							land2id.put(k, root_id);
						}
					}
					land2id.put(r * n + c, root_id);
					num_islands -= (overlap.size() - 1);
				}
				ans.add(num_islands);
			}

			return ans;
		}

	public static void main(String[] args) {
		int m = 3, n = 3;
		int[][] positions = new int[][]{{0,0}, {0,1}, {1,2}, {2,1}};

		List<Integer> res = new NumIslands_305().numIslands2(m, n, positions);

		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));

		}
	}
}
