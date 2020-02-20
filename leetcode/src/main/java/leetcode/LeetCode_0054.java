package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年2月20日 下午2:31:13 
 * @Description: 54. 螺旋矩阵
 *
	给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
	
	示例 1:
	
	输入:
	[
	 [ 1, 2, 3 ],
	 [ 4, 5, 6 ],
	 [ 7, 8, 9 ]
	]
	输出: [1,2,3,6,9,8,7,4,5]
	示例 2:
	
	输入:
	[
	  [1, 2, 3, 4],
	  [5, 6, 7, 8],
	  [9,10,11,12]
	]
	输出: [1,2,3,4,8,12,11,10,9,5,6,7]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/spiral-matrix
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-设置边界顺序螺旋遍历；
 */
public class LeetCode_0054 {

}

class Solution_0054 {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return list;
		}
		int left, right, up, down, x, y;
		x = y = left = up = 0;
		right = matrix[0].length - 1;
		down = matrix.length - 1;
		y--;
		while (true) {
			if (left > right) {
				break;
			}
			while (++y <= right) {
				list.add(matrix[x][y]);
			}
			y--;
			up++;
			if (up > down) {
				break;
			}
			while (++x <= down) {
				list.add(matrix[x][y]);
			}
			x--;
			right--;
			if (left > right) {
				break;
			}
			while (--y >= left) {
				list.add(matrix[x][y]);
			}
			y++;
			down--;
			if (up > down) {
				break;
			}
			while (--x >= up) {
				list.add(matrix[x][y]);
			}
			x++;
			left++;
		}
		return list;
	}
}
