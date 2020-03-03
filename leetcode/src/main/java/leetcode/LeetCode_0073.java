package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月3日 下午1:13:46 
 * @Description: 73. 矩阵置零
 *
	给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
	
	示例 1:
	
	输入: 
	[
	  [1,1,1],
	  [1,0,1],
	  [1,1,1]
	]
	输出: 
	[
	  [1,0,1],
	  [0,0,0],
	  [1,0,1]
	]
	示例 2:
	
	输入: 
	[
	  [0,1,2,0],
	  [3,4,5,2],
	  [1,3,1,5]
	]
	输出: 
	[
	  [0,0,0,0],
	  [0,4,5,0],
	  [0,3,1,0]
	]
	进阶:
	
	一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
	一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
	你能想出一个常数空间的解决方案吗？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/set-matrix-zeroes
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-第一次遍历用首行首列记录对应的行列是否要置0，且记录首行首列是否要最终置0，
	第二次遍历从[1,1]开始，利用首行首列的0标记对遍历位置进行0标记，最后再用第一次遍历记录的首行首列0标记决定是否对首行首列进行0标记；
 */
public class LeetCode_0073 {

}

class Solution_0073 {
	public void setZeroes(int[][] matrix) {
		if (matrix == null) {
			return;
		}
		int m = matrix.length;
		if (m == 0) {
			return;
		}
		int n = matrix[0].length;
		boolean mFlag = false, nFlag = false;
		// 第一次遍历，记录首行首列是否需置0及行列是否置0
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					if (i == 0) {
						mFlag = true;
					}
					if (j == 0) {
						nFlag = true;
					}
					matrix[0][j] = matrix[i][0] = 0;
				}
			}
		}
		// 第二次遍历从[1,1]处开始，用第一次的行列记录进行全面0标记；
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[0][j] == 0 || matrix[i][0] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// 最后用第一次的首行列标记确定首行列是否要进行0标记
		if (nFlag) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
		if (mFlag) {
			for (int i = 0; i < n; i++) {
				matrix[0][i] = 0;
			}
		}
	}
}
