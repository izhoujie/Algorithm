package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ZhouJie
 * @date 2020年3月4日 下午2:37:58 
 * @Description: 994. 腐烂的橘子
 *
	在给定的网格中，每个单元格可以有以下三个值之一：
	
	值 0 代表空单元格；
	值 1 代表新鲜橘子；
	值 2 代表腐烂的橘子。
	每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
	
	返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
	
	 
	
	示例 1：
	
	
	
	输入：[[2,1,1],[1,1,0],[0,1,1]]
	输出：4
	示例 2：
	
	输入：[[2,1,1],[0,1,1],[1,0,1]]
	输出：-1
	解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
	示例 3：
	
	输入：[[0,2]]
	输出：0
	解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
	 
	
	提示：
	
	1 <= grid.length <= 10
	1 <= grid[0].length <= 10
	grid[i][j] 仅为 0、1 或 2
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/rotting-oranges
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-BFS
		2-对题的深入理解，用腐烂值加速BFS；（来自LeetCode已提交优秀解法）
 */
public class LeetCode_0994 {

}

class Solution_0994 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午5:22:38 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 1-BFS；
	 *
	 */
	public int orangesRotting_1(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int goodG = 0;
		Deque<int[]> deque = new ArrayDeque<int[]>();
		for (int i = 0; i < m; i++) {
			// 统计好橘子的数量
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					goodG++;
					// 坏橘子入队列
				} else if (grid[i][j] == 2) {
					deque.offer(new int[] { i, j });
				}
			}
		}
		// 若没有好橘子或没有坏橘子，直接返回
		if (goodG == 0) {
			return 0;
		}
		if (goodG > 0 && deque.isEmpty()) {
			return -1;
		}
		// 上下左右的传播方向
		int[] d1 = new int[] { 1, -1, 0, 0 };
		int[] d2 = new int[] { 0, 0, 1, -1 };
		int minute = 0, badG;
		while (!deque.isEmpty()) {
			badG = deque.size();
			while (badG-- > 0) {
				int[] bad = deque.poll();
				for (int i = 0; i < 4; i++) {
					int x = bad[0] + d1[i];
					int y = bad[1] + d2[i];
					if (x > -1 && x < m && y > -1 && y < n && grid[x][y] == 1) {
						deque.offer(new int[] { x, y });
						grid[x][y] = 2;
						goodG--;
					}
				}
			}
			minute++;
		}
		return goodG == 0 ? minute - 1 : -1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午5:22:58 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 2-优化后的BFS；
	 *
	 */
	public int orangesRotting_2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		// 记录是否有坏橘子
		boolean bad = false;
		// 第一次遍历，遇到坏橘子直接bfs，并且每次增加腐烂值val(初始值2)，则最终的耗时为Max(val)-2
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 2) {
					bad = true;
					dfs(grid, i, j, 2);
				}
			}
		}
		int maxBad = 2;
		// 第二次遍历检查是否还有好橘子，有则直接返回-1
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int k = grid[i][j];
				if (k == 1) {
					return -1;
				}
				maxBad = Math.max(maxBad, k);
			}
		}
		// 如果没有好橘子，则检查初始是否有坏橘子，没有则为0，有则返回max(val)-2
		return !bad ? 0 : maxBad - 2;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午6:13:29 
	 * @param: @param grid
	 * @param: @param x
	 * @param: @param y
	 * @param: @param val
	 * @return: void
	 * @Description: DFS辅助方法
	 *
	 */
	private void dfs(int[][] grid, int x, int y, int val) {
		// 先让好橘子腐烂，然后向四个方向扩散，遇到好橘子腐烂之增加腐烂值或遇到腐烂值比当前值+1还大则重置值并继续扩散
		// 重置的理解：只有离好橘子最近的坏橘子才会有最大的腐烂值，等价于DFS的最短路径计算
		grid[x][y] = val;
		if (x > 0 && (grid[x - 1][y] == 1 || grid[x - 1][y] > val + 1)) {
			dfs(grid, x - 1, y, val + 1);
		}
		if (x < grid.length - 1 && (grid[x + 1][y] == 1 || grid[x + 1][y] > val + 1)) {
			dfs(grid, x + 1, y, val + 1);
		}
		if (y > 0 && (grid[x][y - 1] == 1 || grid[x][y - 1] > val + 1)) {
			dfs(grid, x, y - 1, val + 1);
		}
		if (y < grid[0].length - 1 && (grid[x][y + 1] == 1 || grid[x][y + 1] > val + 1)) {
			dfs(grid, x, y + 1, val + 1);
		}
	}
}