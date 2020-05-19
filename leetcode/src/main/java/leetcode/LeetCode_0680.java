package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月19日 上午12:24:57 
 * @Description: 680. 验证回文字符串 Ⅱ
 *
	给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
	
	示例 1:
	
	输入: "aba"
	输出: True
	示例 2:
	
	输入: "abca"
	输出: True
	解释: 你可以删除c字符。
	注意:
	
	字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/valid-palindrome-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0680 {

}

class Solution_0680 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午12:25:24 
	 * @param: @param s
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-两遍指针遍历，记录指针的位置，因为总共有三次机会去验证是否可改造成回文；
	 *
	 */
	private int start = 0;
	private int end = 0;

	public boolean validPalindrome_1(String s) {
		end = s.length() - 1;
		if (!check(s)) {
			int ss = start, ee = end;
			start++;
			if (!check(s)) {
				start = ss;
				end = ee;
				end--;
				return check(s);
			}
		}
		return true;
	}

	private boolean check(String s) {
		if (start > end) {
			return false;
		} else {
			while (start <= end) {
				if (s.charAt(start) == s.charAt(end)) {
					start++;
					end--;
				} else {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午12:52:52 
	 * @param: @param s
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-递归验证，控制只递归两层；
	 *
	 */
	public boolean validPalindrome_2(String s) {
		return check(s, 0, s.length() - 1, true);
	}

	private boolean check(String s, int start, int end, boolean f) {
		while (start < end) {
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} else {
				// 若是首次，则进行删除左或右再进行验证，否则说明无法改造为回文
				return f && (check(s, start + 1, end, false) || check(s, start, end - 1, false));
			}
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月19日 上午1:09:12 
	 * @param: @param s
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-单个方法内增加变量解决；
	 *
	 */
	public boolean validPalindrome_3(String s) {
		int left = 0, right = s.length() - 1;
		boolean leftDelete = true, rightDelete = true;
		while (left < right) {
			if (s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			} else {
				// 尝试删除左边
				if (leftDelete) {
					left++;
					leftDelete = false;
					// 尝试删除右边，此时左边要回退一个字符
				} else if (rightDelete) {
					left--;
					right--;
					rightDelete = false;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}