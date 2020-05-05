package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午1:22:37 
 * @Description: 面试题29. 顺时针打印矩阵
 *
	输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
	
	 
	
	示例 1：
	
	输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
	输出：[1,2,3,6,9,8,7,4,5]
	示例 2：
	
	输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
	输出：[1,2,3,4,8,12,11,10,9,5,6,7]
	 
	
	限制：
	
	0 <= matrix.length <= 100
	0 <= matrix[i].length <= 100
	注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_29 {

}

class Solution_Offer_29 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 下午2:42:21 
	 * @param: @param matrix
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-
	 *
	 */
	public int[] spiralOrder(int[][] matrix) {
		int m0, m1, n0, n1, k, x, y;
		m0 = n0 = x = y = k = 0;
		m1 = matrix.length;
		if (m1 == 0) {
			return new int[0];
		}
		n1 = matrix[0].length;
		int[] rst = new int[m1 * n1];
		m1--;
		n1--;
		y--;
		while (true) {
			if (n0 > n1) {
				break;
			}
			while (++y <= n1) {
				rst[k++] = matrix[x][y];
			}
			y--;
			m0++;
			if (m0 > m1) {
				break;
			}
			while (++x <= m1) {
				rst[k++] = matrix[x][y];
			}
			x--;
			n1--;
			if (n0 > n1) {
				break;
			}
			while (--y >= n0) {
				rst[k++] = matrix[x][y];
			}
			y++;
			m1--;
			if (m0 > m1) {
				break;
			}
			while (--x >= m0) {
				rst[k++] = matrix[x][y];
			}
			x++;
			n0++;
		}
		return rst;
	}
}