package leetcode;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年1月6日 下午11:06:08 
 * @Description: 20. 有效的括号
 *
	给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	
	有效字符串需满足：
	
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
	
	示例 1:
	
	输入: "()"
	输出: true
	示例 2:
	
	输入: "()[]{}"
	输出: true
	示例 3:
	
	输入: "(]"
	输出: false
	示例 4:
	
	输入: "([)]"
	输出: false
	示例 5:
	
	输入: "{[]}"
	输出: true
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/valid-parentheses
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-使用栈即可；
		2-使用数组；比1的效率高！
 */
public class LeetCode_0020 {

}

class Solution_0020 {

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午10:58:00 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午10:58:00]  
	 * @UpdateRemark:2-直接存储，对比校验；
	 *
	 */
	public boolean isValid_1(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) {
			return true;
		}
		if (len % 2 == 1) {
			return false;
		}
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char pop = stack.pop();
				if (!(pop == '(' && c == ')' || pop == '[' && c == ']' || pop == '{' && c == '}')) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午11:05:15 
	 * @Description: TODO(方法简述) 
	 * @return boolean 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午11:05:15]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public boolean isValid_2(String s) {
		int len;
		if (s == null || (len = s.length()) == 0) {
			return true;
		}
		if (len % 2 == 1) {
			return false;
		}
		char[] check = new char[len];
		int k = 0;
		for (char c : s.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				check[k++] = c;
			} else {
				k--;
				if (k < 0 || !(check[k] == '(' && c == ')' || check[k] == '[' && c == ']'
						|| check[k] == '{' && c == '}')) {
					return false;
				}

			}
		}
		return k == 0;
	}
}
