package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月2日 下午4:07:04 
 * @Description: 289. 生命游戏
 * 
	根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
	
	给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
	
	如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
	如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
	如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
	如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
	根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
	
	 
	
	示例：
	
	输入： 
	[
	  [0,1,0],
	  [0,0,1],
	  [1,1,1],
	  [0,0,0]
	]
	输出：
	[
	  [0,0,0],
	  [1,0,1],
	  [0,1,1],
	  [0,1,0]
	]
	 
	
	进阶：
	
	你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
	本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/game-of-life
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0289 {

}

class Solution_0289 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月2日 下午4:07:37 
	 * @param: @param board
	 * @return: void
	 * @Description: 1-使用额外数组保存下一个状态，最后更新到原数组；
	 *
	 */
	public void gameOfLife_1(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int m = board.length;
		int n = board[0].length;
		int[][] newStatus = new int[m][n];
		// 方位数组
		int[] dx = new int[] { 0, 0, 1, 1, 1, -1, -1, -1 };
		int[] dy = new int[] { 1, -1, 1, -1, 0, 0, 1, -1 };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = 0;
				for (int k = 0; k < 8; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					// 统计当前细胞周围存活的细胞数
					if (x > -1 && x < m && y > -1 && y < n) {
						lives += board[x][y];
					}
				}
				if (board[i][j] == 1) {
					if (lives > 1 && lives < 4) {
						newStatus[i][j] = 1;
					}
				} else if (lives == 3) {
					newStatus[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = newStatus[i][j];
			}
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月2日 下午4:50:19 
	 * @param: @param board
	 * @return: void
	 * @Description: 2-因为状态只有0和1，所以我们可以使用位的特性，
	 * 				用int的第1位表示当前状态，第二位表示下一个状态，
	 * 				最后位右移1位即可，避免了使用额外数组空间；
	 *
	 */
	public void gameOfLife_2(int[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return;
		}
		int m = board.length;
		int n = board[0].length;
		// 方位数组
		int[] dx = new int[] { 0, 0, 1, 1, 1, -1, -1, -1 };
		int[] dy = new int[] { 1, -1, 1, -1, 0, 0, 1, -1 };
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lives = 0;
				for (int k = 0; k < 8; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					if (x > -1 && x < m && y > -1 && y < n) {
						// 第一位存的是当前状态，所以需要&1取到当前状态，第二位是即将更显的状态
						lives += board[x][y] & 1;
					}
				}
				if ((board[i][j] & 1) == 1) {
					if (lives > 1 && lives < 4) {
						// 知识点：二进制表示方式，0b开头后面跟01串
						// 0b11即表示当前细胞的当前状态为存活下一个状态也为存活
						// 等价于 board[i][j] | = 1<<1;
						board[i][j] = 0b11;
					}
				} else if (lives == 3) {
					// 0b10即表示当前细胞的当前状态为死亡下一个状态为存活
					board[i][j] = 0b10;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 右移一位，舍弃上一个状态，更新为当前状态；
				board[i][j] >>= 1;
			}
		}
	}

}