package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月13日 上午12:03:38 
 * @Description: 面试题10- II. 青蛙跳台阶问题
 * 
	 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
	
	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	
	示例 1：
	
	输入：n = 2
	输出：2
	示例 2：
	
	输入：n = 7
	输出：21
	提示：
	
	0 <= n <= 100
	注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/
	
	 
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-
 *
 */
public class LeetCode_Offer_10_2 {
	public static void main(String[] args) {
		System.out.println(new Solution_Offer_10_2().numWays_1(30));
		System.out.println(new Solution_Offer_10_2().numWays_2(30));
	}
}

class Solution_Offer_10_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月13日 上午12:06:43 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-递归n太大时会栈溢出；
	 *
	 */
	public int numWays_1(int n) {
		return n < 2 ? 1 : (numWays_1(n - 2) + numWays_1(n - 1)) % 1000000007;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月13日 上午12:07:16 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 2-迭代可以避免递归的栈溢出问题；
	 *
	 */
	public int numWays_2(int n) {
		if (n < 2) {
			return 1;
		}
		int a = 1, b = 1;
		for (int i = 2; i < n + 1; i++) {
			int c = (a + b) % 1000000007;
			a = b;
			b = c;
		}
		return b;
	}
}