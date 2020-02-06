package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月4日 下午6:05:49 
 * @Description: 32. 最长有效括号
 *
	给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
	
	示例 1:
	
	输入: "(()"
	输出: 2
	解释: 最长有效括号子串为 "()"
	示例 2:
	
	输入: ")()())"
	输出: 4
	解释: 最长有效括号子串为 "()()"
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/longest-valid-parentheses
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-暴力遍历--超时...
		2-前后各遍历一般，返回最大匹配值；利用了括号左右闭合及对称特性；（来自LeetCode评论区解法）
 */
public class LeetCode_0032 {
	public static void main(String[] args) {
		Solution_0032 o = new Solution_0032();
		String test = "))(((()()()))))()))";
		System.out.println(o.longestValidParentheses_1(test));
	}
}

class Solution_0032 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午6:51:41 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 1-暴力求解，全遍历，会超时..
	 *
	 */
	public int longestValidParentheses_1(String s) {
		int n = 0;
		if (s == null || (n = s.length()) == 0) {
			return 0;
		}
		int i = n % 2 == 0 ? n : n - 1;
		for (; i > 1; i -= 2) {
			for (int j = 0; j < n - i + 1; j++) {
				if (check(s.substring(j, j + i))) {
					return i;
				}
			}
		}
		return 0;
	}

	private boolean check(String s) {
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				count++;
			} else {
				count--;
				if (count < 0) {
					return false;
				}
			}
		}
		return count == 0;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午6:52:09 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 2-
	 *
	 */
	public int longestValidParentheses_2(String s) {
		int n = 0;
		if (s == null || (n = s.length()) == 0) {
			return 0;
		}
		return Math.max(check(s.toCharArray(), 0, n, 1, '('), check(s.toCharArray(), n - 1, -1, -1, ')'));
	}

	private int check(char[] cs, int start, int end, int k, char c) {
		int max = 0, sum = 0, currLen = 0, validLen = 0;
		for (; start != end; start += k) {
			// 匹配并计算currLen
			sum += (cs[start] == c ? 1 : -1);
			currLen++;
			// 若sum<0，说明当前括号已不匹配，记录已获得的validLen与max的最大长度，并复位
			if (sum < 0) {
				max = max > validLen ? max : validLen;
				sum = currLen = validLen = 0;
				// 如sum=0，说明当前恰好匹配，记录当前的currLen为validLen，并继续尝试匹配
			} else if (sum == 0) {
				validLen = currLen;
			}
		}
		// 返回时再对validLen与max进行一次取大判断
		return max > validLen ? max : validLen;
	}
}