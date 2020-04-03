package leetcode;

import java.util.HashSet;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午4:30:05 
 * @Description: 36. 有效的数独
 *
	判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
	
	数字 1-9 在每一行只能出现一次。
	数字 1-9 在每一列只能出现一次。
	数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
	
	
	上图是一个部分填充的有效的数独。
	
	数独部分空格内已填入了数字，空白格用 '.' 表示。
	
	示例 1:
	
	输入:
	[
	  ["5","3",".",".","7",".",".",".","."],
	  ["6",".",".","1","9","5",".",".","."],
	  [".","9","8",".",".",".",".","6","."],
	  ["8",".",".",".","6",".",".",".","3"],
	  ["4",".",".","8",".","3",".",".","1"],
	  ["7",".",".",".","2",".",".",".","6"],
	  [".","6",".",".",".",".","2","8","."],
	  [".",".",".","4","1","9",".",".","5"],
	  [".",".",".",".","8",".",".","7","9"]
	]
	输出: true
	示例 2:
	
	输入:
	[
	  ["8","3",".",".","7",".",".",".","."],
	  ["6",".",".","1","9","5",".",".","."],
	  [".","9","8",".",".",".",".","6","."],
	  ["8",".",".",".","6",".",".",".","3"],
	  ["4",".",".","8",".","3",".",".","1"],
	  ["7",".",".",".","2",".",".",".","6"],
	  [".","6",".",".",".",".","2","8","."],
	  [".",".",".","4","1","9",".",".","5"],
	  [".",".",".",".","8",".",".","7","9"]
	]
	输出: false
	解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
	     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
	说明:
	
	一个有效的数独（部分已被填充）不一定是可解的。
	只需要根据以上规则，验证已经填入的数字是否有效即可。
	给定数独序列只包含数字 1-9 和字符 '.' 。
	给定数独永远是 9x9 形式的。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/valid-sudoku
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-行，列，小宫格依次判断，单个set重复利用；
		2-单次遍历，行列小宫格各需要空间分别记录，需要的空间比1多；
		3-使用位运算记录，进一步降低辅助空间消耗；
 */
public class LeetCode_0036 {

}

class Solution_0036 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午8:27:14 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午8:27:14]  
	 * @UpdateRemark:1-行列小宫格分别遍历校验，使用一个set
	 *
	 */
	public boolean isValidSudoku_1(char[][] board) {
		if (board == null) {
			return false;
		}
		HashSet<Character> set = new HashSet<Character>();
		// 行列判断
		for (int x = 0; x < 9; x++) {
			set.clear();
			// 行判断
			for (int y = 0; y < 9; y++) {
				if (!check(set, board[x][y])) {
					return false;
				}
			}
			set.clear();
			// 列判断
			for (int y = 0; y < 9; y++) {
				if (!check(set, board[y][x])) {
					return false;
				}
			}
		}
		// 小宫格判断，先定位单个小宫格位置，再遍历小宫格的数字
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				set.clear();
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (!check(set, board[x + i][y + j])) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private boolean check(HashSet<Character> set, char c) {
		if (c == '.') {
			return true;
		} else if (set.contains(c)) {
			return false;
		} else {
			set.add(c);
			return true;
		}
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午8:27:38 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午8:27:38]  
	 * @UpdateRemark:2-行列小宫格各自使用自己的校验空间同时校验，一次遍历 
	 *
	 */
	public boolean isValidSudoku_2(char[][] board) {
		// 行列小宫格的编号
		int[][] rows = new int[9][9];
		int[][] columns = new int[9][9];
		int[][] boxes = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c != '.') {
					int num = c - '1';
					// 关注点，计算小宫格索引公式
					int boxIndex = (i / 3) * 3 + j / 3;
					// 行判断
					if (rows[i][num] == 1) {
						return false;
					} else {
						rows[i][num] = 1;
					}
					// 列判断
					if (columns[j][num] == 1) {
						return false;
					} else {
						columns[j][num] = 1;
					}
					// 小宫格判断
					if (boxes[boxIndex][num] == 1) {
						return false;
					} else {
						boxes[boxIndex][num] = 1;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月3日 下午9:30:03 
	 * @param: @param board
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-进一步压缩空间，使bit位来计算数据
	 *
	 */
	public boolean isValidSudoku_3(char[][] board) {
		// 行列小宫格的编号
		int[] rows = new int[9];
		int[] columns = new int[9];
		int[] boxes = new int[9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c != '.') {
					int num = c - '1';
					// 关注点，计算小宫格索引公式
					int boxIndex = (i / 3) * 3 + j / 3;
					// 行判断
					if (((rows[i] >> num) & 1) == 1) {
						return false;
					} else {
						rows[i] |= 1 << num;
					}
					// 列判断
					if (((columns[j] >> num) & 1) == 1) {
						return false;
					} else {
						columns[j] |= 1 << num;
					}
					// 小宫格判断
					if (((boxes[boxIndex] >> num) & 1) == 1) {
						return false;
					} else {
						boxes[boxIndex] |= 1 << num;
					}
				}
			}
		}
		return true;
	}
}