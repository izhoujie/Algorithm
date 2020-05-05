package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月2日 下午9:19:48 
 * @Description: 面试题15. 二进制中1的个数
 *
	请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
	
	示例 1：
	
	输入：00000000000000000000000000001011
	输出：3
	解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
	示例 2：
	
	输入：00000000000000000000000010000000
	输出：1
	解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
	示例 3：
	
	输入：11111111111111111111111111111101
	输出：31
	解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
	 
	
	注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_15 {

}

class Solution_Offer_15 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月2日 下午9:21:12 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-一个正数与其减1后二进制与运算后，该数的最低位的1将被0替代；
	 *
	 */
	// you need to treat n as an unsigned value
	public int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			n &= (n - 1);
			count++;
		}
		return count;
	}
}