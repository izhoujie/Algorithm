package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月12日 下午11:05:57 
 * @Description: 415. 字符串相加
 *
	给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
	
	注意：
	
	num1 和num2 的长度都小于 5100.
	num1 和num2 都只包含数字 0-9.
	num1 和num2 都不包含任何前导零。
	你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/add-strings
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-倒序依次取字符串的值进行计算，注意进位值carry的处理；
		2-1需要一个额外的空间，利用两个字符串中最大的那个串用来保存结果，注意最后carry值的处理；
 */
public class LeetCode_0415 {
}

class Solution_0415 {
	/**
	 * @author: ZhouJie
	 * @date: 2020-8-3 10:55:26 
	 * @param: @param num1
	 * @param: @param num2
	 * @param: @return
	 * @return: String
	 * @Description: 倒序相加，使用StringBuilder
	 *
	 */
	public String addStrings_1(String num1, String num2) {
		num1 = num1 == null ? "0" : num1;
		num2 = num2 == null ? "0" : num2;
		StringBuilder sb = new StringBuilder();
		int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
		while (i >= 0 || j >= 0) {
			int a = i < 0 ? 0 : num1.charAt(i) - '0';
			int b = j < 0 ? 0 : num2.charAt(j) - '0';
			carry += a + b;
			sb.insert(0, carry % 10);
			carry /= 10;
			i--;
			j--;
		}
		return carry > 0 ? sb.insert(0, carry).toString() : sb.toString();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020-8-3 10:55:49 
	 * @param: @param num1
	 * @param: @param num2
	 * @param: @return
	 * @return: String
	 * @Description: 倒序相加，使用char数组
	 *
	 */
	public String addStrings_2(String num1, String num2) {
		num1 = num1 == null ? "0" : num1;
		num2 = num2 == null ? "0" : num2;
		int len1 = num1.length();
		int len2 = num2.length();
		if (len1 > len2) {
			String s = num1;
			num1 = num2;
			num2 = s;
			int t = len1;
			len1 = len2;
			len2 = t;
		}
		char[] cs1 = num1.toCharArray();
		char[] cs2 = num2.toCharArray();
		char c;
		int p = len2 - len1;
		boolean carry = false;
		for (int i = len1 - 1; i >= 0; i--) {
			c = (char) (cs1[i] + cs2[p + i] - '0');
			if (carry) {
				c++;
			}
			if (c > '9') {
				c -= 10;
				carry = true;
			} else {
				carry = false;
			}
			cs2[p + i] = c;
		}
		if (len2 > len1 && carry) {
			for (int i = p - 1; i >= 0; i--) {
				if (!carry) {
					break;
				}
				c = cs2[i];
				c++;
				if (c > '9') {
					c -= 10;
					carry = true;
				} else {
					carry = false;
				}
				cs2[i] = c;
			}
		}
		return carry ? "1" + new String(cs2) : new String(cs2);
	}
}
