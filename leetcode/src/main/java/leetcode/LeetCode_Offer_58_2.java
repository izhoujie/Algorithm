package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月16日 下午11:30:08 
 * @Description: 面试题58 - II. 左旋转字符串
 *
	字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

	示例 1：
	
	输入: s = "abcdefg", k = 2
	输出: "cdefgab"
	示例 2：
	
	输入: s = "lrloseumgh", k = 6
	输出: "umghlrlose"
	 
	
	限制：
	
	1 <= k < s.length <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_58_2 {

}

class Solution_Offer_58_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月16日 下午11:31:12 
	 * @param: @param s
	 * @param: @param n
	 * @param: @return
	 * @return: String
	 * @Description: 1-分两次遍历拼接到StringBuilder；
	 *
	 */
	public String reverseLeftWords_1(String s, int n) {
		int len = s.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = n; i < len; i++) {
			sb.append(s.charAt(i));
		}
		for (int i = 0; i < n; i++) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月16日 下午11:35:25 
	 * @param: @param s
	 * @param: @param n
	 * @param: @return
	 * @return: String
	 * @Description: 2-利用取模%特性一次遍历；
	 *
	 */
	public String reverseLeftWords_2(String s, int n) {
		int len = s.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = n; i < len + n; i++) {
			sb.append(s.charAt(i % len));
		}
		return sb.toString();
	}
}