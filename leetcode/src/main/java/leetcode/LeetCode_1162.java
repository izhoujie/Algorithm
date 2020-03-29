package leetcode;

import java.util.ArrayDeque;

/**
 * @author ZhouJie
 * @date 2020年3月29日 下午9:45:11 
 * @Description: 1162. 地图分析
 *
	你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。
	
	我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。
	
	如果我们的地图上只有陆地或者海洋，请返回 -1。
	
	 
	
	示例 1：
	
	
	
	输入：[[1,0,1],[0,0,0],[1,0,1]]
	输出：2
	解释： 
	海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
	示例 2：
	
	
	
	输入：[[1,0,0],[0,0,0],[0,0,0]]
	输出：4
	解释： 
	海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
	 
	
	提示：
	
	1 <= grid.length == grid[0].length <= 100
	grid[i][j] 不是 0 就是 1
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1162 {

}

class Solution_1162 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月29日 下午11:31:43 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 1-BFS，所有陆地作为初始点，开始扩散，使用优先队列；
	 *
	 */
	public int maxDistance_1(int[][] grid) {
		ArrayDeque<int[]> deque = new ArrayDeque<int[]>();
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					deque.offer(new int[] { i, j });
				}
			}
		}

		int[] last = null;
		boolean noSea = true;
		// 四个搜索方向
		int[] dx = new int[] { 1, -1, 0, 0 };
		int[] dy = new int[] { 0, 0, 1, -1 };
		while (!deque.isEmpty()) {
			last = deque.poll();
			int x0 = last[0];
			int y0 = last[1];
			for (int i = 0; i < 4; i++) {
				int x = x0 + dx[i];
				int y = y0 + dy[i];
				if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 0) {
					continue;
				}
				grid[x][y] = grid[x0][y0] + 1;
				noSea = false;
				deque.offer(new int[] { x, y });
			}
		}
		return noSea || last == null ? -1 : grid[last[0]][last[1]] - 1;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月29日 下午11:31:40 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 2-动态规划，基于曼哈顿距离的概念，即距离总是为正上方和左前方的距离和或正下方和右前方的距离和，
	 *				对grid进行两次dp，一次从左上角到右下角，一次从右下角到左上角；
	 *
	 */
	public int maxDistance_2(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		boolean noLand = true;
		// 从左上角到右下角的dp，顺带标记noLand
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					noLand = false;
					continue;
				}
				if (grid[i][j] == 0) {
					grid[i][j] = m + n;
				}
				if (i > 0) {
					grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1);
				}
				if (j > 0) {
					grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1);
				}
			}
		}
		int distance = 0;
		// 右下方到左上方的dp，计算distance
		for (int i = m - 1; i > -1; i--) {
			for (int j = n - 1; j > -1; j--) {
				if (grid[i][j] != 1) {
					if (i < m - 1) {
						grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1);
					}
					if (j < n - 1) {
						grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1);
					}
					distance = Math.max(distance, grid[i][j]);
				}
			}
		}
		return noLand ? -1 : --distance;
	}
}