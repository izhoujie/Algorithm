package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年1月7日 下午10:39:35 
 * @Description: 22. 括号生成
 *
	给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
	
	例如，给出 n = 3，生成结果为：
	
	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/generate-parentheses
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-根据括号左右闭合的特性进行先左后右的递归；
 */
public class LeetCode_0022 {

}

class Solution_0022 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月3日 下午11:33:16 
	 * @param: @param n
	 * @param: @return
	 * @return: List<String>
	 * @Description: 1-根据左右闭合特性，依次放置n个“（”和“）”且放“）”时已放“（”的数量不能大于将要放入“）”的数量；
	 *
	 */
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		makeStr(list, "", 0, 0, n);
		return list;
	}

	private void makeStr(List<String> list, String str, int open, int close, int n) {
		// 若")"数达n，说明"("和")"数均已达到n个
		if (close == n) {
			list.add(str);
		} else {
			// 若"("未达最大数n则可以加一个"("
			if (open < n) {
				makeStr(list, str + "(", open + 1, close, n);
			}
			// 若")"数小于"("数，则可以加一个")"
			if (close < open) {
				makeStr(list, str + ")", open, close + 1, n);
			}
		}
	}
}
