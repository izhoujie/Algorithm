package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午5:28:14 
 * @Description: 7. 整数反转
 * 
	给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	
	示例 1:
	
	输入: 123
	输出: 321
	 示例 2:
	
	输入: -123
	输出: -321
	示例 3:
	
	输入: 120
	输出: 21
	注意:
	
	假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/reverse-integer
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-关键在判断溢出，因为溢出时必有 x!=(x*10)/10，可据此判断溢出
 *
 */
public class LeetCode_0007 {

}

class Solution_0007 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午6:03:16 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午5:25:45]  
	 * @UpdateRemark:利用溢出的特性来判断溢出：（溢出时）x!=(x*10)/10
	 *
	 */
	public int reverse(int x) {
		int rst, check;
		rst = check = 0;
		while (x != 0) {
			rst = rst * 10 + x % 10;
			// 溢出校验
			if (rst / 10 != check) {
				return 0;
			}
			check = rst;
			x /= 10;
		}
		return rst;
	}
}
