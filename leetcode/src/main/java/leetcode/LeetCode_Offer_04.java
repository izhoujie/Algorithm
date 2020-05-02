package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午4:45:26 
 * @Description: 面试题04. 二维数组中的查找
 *
	在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	
	示例:
	
	现有矩阵 matrix 如下：
	
	[
	  [1,   4,  7, 11, 15],
	  [2,   5,  8, 12, 19],
	  [3,   6,  9, 16, 22],
	  [10, 13, 14, 17, 24],
	  [18, 21, 23, 26, 30]
	]
	给定 target = 5，返回 true。
	
	给定 target = 20，返回 false。
	
	 
	
	限制：
	
	0 <= n <= 1000
	
	0 <= m <= 1000
	
	 
	
	注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_04 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午5:51:04 
	 * @param: @param matrix
	 * @param: @param target
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-从右上角向左下角把相连的横竖行拉直就是一个连续的递增数组；
	 *
	 */
	public boolean findNumberIn2DArray(int[][] matrix, int target) {
		// 特例判断
		if (matrix == null || matrix.length == 0) {
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int row = 0, col = n - 1;
		// 二分查找，以右上角的位置作为中间位置，小于target就row++，否则就col--
		while (row < m && col > -1) {
			if (matrix[row][col] > target) {
				col--;
			} else if (matrix[row][col] < target) {
				row++;
			} else {
				return true;
			}
		}
		// 越界仍为找到时
		return false;
	}
}
