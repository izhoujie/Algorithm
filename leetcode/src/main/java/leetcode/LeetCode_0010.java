package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午7:53:23 
 * @Description: 10. 正则表达式匹配
 *
	给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
	
	'.' 匹配任意单个字符
	'*' 匹配零个或多个前面的那一个元素
	所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
	
	说明:
	
	s 可能为空，且只包含从 a-z 的小写字母。
	p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
	示例 1:
	
	输入:
	s = "aa"
	p = "a"
	输出: false
	解释: "a" 无法匹配 "aa" 整个字符串。
	示例 2:
	
	输入:
	s = "aa"
	p = "a*"
	输出: true
	解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
	示例 3:
	
	输入:
	s = "ab"
	p = ".*"
	输出: true
	解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
	示例 4:
	
	输入:
	s = "aab"
	p = "c*a*b"
	输出: true
	解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
	示例 5:
	
	输入:
	s = "mississippi"
	p = "mis*is*p*."
	输出: false
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/regular-expression-matching
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-自底向上的动态规划；（待研究）
 */
public class LeetCode_0010 {

}

class Solution_0010 {
	public boolean isMatch(String s, String p) {
		int lenS = 0;
		int lenP = 0;
		if (p == null || (lenP = p.length()) == 0) {
			return s == null || (lenS = s.length()) == 0;
		}
		boolean[][] dp = new boolean[lenS + 1][lenP + 1];
		dp[lenS][lenP] = true;

		for (int i = lenS; i >= 0; i--) {
			for (int j = lenP - 1; j >= 0; j--) {
				boolean first = (i < lenS && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
				if (j + 1 < lenP && p.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || first && dp[i + 1][j];
				} else {
					dp[i][j] = first && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}
