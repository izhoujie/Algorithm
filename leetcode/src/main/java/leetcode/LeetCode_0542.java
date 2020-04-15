package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ZhouJie
 * @date 2020年4月15日 下午6:39:55 
 * @Description: 542. 01 矩阵
 *
	给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
	
	两个相邻元素间的距离为 1 。
	
	示例 1:
	输入:
	
	0 0 0
	0 1 0
	0 0 0
	输出:
	
	0 0 0
	0 1 0
	0 0 0
	示例 2:
	输入:
	
	0 0 0
	0 1 0
	1 1 1
	输出:
	
	0 0 0
	0 1 0
	1 2 1
	注意:
	
	给定矩阵的元素个数不超过 10000。
	给定矩阵中至少有一个元素是 0。
	矩阵中的元素只在四个方向上相邻: 上、下、左、右。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/01-matrix
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0542 {

}

class Solution_0542 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月15日 下午6:40:25 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int[][]
	 * @Description: 1-dp，先从左上角扫描到右下角，然后再反向回扫一遍；
	 *
	 */
	public int[][] updateMatrix_1(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int max = row + col;
		// 左上角向右下角扫描
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1) {
					// 可能的最大路径值
					matrix[i][j] = max;
				}
				if (i > 0) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
				}
				if (j > 0) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
				}

			}
		}
		// 右下角往左上角扫描
		for (int i = row - 1; i > -1; i--) {
			for (int j = col - 1; j > -1; j--) {
				if (i < row - 1) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
				}
				if (j < col - 1) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
				}
			}
		}
		return matrix;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月15日 下午7:29:39 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int[][]
	 * @Description: 2-bfs
	 *
	 */
	public int[][] updateMatrix_2(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int max = row + col;
		// 四个方向向量
		int[] vectorX = new int[] { 0, 0, 1, -1 };
		int[] vectorY = new int[] { 1, -1, 0, 0 };
		// 队列，0位置先入队
		Deque<int[]> deque = new ArrayDeque<int[]>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					deque.offer(new int[] { i, j });
				} else {
					// 非0一致先给最大路径，在bfs时再往小值收敛
					matrix[i][j] = max;
				}
			}
		}
		while (!deque.isEmpty()) {
			int[] p = deque.poll();
			for (int i = 0; i < 4; i++) {
				int x = p[0] + vectorX[i];
				int y = p[1] + vectorY[i];
				// 因为每次都是从外围往内收缩，所以后续同层的会跳过，而靠内层的最大路径只会比外层的+1，往小值收敛
				if (x > -1 && x < row && y > -1 && y < col && matrix[x][y] > matrix[p[0]][p[1]] + 1) {
					matrix[x][y] = matrix[p[0]][p[1]] + 1;
					deque.offer(new int[] { x, y });
				}
			}
		}
		return matrix;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月15日 下午7:44:55 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int[][]
	 * @Description: 3-dfs
	 *
	 */
	public int[][] updateMatrix_3(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int max = row + col;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 若不是最外围的1，则初始化为最大路径
				if (matrix[i][j] == 1 && !check(matrix, row, col, i, j)) {
					matrix[i][j] = max;
				}
			}
		}
		// 四个方向向量
		int[] vectorX = new int[] { 0, 0, 1, -1 };
		int[] vectorY = new int[] { 1, -1, 0, 0 };
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 从最外围的1往内dfs
				if (matrix[i][j] == 1) {
					dfs(matrix, vectorX, vectorY, row, col, i, j);
				}
			}
		}
		return matrix;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月15日 下午8:01:35 
	 * @param: @param matrix
	 * @param: @param row
	 * @param: @param col
	 * @param: @param i
	 * @param: @param j
	 * @param: @return
	 * @return: boolean
	 * @Description: 辅助方法，判断坐标[i，j]周围是否至少有一个位置是0，即[i,j]是否是最外围的1
	 *
	 */
	private boolean check(int[][] matrix, int row, int col, int i, int j) {
		if (i > 0 && matrix[i - 1][j] == 0) {
			return true;
		}
		if (j > 0 && matrix[i][j - 1] == 0) {
			return true;
		}
		if (i < row - 1 && matrix[i + 1][j] == 0) {
			return true;
		}
		if (j < col - 1 && matrix[i][j + 1] == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月15日 下午8:07:52 
	 * @param: @param matrix
	 * @param: @param vectorX
	 * @param: @param vectorY
	 * @param: @param row
	 * @param: @param col
	 * @param: @param i
	 * @param: @param j
	 * @return: void
	 * @Description: dfs
	 *
	 */
	private void dfs(int[][] matrix, int[] vectorX, int[] vectorY, int row, int col, int i, int j) {
		for (int k = 0; k < 4; k++) {
			int x = i + vectorX[k];
			int y = j + vectorY[k];
			if (x > -1 && x < row && y > -1 && y < col && matrix[x][y] > matrix[i][j] + 1) {
				matrix[x][y] = matrix[i][j] + 1;
				dfs(matrix, vectorX, vectorY, row, col, x, y);
			}
		}
	}

}