package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月13日 上午1:16:51 
 * @Description: 面试题10- I. 斐波那契数列
 * 
	面试题10- I. 斐波那契数列
	写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
	
	F(0) = 0,   F(1) = 1
	F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
	斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
	
	答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
	
	 
	
	示例 1：
	
	输入：n = 2
	输出：1
	示例 2：
	
	输入：n = 5
	输出：5
	 
	
	提示：
	
	0 <= n <= 100
	注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/
 */
public class LeetCode_Offer_10_1 {

}

class Solution_Offer_10_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月13日 上午1:19:17 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-迭代；
	 *
	 */
	public int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n < 3) {
			return 1;
		}
		int a = 1, b = 1;
		for (int i = 3; i < n + 1; i++) {
			int c = (a + b) % 1000000007;
			a = b;
			b = c;
		}
		return b;
	}
}