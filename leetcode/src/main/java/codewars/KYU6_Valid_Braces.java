package codewars;

import java.util.Stack;

/**
 * @author ZhouJie
 * @date 2020年2月26日 下午9:08:38 
 * @Description: Valid Braces
 *
	Write a function that takes a string of braces, and determines if the order of the braces is valid. It should return true if the string is valid, and false if it's invalid.
	
	This Kata is similar to the Valid Parentheses Kata, but introduces new characters: brackets [], and curly braces {}. Thanks to @arnedag for the idea!
	
	All input strings will be nonempty, and will only consist of parentheses, brackets and curly braces: ()[]{}.
	
	What is considered Valid?
	A string of braces is considered valid if all braces are matched with the correct brace.
	
	Examples
	"(){}[]"   =>  True
	"([{}])"   =>  True
	"(}"       =>  False
	"[(])"     =>  False
	"[({})](]" =>  False
 */
public class KYU6_Valid_Braces {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月26日 下午9:37:00 
	 * @param: @param braces
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-顺序先考虑放入栈再考虑对比；
	 *
	 */
	public boolean isValid_1(String braces) {
		if (braces == null) {
			return true;
		}
		Stack<Character> stack = new Stack<Character>();
		for (char c : braces.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				Character popC = stack.pop();
				if (!(popC == '(' && c == ')' || popC == '[' && c == ']' || popC == '{' && c == '}')) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月26日 下午9:37:25 
	 * @param: @param braces
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-转换思路，先考虑对比再考虑放入栈；
	 *
	 */
	public boolean isValid_2(String braces) {
		if (braces == null) {
			return true;
		}
		Stack<Character> stack = new Stack<Character>();
		for (char c : braces.toCharArray()) {
			if (!stack.isEmpty() && check(stack.peek(), c)) {
				stack.pop();
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	private boolean check(char c1, char c2) {
		return c1 == '(' && c2 == ')' || c1 == '[' && c2 == ']' || c1 == '{' && c2 == '}';
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月26日 下午9:47:33 
	 * @param: @param braces
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-成对删除的思路，至多删除braces.length()/2次，braces将会成为一个空串；
	 *
	 */
	public boolean isValid_3(String braces) {
		if (braces == null) {
			return true;
		}
		int before, after, len = braces.length();
		for (int i = 0; i < len / 2; i++) {
			before = braces.length();
			if (before == 0) {
				return true;
			}
			braces = braces.replaceAll("\\(\\)", "").replaceAll("\\[\\]", "").replaceAll("\\{\\}", "");
			after = braces.length();
			if (before == after) {
				return false;
			}
		}
		return braces.isEmpty();
	}
}
