package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月25日 下午2:40:31 
 * @Description: 892. 三维形体的表面积
 *
	在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
	
	每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
	
	请你返回最终形体的表面积。
	
	
	示例 1：
	
	输入：[[2]]
	输出：10
	示例 2：
	
	输入：[[1,2],[3,4]]
	输出：34
	示例 3：
	
	输入：[[1,0],[0,2]]
	输出：16
	示例 4：
	
	输入：[[1,1,1],[1,0,1],[1,1,1]]
	输出：32
	示例 5：
	
	输入：[[2,2,2],[2,1,2],[2,2,2]]
	输出：46
	 
	
	提示：
	
	1 <= N <= 50
	0 <= grid[i][j] <= 50
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/surface-area-of-3d-shapes
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-考虑减去上面相邻和前面相邻位置的重叠面即可；
 */
public class LeetCode_0892 {

}

class Solution_0892 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月25日 下午4:19:42 
	 * @param: @param grid
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int surfaceArea(int[][] grid) {
		int s = 0;
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return s;
		}
		int n = grid.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 当前位置的立方体数量
				int h = grid[i][j];
				if (h > 0) {
					// h个叠加的立方体总面积为6*h-(h-1)*2即4h+2等价于(h<<2)+2;
					s += (h << 2) + 2;
					// 与上相邻和左相邻位置的重叠面计算，重叠面等于相邻的较少数*2
					if (i > 0) {
						s -= Math.min(h, grid[i - 1][j]) << 1;
					}
					if (j > 0) {
						s -= Math.min(h, grid[i][j - 1]) << 1;
					}
				}
			}
		}
		return s;
	}
}
