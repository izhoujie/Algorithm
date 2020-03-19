package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月18日 下午2:16:28 
 * @Description: 836. 矩形重叠
 *
	矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
	
	如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
	
	给出两个矩形，判断它们是否重叠并返回结果。
	
	示例 1：
	
	输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
	输出：true
	示例 2：
	
	输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
	输出：false
	 
	
	提示：
	
	两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
	矩形中的所有坐标都处于 -10^9 和 10^9 之间。
	x 轴默认指向右，y 轴默认指向上。
	你可以仅考虑矩形是正放的情况。
	思路：1-组成的矩形标记为1，2，因为不想交的好分析，分析不想交的情况最后取反即可；
		若不相交，则可能：1-矩形x轴方向上分离，即靠左矩形的右边界x必不大于靠右矩形的左边界x
					2-矩形y轴方向上分离，即靠下矩形的上边界y必不大于靠上矩形的下边界y
 */
public class LeetCode_0836 {

}

class Solution_0836 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月18日 下午3:24:09 
	 * @param: @param rec1
	 * @param: @param rec2
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-判断矩形的边界；
	 *
	 */
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		// 先判断不重叠的情况
		// 因为不确定两矩形谁前谁后，谁上谁下，所以前后和上下的位置各需要进行两次判断，最后取反即可
		return !(rec1[2] <= rec2[0] || rec2[2] <= rec1[1] || rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
	}
}
