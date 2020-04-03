package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午6:13:52 
 * @Description:8. 字符串转换整数 (atoi)
 *
	 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
	
	首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
	
	当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
	
	该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
	
	注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
	
	在任何情况下，若函数不能进行有效的转换时，请返回 0。
	
	说明：
	
	假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
	
	示例 1:
	
	输入: "42"
	输出: 42
	示例 2:
	
	输入: "   -42"
	输出: -42
	解释: 第一个非空白字符为 '-', 它是一个负号。
	     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
	示例 3:
	
	输入: "4193 with words"
	输出: 4193
	解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
	示例 4:
	
	输入: "words and 987"
	输出: 0
	解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
	     因此无法执行有效的转换。
	示例 5:
	
	输入: "-91283472332"
	输出: -2147483648
	解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
	     因此返回 INT_MIN (−231) 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/string-to-integer-atoi
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先左右trim()，再校验第一个字符是否是"-"号 是则i=1否则i=0，然后判断首字符，
		接下来的流程就是NO.7题的思路
 */
public class LeetCode_0008 {
	public static void main(String[] args) {
		Solution_0008 solution_0008 = new Solution_0008();
		System.out.println(solution_0008.myAtoi("2147483648"));
		Double.valueOf("53454.sdrf");
	}

}

class Solution_0008 {

	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午7:00:52 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月10日 下午7:00:52]  
	 * @UpdateRemark:1-思路：
	 * 					-先trim()左右去空并再次验非空；
	 * 					-校验首字符是+-的情况
	 * 					-逐个取字符转化数字并校验是否溢出
	 */
	public int myAtoi(String str) {
		if (str == null) {
			return 0;
		}
		// 去除左右空白字符，且去除后长度不能为0
		str = str.trim();
		int len = str.length();
		if (len < 1) {
			return 0;
		}
		int flag = 1;
		int i = 0;
		char c = str.charAt(0);
		// 首个字符为+或-的预处理，同时记录符号
		if (c == '-' || c == '+') {
			i = 1;
			if (c == '-') {
				flag = -1;
			}
		}
		int rst = 0;
		int check = 0;
		// 逐个字符转化，每次/10与上一次的值校验用以判断是否溢出
		for (; i < len; i++) {
			int num = str.charAt(i) - '0';
			if (num >= 0 && num <= 9) {
				rst = rst * 10 + num * flag;
				// 溢出校验，若本次结果已溢出，那么当前值/10必不等于上一次的值，利用溢出去校验溢出，巧妙
				if (rst / 10 != check) {
					return flag == 1 ? ((1 << 31) - 1) : (-1 << 31);
				}
				check = rst;
			} else {
				return rst;
			}
		}
		return rst;
	}
}