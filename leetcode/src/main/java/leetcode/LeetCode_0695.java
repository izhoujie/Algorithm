package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月15日 下午4:17:18 
 * @Description: 695. 岛屿的最大面积
 *
	给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
	
	找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
	
	示例 1:
	
	[[0,0,1,0,0,0,0,1,0,0,0,0,0],
	 [0,0,0,0,0,0,0,1,1,1,0,0,0],
	 [0,1,1,0,1,0,0,0,0,0,0,0,0],
	 [0,1,0,0,1,1,0,0,1,0,1,0,0],
	 [0,1,0,0,1,1,0,0,1,1,1,0,0],
	 [0,0,0,0,0,0,0,0,0,0,1,0,0],
	 [0,0,0,0,0,0,0,1,1,1,0,0,0],
	 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
	对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
	
	示例 2:
	
	[[0,0,0,0,0,0,0,0]]
	对于上面这个给定的矩阵, 返回 0。
	
	注意: 给定的矩阵grid 的长度和宽度都不超过 50。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/max-area-of-island
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-bfs，使用了队列等额外空间，适合数据量大时使用；
		2-dfs，避免试用额外空间，直接递归，数据量大时可能爆栈，需注意；
 */
public class LeetCode_0695 {

}

class Solution_0695 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月15日 下午4:17:57 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 1-先一次遍历，找到所有的1位置，然后逐个取1进行BFS，标记过的为-1，保留每个岛屿的最大面积；
	 *
	 */
	public int maxAreaOfIsland_1(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		// 行
		int m = grid.length;
		// 列
		int n = grid[0].length;
		List<Integer> startX = new ArrayList<Integer>();
		List<Integer> startY = new ArrayList<Integer>();
		int startCount = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					startX.add(i);
					startY.add(j);
					startCount++;
				}
			}
		}
		if (startCount == 0) {
			return 0;
		}
		// BFS时的队列
		Deque<Integer> gX = new ArrayDeque<Integer>();
		Deque<Integer> gY = new ArrayDeque<Integer>();
		// 四个搜索方向
		int[] dX = new int[] { 0, 0, 1, -1 };
		int[] dY = new int[] { 1, -1, 0, 0 };
		// 最大岛屿面积
		int maxArea = 0, tempArea;
		while (startCount-- > 0) {
			int checkX = startX.get(startCount);
			int checkY = startY.get(startCount);
			// 是否已经计算过面积
			if (grid[checkX][checkY] != 1) {
				continue;
			}
			gX.offer(checkX);
			gY.offer(checkY);
			tempArea = 0;
			while (!gX.isEmpty()) {
				int x = gX.poll();
				int y = gY.poll();
				if (grid[x][y] != 1) {
					continue;
				}
				tempArea++;
				// 标记计算过面积的位置；
				grid[x][y] = -1;
				// 四个方向BFS
				for (int i = 0; i < 4; i++) {
					int x0 = x + dX[i];
					int y0 = y + dY[i];
					if (x0 > -1 && x0 < m && y0 > -1 && y0 < n && grid[x0][y0] == 1) {
						gX.offer(x0);
						gY.offer(y0);
					}
				}
			}
			// 保留最大面积；
			maxArea = Math.max(maxArea, tempArea);
		}
		return maxArea;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月15日 下午4:55:56 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 2-使用递归优化，避免使用栈或者队列额外空间；（题目说明了二维数组不超过50，过大时此方法递归可能爆栈）
	 *
	 */
	public int maxAreaOfIsland_2(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		// 行
		int m = grid.length;
		// 列
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					int tempArea = dfs(grid, i, j, m, n);
					maxArea = Math.max(maxArea, tempArea);
				}
			}
		}
		return maxArea;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月15日 下午5:03:06 
	 * @param: @param grid
	 * @param: @param x
	 * @param: @param y
	 * @param: @param m
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: dfs
	 *
	 */
	private int dfs(int[][] grid, int x, int y, int m, int n) {
		if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
			return 0;
		} else {
			grid[x][y] = 0;
			return 1 + dfs(grid, x + 1, y, m, n) + dfs(grid, x - 1, y, m, n) + dfs(grid, x, y + 1, m, n)
					+ dfs(grid, x, y - 1, m, n);
		}
	}
}