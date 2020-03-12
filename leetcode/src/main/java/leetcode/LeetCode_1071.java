package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月12日 下午3:32:53 
 * @Description: 1071. 字符串的最大公因子
 *
	对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
	
	返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
	
	 
	
	示例 1：
	
	输入：str1 = "ABCABC", str2 = "ABC"
	输出："ABC"
	示例 2：
	
	输入：str1 = "ABABAB", str2 = "ABAB"
	输出："AB"
	示例 3：
	
	输入：str1 = "LEET", str2 = "CODE"
	输出：""
	 
	
	提示：
	
	1 <= str1.length <= 1000
	1 <= str2.length <= 1000
	str1[i] 和 str2[i] 为大写英文字母
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先判断是否有公因子串，然后转为就长度的gcd；
 */
public class LeetCode_1071 {
	public static void main(String[] args) {
		System.out.println(new Solution_1071().gcdOfStrings("ABABAB", "ABAB"));
	}
}

class Solution_1071 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月12日 下午3:33:40 
	 * @param: @param str1
	 * @param: @param str2
	 * @param: @return
	 * @return: String
	 * @Description: 1-
	 *
	 */
	public String gcdOfStrings(String str1, String str2) {
		if ((str1 + str2).equals(str2 + str1)) {
			return str1.substring(0, gcd(str1.length(), str2.length()));
		} else {
			return "";
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月12日 下午10:45:14 
	 * @param: @param a
	 * @param: @param b
	 * @param: @return
	 * @return: int
	 * @Description: 求a和b的最小公倍数；
	 *
	 */
	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}