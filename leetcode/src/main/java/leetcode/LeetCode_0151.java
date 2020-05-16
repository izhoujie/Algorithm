package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月10日 下午5:59:51 
 * @Description: 151. 翻转字符串里的单词
 *
	给定一个字符串，逐个翻转字符串中的每个单词。
	
	 
	
	示例 1：
	
	输入: "the sky is blue"
	输出: "blue is sky the"
	示例 2：
	
	输入: "  hello world!  "
	输出: "world! hello"
	解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
	示例 3：
	
	输入: "a good   example"
	输出: "example good a"
	解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
	 
	
	说明：
	
	无空格字符构成一个单词。
	输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
	如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_0151 {

}

class Solution_0151 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月10日 下午6:09:53 
	 * @param: @param s
	 * @param: @return
	 * @return: String
	 * @Description: 1-使用正则匹配所有空白字符分割单词然后逆序拼接即可；缺点：正则分割比较耗时
	 *
	 */
	public String reverseWords_1(String s) {
		if ((s = s.trim()).length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(s.length());
		String s1 = " ";
		for (String s0 : s.split("\\s+")) {
			sb.insert(0, s0).insert(0, s1);
		}
		return sb.substring(1, sb.length()).toString();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月10日 下午6:11:11 
	 * @param: @param 
	 * @param: @return
	 * @return: String
	 * @Description: 2-按单个空格分割，拼接时跳过空格；
	 *
	 */
	public String reverseWords_2(String s) {
		StringBuilder sb = new StringBuilder(s.length());
		String s1 = " ";
		for (String s0 : s.split(" ")) {
			if (!"".equals(s0)) {
				sb.insert(0, s0).insert(0, s1);
			}
		}
		return sb.length() == 0 ? "" : sb.substring(1).toString();
	}

}