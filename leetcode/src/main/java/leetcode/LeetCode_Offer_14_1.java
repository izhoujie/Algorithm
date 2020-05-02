package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月30日 下午11:25:29 
 * @Description: 面试题14- I. 剪绳子
 *
 */
public class LeetCode_Offer_14_1 {

}

class Solution_Offer_14_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月30日 下午11:26:01 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int cuttingRope_1(int n) {
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