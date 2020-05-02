package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月30日 下午11:52:44 
 * @Description: 面试题14- II. 剪绳子 II
 *
 *
	给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	
	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	
	 
	
	示例 1：
	
	输入: 2
	输出: 1
	解释: 2 = 1 + 1, 1 × 1 = 1
	示例 2:
	
	输入: 10
	输出: 36
	解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
	 
	
	提示：
	
	2 <= n <= 1000
	注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_14_2 {

}

class Solution_Offer_14_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月1日 上午12:13:23 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: TODO
	 *
	 */
	public int cuttingRope_1(int n) {
		int mod = 1000000007;
		// 特例判断
		if (n < 4) {
			return n > 2 ? 2 : 1;
		} else {
			long rst = 1;
			while (n > 4) {
				rst = rst * 3 % mod;
				n -= 3;
			}
			return (int) (rst * n % mod);
		}
	}
}