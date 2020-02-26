package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月26日 下午5:43:58 
 * @Description: 59. 螺旋矩阵 II
 *
	给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
	
	示例:
	
	输入: 3
	输出:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/spiral-matrix-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-顺序遍历，使用转向数组控制边界；
 */
public class LeetCode_0059 {

}

class Solution_0059 {
	public int[][] generateMatrix(int n) {
		if (n < 0) {
			return null;
		}
		int[][] rst = new int[n][n];
		// X和Y为转向指针
		int[] X = new int[] { 0, 1, 0, -1 };
		int[] Y = new int[] { 1, 0, -1, 0 };
		int x, y, p;
		x = y = p = 0;
		for (int i = 1; i <= n * n; i++) {
			rst[x][y] = i;
			p %= 4;
			int x1 = x + X[p];
			int y1 = y + Y[p];
			// 越界及是否已遍历
			if (x1 >= n || y1 >= n || x1 < 0 || y1 < 0 || rst[x1][y1] > 0) {
				p = (p + 1) % 4;
			}
			x += X[p];
			y += Y[p];
		}
		return rst;
	}
}
