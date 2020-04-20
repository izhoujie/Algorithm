package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ZhouJie
 * @date 2020年4月20日 下午3:23:26 
 * @Description: 200. 岛屿数量
 *
	给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
	
	岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
	
	此外，你可以假设该网格的四条边均被水包围。
	
	示例 1:
	
	输入:
	11110
	11010
	11000
	00000
	输出: 1
	示例 2:
	
	输入:
	11000
	11000
	00100
	00011
	输出: 3
	解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/number-of-islands
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0200 {

}

class Solution_0200 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月20日 下午4:12:48 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 1-DFS，每找到一个1就给岛屿一个编号并DFS，最后命名的数量就是岛屿数；
	 *
	 */
	public int numIslands_1(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int row = grid.length;
		int col = grid[0].length;
		// 因为源数组是'1'，所以岛屿编号从'2'开始
		char count = '2';
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 每找到一个'1'就dfs并标记 为当前编号
				if (grid[i][j] == '1') {
					dfs(grid, i, j, count);
					// 标记完当前岛屿编号递增
					count++;
				}
			}
		}
		// 一共标记的岛屿数
		return count - '2';
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月20日 下午4:13:59 
	 * @param: @param grid
	 * @param: @param i
	 * @param: @param j
	 * @param: @param count
	 * @return: void
	 * @Description: dfs
	 *
	 */
	private void dfs(char[][] grid, int i, int j, char count) {
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
			grid[i][j] = count;
			dfs(grid, i - 1, j, count);
			dfs(grid, i + 1, j, count);
			dfs(grid, i, j - 1, count);
			dfs(grid, i, j + 1, count);
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月20日 下午4:22:47 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 2-BFS，不同与同源BFS，本题恰是要计算不同源的数量，所以每遇到一个'1'就需要一次BFS； 
	 *
	 */
	public int numIslands_2(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int row = grid.length;
		int col = grid[0].length;
		// 因为源数组是'1'，所以岛屿编号从'2'开始
		char count = '2';
		Deque<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 每找到一个'1'就dfs并标记 为当前编号
				if (grid[i][j] == '1') {
					// 这里存ij的位置很巧妙，用col乘以i再加j，因为j<col，所以取回时除以col就能再得到i，而对col取模就得到了j，避免了用数组存ij
					queue.offer(i * col + j);
					while (!queue.isEmpty()) {
						Integer poll = queue.poll();
						int x = poll / col;
						int y = poll % col;
						if (x > 0 && grid[x - 1][y] == '1') {
							grid[x - 1][y] = count;
							queue.offer((x - 1) * col + y);
						}
						if (y > 0 && grid[x][y - 1] == '1') {
							grid[x][y - 1] = count;
							queue.offer(x * col + y - 1);
						}
						if (x < row - 1 && grid[x + 1][y] == '1') {
							grid[x + 1][y] = count;
							queue.offer((x + 1) * col + y);
						}
						if (y < col - 1 && grid[x][y + 1] == '1') {
							grid[x][y + 1] = count;
							queue.offer(x * col + y + 1);
						}

					}
					// 标记完当前岛屿编号递增
					count++;
				}
			}
		}
		return count - '2';
	}
}