package leetcode;

// https://leetcode.com/problems/number-of-islands/
public class NumIslands_200 {
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					dfsSearch(grid, i, j);
					count += 1;
				}

			}
		}
		return count;
	}

	public void dfsSearch(char[][] grid, int i, int j) {
		//
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;

		if (grid[i][j] == '0' || grid[i][j] == '2') return;
		grid[i][j] = '2';
		dfsSearch(grid, i + 1, j);
		dfsSearch(grid, i, j + 1);
		dfsSearch(grid, i - 1, j);
		dfsSearch(grid, i, j - 1);


	}

	public static void main(String[] args) {
		char[][] matrix = new char[][]{
				{'1','1','0','0','0'},
						{'1','1','0','0','0'},
						{'0','0','1','0','0'},
						{'0','0','0','1','1'}
		};
		int res = new NumIslands_200().numIslands(matrix);
		System.out.println(res);
	}
}
