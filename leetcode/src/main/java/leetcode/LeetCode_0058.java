package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月18日 下午11:11:24 
 * @Description: 58. 最后一个单词的长度
 *
	给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
	
	如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
	
	如果不存在最后一个单词，请返回 0 。
	
	说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
	
	 
	
	示例:
	
	输入: "Hello World"
	输出: 5
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/length-of-last-word
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-直接split("\\s")取最后一个；
		2-尾部向前检测到第一个空白返回；
 */
public class LeetCode_0058 {

}

class Solution_0058 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月18日 下午11:16:51 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int lengthOfLastWord_1(String s) {
		if (s == null || (s = s.trim()).length() == 0) {
			return 0;
		}
		String[] split = s.split(" ");
		return split[split.length - 1].length();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月18日 下午11:17:12 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 2-
	 *
	 */
	public int lengthOfLastWord_2(String s) {
		int len = 0;
		if (s == null || (len = (s = s.trim()).length()) == 0) {
			return len;
		}
		if (!s.contains(" ")) {
			return len;
		}
		int index = len - 1;
		while (--index > -1 && s.charAt(index) != ' ')
			;

		return len - index - 1;
	}
}
