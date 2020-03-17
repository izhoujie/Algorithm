package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月16日 上午12:21:12 
 * @Description: 面试题 01.06. 字符串压缩
 *
	字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
	
	示例1:
	
	 输入："aabcccccaaa"
	 输出："a2b1c5a3"
	示例2:
	
	 输入："abbccd"
	 输出："abbccd"
	 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
	提示：
	
	字符串长度在[0, 50000]范围内。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/compress-string-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-一次遍历使用StringBuilder保存，最后对比一次长度；
 */
public class LeetCode_Satine_01_06 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月16日 上午12:31:24 
	 * @date: 2020年3月17日 上午11:09:14 
	 * @param: @param S
	 * @param: @return
	 * @return: String
	 * @Description: 1-
	 *
	 */
	public String compressString(String S) {
		int len = 0;
		if (S == null || (len = S.length()) < 3) {
			return S;
		}
		char[] cs = S.toCharArray();
		StringBuilder sb = new StringBuilder(len);
		int count = 0;
		char ck = cs[0];
		sb.append(ck);
		for (char c : cs) {
			if (ck == c) {
				count++;
			} else {
				sb.append(count).append(c);
				ck = c;
				count = 1;
			}
		}
		if (count > 0) {
			sb.append(count);
		}
		return sb.length() < len ? sb.toString() : S;
	}
}
