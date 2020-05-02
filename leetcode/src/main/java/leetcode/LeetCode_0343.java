package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月2日 下午7:50:30 
 * @Description: 343. 整数拆分
 *
	给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
	
	示例 1:
	
	输入: 2
	输出: 1
	解释: 2 = 1 + 1, 1 × 1 = 1。
	示例 2:
	
	输入: 10
	输出: 36
	解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
	说明: 你可以假设 n 不小于 2 且不大于 58。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/integer-break
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0343 {

}

class Solution_0343 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月2日 下午7:51:03 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 优先分为3连乘，其次是2
	 *
	 */
	public int integerBreak(int n) {
		if (n < 4) {
			return n > 2 ? 2 : 1;
		} else {
			int rst = 1;
			while (n > 4) {
				rst *= 3;
				n -= 3;
			}
			return rst * n;
		}
	}
}