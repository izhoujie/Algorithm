package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月15日 下午10:59:33 
 * @Description: 面试题64. 求1+2+…+n
 *
	求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
	
	示例 1：
	
	输入: n = 3
	输出: 6
	示例 2：
	
	输入: n = 9
	输出: 45
	 
	
	限制：
	
	1 <= n <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/qiu-12n-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_64 {

}

class Solution_Offer_64 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月15日 下午10:59:57 
	 * @param: @param n
	 * @param: @return
	 * @return: int
	 * @Description: 1-利用短路&&的特性来决定前后部分的执行逻辑；
	 *
	 */
	public int sumNums_1(int n) {
		@SuppressWarnings("unused")
		boolean f = n > 0 && (n += sumNums_1(n - 1)) > 0;
		return n;
	}
}