package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午11:56:30 
 * @Description: 407. 接雨水 II
 *
	给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
	
	 
	
	说明:
	
	m 和 n 都是小于110的整数。每一个单位的高度都大于 0 且小于 20000。
	
	 
	
	示例：
	
	给出如下 3x6 的高度图:
	[
	  [1,4,3,1,3,2],
	  [3,2,1,3,2,4],
	  [2,3,3,2,3,1]
	]
	
	返回 4。
	
	
	如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
	
	 
	
	
	
	下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/trapping-rain-water-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-广度优先算法，BFS，从外侧向内搜索，找邻近低的值进行接雨水；
 */
public class LeetCode_0407 {

}

class Solution_0407 {

	public int trapRainWater(int[][] heightMap) {
		if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
			return 0;
		}
		// 优先队列
		PriorityQueue<Cell> queue = new PriorityQueue<Cell>(new Comparator<Cell>() {
			@Override
			public int compare(Cell o1, Cell o2) {
				return o1.h - o2.h;
			}
		});
		int m = heightMap.length;
		int n = heightMap[0].length;
		// 辅助矩阵，用以记录该点是否处理过
		boolean[][] f = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			f[i][0] = true;
			f[i][n - 1] = true;
			queue.offer(new Cell(i, 0, heightMap[i][0]));
			queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
		}
		for (int i = 0; i < n; i++) {
			f[0][i] = true;
			f[m - 1][i] = true;
			queue.offer(new Cell(0, i, heightMap[0][i]));
			queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
		}
		// 某个点的临近点方向
		int[][] index = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		int water = 0;
		while (!queue.isEmpty()) {
			Cell cell = queue.poll();
			for (int[] p : index) {
				int x = cell.x + p[0];
				int y = cell.y + p[1];
				if (x >= 0 && x < m && y >= 0 && y < n && !f[x][y]) {
					water += Math.max(0, cell.h - heightMap[x][y]);
					queue.offer(new Cell(x, y, Math.max(cell.h, heightMap[x][y])));
					f[x][y] = true;
				}
			}
		}
		return water;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月2日 下午3:40:37 
	 * @Description: 矩阵点辅助
	 *
	 */
	class Cell {
		int x;
		int y;
		int h;

		public Cell(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
}
