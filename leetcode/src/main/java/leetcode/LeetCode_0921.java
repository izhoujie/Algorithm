package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月12日 下午10:14:26 
 * @Description: 921. 使括号有效的最少添加
 *
	给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
	
	从形式上讲，只有满足下面几点之一，括号字符串才是有效的：
	
	它是一个空字符串，或者
	它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
	它可以被写作 (A)，其中 A 是有效字符串。
	给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。
	
	 
	
	示例 1：
	
	输入："())"
	输出：1
	示例 2：
	
	输入："((("
	输出：3
	示例 3：
	
	输入："()"
	输出：0
	示例 4：
	
	输入："()))(("
	输出：4
	 
	
	提示：
	
	S.length <= 1000
	S 只包含 '(' 和 ')' 字符。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-一个变量记录左括号溢出量，另一个变量记录右括号溢出量；
		2-官方：平衡法，类似1，代码更简洁；
 */
public class LeetCode_0921 {

}

class Solution_0921 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月12日 下午10:50:45 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月12日 下午10:50:45]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public int minAddToMakeValid_1(String S) {
		if (S == null) {
			return 0;
		}
		int n = 0, k = 0;

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '(') {
				k++;
			} else if (c == ')') {
				if (k == 0) {
					n++;
				} else {
					k--;
				}
			}
		}
		return k == 0 ? n : n + k;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月12日 下午10:55:29 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月12日 下午10:55:29]  
	 * @UpdateRemark:[本次修改内容]  
	 *
	 */
	public int minAddToMakeValid_2(String S) {
		if (S == null) {
			return 0;
		}
		int m = 0, n = 0;
		for (char c : S.toCharArray()) {
			m += c == '(' ? 1 : -1;
			if (m == -1) {
				n++;
				m++;
			}
		}
		return m + n;
	}
}
