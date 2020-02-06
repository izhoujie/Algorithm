package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月2日 下午10:23:55 
 * @Description: 50. Pow(x, n)
 *
	实现 pow(x, n) ，即计算 x 的 n 次幂函数。
	
	示例 1:
	
	输入: 2.00000, 10
	输出: 1024.00000
	示例 2:
	
	输入: 2.10000, 3
	输出: 9.26100
	示例 3:
	
	输入: 2.00000, -2
	输出: 0.25000
	解释: 2-2 = 1/22 = 1/4 = 0.25
	说明:
	
	-100.0 < x < 100.0
	n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/powx-n
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-折半幂次乘积，logN复杂度；（来自leetcode评论区解法）
 */
public class LeetCode_0050 {

}

class Solution_0050 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午11:29:49 
	 * @param: @param x
	 * @param: @param n
	 * @param: @return
	 * @return: double
	 * @Description: 1-
	 *
	 */
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double rst = 1.0;
		for (int i = n; i != 0; i /= 2) {
			// 关键点，只有在i初始为奇数和最终降幂为1时进行此处计算，其余的迭代i都是2的幂次方数，直接将x幂积即可；
			if (i % 2 != 0) {
				rst *= x;
			}
			x *= x;
		}
		// 判断n的符号来确定返回值
		return n > 0 ? rst : 1 / rst;
	}
}
