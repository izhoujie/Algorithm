package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午6:06:08 
 * @Description: 面试题05. 替换空格
 *
	请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
	
	
	示例 1：
	
	输入：s = "We are happy."
	输出："We%20are%20happy."
	 
	
	限制：
	
	0 <= s 的长度 <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_05 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午6:26:31 
	 * @param: @param s
	 * @param: @return
	 * @return: String
	 * @Description: 1-逐位校验替换
	 *
	 */
	public String replaceSpace_1(String s) {
		StringBuilder sb = new StringBuilder();
		char ch = ' ';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ch) {
				sb.append("%20");
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午6:26:44 
	 * @param: @param s
	 * @param: @return
	 * @return: String
	 * @Description: 2-库函数
	 *
	 */
	public String replaceSpace_2(String s) {
		return s.replaceAll(" ", "%20");
	}

}
