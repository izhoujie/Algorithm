package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月13日 下午11:45:48 
 * @Description: 28. 实现 strStr()
 *
	实现 strStr() 函数。
	
	给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
	
	示例 1:
	
	输入: haystack = "hello", needle = "ll"
	输出: 2
	示例 2:
	
	输入: haystack = "aaaaa", needle = "bba"
	输出: -1
	说明:
	
	当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
	
	对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/implement-strstr
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-双指针比对，滑动窗口；
 */
public class LeetCode_0028 {

}

class Solution_0028 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午3:57:03 
	 * @param: @param haystack
	 * @param: @param needle
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null) {
			return -1;
		}
		int len1 = 0, len2 = 0;
		if ((len1 = haystack.length()) >= 0 && (len2 = needle.length()) == 0) {
			return 0;
		}
		if (len1 < len2) {
			return -1;
		}
		int k;
		for (int i = 0; i < len1 - len2 + 1; i++) {
			k = i;
			for (int j = 0; j < len2; j++) {
				if (haystack.charAt(k) == needle.charAt(j)) {
					k++;
				} else {
					break;
				}
			}
			if (k - i == len2) {
				return i;
			}
		}
		return -1;
	}
}
