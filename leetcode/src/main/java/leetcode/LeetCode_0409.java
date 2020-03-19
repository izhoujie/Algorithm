package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月19日 下午12:25:32 
 * @Description: 409. 最长回文串
 *
	给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
	
	在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
	
	注意:
	假设字符串的长度不会超过 1010。
	
	示例 1:
	
	输入:
	"abccccdd"
	
	输出:
	7
	
	解释:
	我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
	思路：1-最大的回文串是取所有出现字母的偶数次和且若小于源字符长度可额外+1
 */
public class LeetCode_0409 {

}

class Solution_0409 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月19日 下午1:01:45 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int longestPalindrome(String s) {
		int len = 0;
		if (s == null || (len = s.length()) < 2) {
			return len;
		}
		int[] all = new int['z' - 'A' + 1];
		// 统计所有字母出现次数
		for (int i = 0; i < len; i++) {
			all[s.charAt(i) - 'A']++;
		}
		int maxLen = 0;
		for (int i : all) {
			// 最多取其偶数次
			maxLen += i - (i & 1);
		}
		return maxLen < len ? ++maxLen : maxLen;
	}
}