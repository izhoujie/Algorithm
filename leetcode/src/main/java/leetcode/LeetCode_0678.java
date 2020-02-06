package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月11日 下午1:16:10 
 * @Description: 678. 有效的括号字符串
 *
	给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
	
	任何左括号 ( 必须有相应的右括号 )。
	任何右括号 ) 必须有相应的左括号 ( 。
	左括号 ( 必须在对应的右括号之前 )。
	* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
	一个空字符串也被视为有效字符串。
	示例 1:
	
	输入: "()"
	输出: True
	示例 2:
	
	输入: "(*)"
	输出: True
	示例 3:
	
	输入: "(*))"
	输出: True
	注意:
	
	字符串大小将在 [1，100] 范围内。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/valid-parenthesis-string
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-平衡法；
 */
public class LeetCode_0678 {

}

class Solution_0678 {
	public boolean checkValidString(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}
		char[] cs = s.toCharArray();
		int left = 0, right = 0;

		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if (c == '(') {
				left++;
				right++;
			} else if (c == ')') {
				if (left > 0) {
					left--;
				}
				right--;
			} else if (c == '*') {
				// 若left大于0说明"("多，尽量匹配"("，同时")"right也+1，为后续匹配到")"时可-1，两手准备
				if (left > 0) {
					left--;
				}
				right++;
			}
			// 若*号作为"("时仍然不够匹配")"，即*号部分right++后在")"部分right--小于0，说明")"太多，此时匹配完有多余的")"，剩余的部分已无进行的必要，直接返回；
			if (right < 0) {
				return false;
			}
		}
		// left=0，则说明可以匹配，此时right=0则是完美匹配，*都起到了实际匹配作用，right>0说明部分或全部*被当作空字符串处理了
		return left == 0;
	}
}