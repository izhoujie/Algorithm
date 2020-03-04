package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午7:04:44 
 * @Description: 9. 回文数
 *
	判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
	
	示例 1:
	
	输入: 121
	输出: true
	示例 2:
	
	输入: -121
	输出: false
	解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
	示例 3:
	
	输入: 10
	输出: false
	解释: 从右向左读, 为 01 。因此它不是一个回文数。
	进阶:
	
	你能不将整数转为字符串来解决这个问题吗？
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/palindrome-number
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-负数直接返回false，正数做一次反转再判是否与原数相等即可；
		2-反转时只反转一半，然后对比值，如12321，反转得到123和12，只需要比较123==12||123/10==12即可（来自leetcoed官方解法）
 */
public class LeetCode_0009 {

}

class Solution_0009 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午5:58:05 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午5:58:05]  
	 * @UpdateRemark:1-直接完全反转后对比
	 *
	 */
	public boolean isPalindrome(int x) {
		// 快速排除负数及末尾为0的非0数
		if (x < 0 || x != 0 && x % 10 == 0) {
			return false;
		}
		int k = x;
		int y = 0;
		while (k != 0) {
			y = y * 10 + k % 10;
			k /= 10;
		}
		return y == x;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午5:58:20 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午5:58:20]  
	 * @UpdateRemark:2-反转一半后对比，（解决原数长度是奇偶数技巧很赞）；
	 *
	 */
	public boolean isPalindrome_1(int x) {
		// 快速排除负数及末尾为0的非0数
		if (x < 0 || x != 0 && x % 10 == 0) {
			return false;
		}
		int y = 0;
		while (x > y) {
			y = y * 10 + x % 10;
			x /= 10;
		}
		return y == x || y / 10 == x;
	}
}
