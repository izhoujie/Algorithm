package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月14日 下午11:29:28 
 * @Description: 面试题65. 不用加减乘除做加法
 *
	写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
	
	示例:
	
	输入: a = 1, b = 1
	输出: 2
	 
	
	提示：
	
	a, b 均可能是负数或 0
	结果不会溢出 32 位整数
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_65 {

}

class Solution_Offer_65 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月14日 下午11:29:55 
	 * @param: @param a
	 * @param: @param b
	 * @param: @return
	 * @return: int
	 * @Description: 1-用异或计算位相加无进位的情况，用与计算位相加有进位的情况，递归处理即可；
	 *
	 */
	public int add(int a, int b) {
		// a^b是对同bit位只有一个1情况的位求和
		// a&b是对同bit位均为1情况的进位，若均为1相加后新1的bit位比原1高一个bit位，即需要左移1位
		return b == 0 ? a : add(a ^ b, (a & b) << 1);
	}
}