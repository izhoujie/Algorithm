package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ZhouJie
 * @date 2020年5月8日 下午5:06:45 
 * @Description: 221. 最大正方形
 *
	在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
	
	示例:
	
	输入: 
	
	1 0 1 0 0
	1 0 1 1 1
	1 1 1 1 1
	1 0 0 1 0
	
	输出: 4
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/maximal-square
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
 */
public class LeetCode_0221 {

}

class Solution_0221 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月8日 下午6:49:30 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int
	 * @Description: 1-BFS；
	 *
	 */
	public int maximalSquare_1(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		Deque<Integer> deque = new ArrayDeque<Integer>();
		int size = 0, maxS = 0;
		// 右方、下方、右下方的方位
		int[] dx = new int[] { 1, 0, 1 };
		int[] dy = new int[] { 0, 1, 1 };
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 若当前是1则开始BFS
				if (matrix[i][j] == '1') {
					deque.clear();
					// 保存坐标，不使用数组，利用int整除和取模的特性存储坐标信息
					deque.offer(i * col + j);
					// 初始变长为1
					int a = 1;
					boolean f = false;
					while (!deque.isEmpty()) {
						maxS = Math.max(a * a, maxS);
						size = deque.size();
						while (size-- > 0) {
							Integer p = deque.poll();
							int x = p / col;
							int y = p % col;
							int x0, y0;
							for (int k = 0; k < 3; k++) {
								x0 = x + dx[k];
								y0 = y + dy[k];
								// 当前位置的右方、下方、和右下方的位置也必须都是1才能扩大正方形的边长，否则BFS到此为止
								if (x0 < row && y0 < col && matrix[x0][y0] == '1') {
									deque.offer(x0 * col + y0);
								} else {
									f = true;
									break;
								}
							}
						}
						if (f) {
							break;
						}
						a++;
					}
				}
			}
		}
		return maxS;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月8日 下午6:49:40 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int
	 * @Description: 2-dp动态规划；
	 *
	 */
	public int maximalSquare_2(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		// 记录最大边长
		char max = '0';
		// dp逻辑，第0行和第0列只需要记录是否有1，从[1][1]开始dp，对于之后的[i][j]逻辑为：
		// 若[i][j]不为0，则：
		// dp[i][j]=min(dp[i-1][j-1],min(dp[i-1][j],dp[i][j-1]))+1，dp[i][j]
		// dp[i][j]可能为最大边长，因为matrix本身就是dp所需的初始状态，只是保存的为char类型，需要转int类型，所以可以直接用matrix进行dp
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if ((i == 0 || j == 0)) {
					if (max == '0' && matrix[i][j] == '1') {
						max = '1';
					}
				} else if (matrix[i][j] > '0') {
					matrix[i][j] = matrix[i][j - 1] < matrix[i - 1][j] ? matrix[i][j - 1] : matrix[i - 1][j];
					matrix[i][j] = matrix[i][j] < matrix[i - 1][j - 1] ? matrix[i][j] : matrix[i - 1][j - 1];
					matrix[i][j]++;
					max = max > matrix[i][j] ? max : matrix[i][j];
				}
			}
		}
		return (max - '0') * (max - '0');
	}
}