package leetcode;

import java.util.ArrayDeque;

/**
 * @author ZhouJie
 * @date 2020年4月8日 下午7:38:21 
 * @Description: 面试题13. 机器人的运动范围
 *
	地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_13 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午7:38:58 
	 * @param: @param m
	 * @param: @param n
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 1-本题等价于从[0,0]点扩散，共有多少个点满足条件：行坐标和列坐标的数位之和不大于k；故可用BFS解决
	 *
	 */
	public int movingCount_1(int m, int n, int k) {
		// 是否到达过
		boolean[][] f = new boolean[m][n];
		// 方向数组，因为是从[0，0]开始，所以只需要向右和向下扩散搜索即可；
		int[] dx = new int[] { 0, 1 };
		int[] dy = new int[] { 1, 0 };
		// bfs队列
		ArrayDeque<int[]> deque = new ArrayDeque<int[]>();
		deque.offer(new int[] { 0, 0 });
		int count = 0;
		while (!deque.isEmpty()) {
			int[] poll = deque.poll();
			int x = poll[0];
			int y = poll[1];
			if (f[x][y]) {
				continue;
			}
			count++;
			f[x][y] = true;
			for (int i = 0; i < 2; i++) {
				int x0 = x + dx[i];
				int y0 = y + dy[i];
				if (x0 < m && y0 < n && (x0 % 10 + x0 / 10 + y0 % 10 + y0 / 10) <= k) {
					deque.offer(new int[] { x0, y0 });
				}
			}
		}
		return count;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午8:16:57 
	 * @param: @param m
	 * @param: @param n
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 2-方法1是bfs，现在用dfs；
	 *
	 */
	public int movingCount_2(int m, int n, int k) {
		boolean[][] f = new boolean[m][n];
		return dfs(m, n, 0, 0, k, f);
	}

	private int dfs(int m, int n, int x, int y, int k, boolean[][] f) {
		if (x < m && y < n && !f[x][y] && (x % 10 + x / 10 + y % 10 + y / 10) <= k) {
			// 符合条件的在右和下两个方向上继续搜索
			f[x][y] = true;
			return 1 + dfs(m, n, x + 1, y, k, f) + dfs(m, n, x, y + 1, k, f);
		} else {
			return 0;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月8日 下午8:45:57 
	 * @param: @param m
	 * @param: @param n
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 3-递推验证，某一个位置是否可达，取决于其本身是否可达且其左方和上方至少有一个可达；
	 *
	 */
	public int movingCount_3(int m, int n, int k) {
		boolean[][] f = new boolean[m][n];
		int count = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 起点位置不需要前置验证
				if (i == 0 && j == 0) {
					f[i][j] = true;
					count++;
					continue;
				}
				// 非起点位置，必须验证当前起点可达且其左位置和上位置至少有一个可达
				if ((i % 10 + i / 10 + j % 10 + j / 10) <= k && (i > 0 && f[i - 1][j] || j > 0 && f[i][j - 1])) {
					f[i][j] = true;
					count++;
				}
			}
		}
		return count;
	}
}
