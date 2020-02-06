package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月14日 上午12:15:03 
 * @Description: 29. 两数相除
 *
	给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
	
	返回被除数 dividend 除以除数 divisor 得到的商。
	
	示例 1:
	
	输入: dividend = 10, divisor = 3
	输出: 3
	示例 2:
	
	输入: dividend = 7, divisor = -3
	输出: -2
	说明:
	
	被除数和除数均为 32 位有符号整数。
	除数不为 0。
	假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/divide-two-integers
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-使用位运算，因为正数存在溢出的问题，所以先确定符号，使用负数进行计算，最后再还原符号；
 */
public class LeetCode_0029 {
	public static void main(String[] args) {
		System.out.println(new Solution_0029().divide(2, -2));
	}
}

class Solution_0029 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午4:09:28 
	 * @param: @param dividend
	 * @param: @param divisor
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int divide(int dividend, int divisor) {
		// 被除数为0时快速返回
		if (divisor == 0) {
			return 0;
		}
		// 相等时快速返回
		if (dividend == divisor) {
			return 1;
		}
		// 唯一的商会溢出的情况，单独处理
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		// 为1时快速返回
		if (divisor == 1 || divisor == -1) {
			return divisor == 1 ? dividend : -dividend;
		}
		// 使用异或位运算记录商的符号：false为负数
		boolean flag = (dividend ^ divisor) > 0 ? false : true;
		int d1 = dividend;
		int d2 = divisor;
		// 都转为负数
		d1 = d1 > 0 ? -d1 : d1;
		d2 = d2 > 0 ? -d2 : d2;
		// 被除数绝对值小于除数绝对值时快速返回
		if (d1 > d2) {
			return 0;
		}
		int d3 = d2;
		int quotient = 0;
		// 位运算的中间值
		int multiple = 1;
		while (d1 <= d3) {
			// 2倍循环扩大被除数值，注意溢出判断，满足条件时获得相应的局部商值multiple
			while ((d2 << 1) < 0 && d1 <= (d2 << 1)) {
				multiple <<= 1;
				d2 <<= 1;
			}
			// 记录局部商值
			quotient += multiple;
			// 各种值复位，以及获得减去d2位移后剩余的新被除数，并复位d2为初始被除数后再进行位移操作
			// 这里是关键，因为第一次可能d2位移的过于大，所以d1减去已获得的最大d2并将d2复位后继续进行位移操作
			d1 -= d2;
			d2 = d3;
			multiple = 1;
		}
		return flag ? -quotient : quotient;
	}
}
