package leetcode;

/**
 * @author ZhouJie
 * @date 2020-8-10 14:38:39 
 * @Description: 696. 计数二进制子串 
 *
	给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
	
	重复出现的子串要计算它们出现的次数。
	
	示例 1 :
	
	输入: "00110011"
	输出: 6
	解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
	
	请注意，一些重复出现的子串要计算它们出现的次数。
	
	另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
	示例 2 :
	
	输入: "10101"
	输出: 4
	解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
	注意：
	
	s.length 在1到50,000之间。
	s 只包含“0”或“1”字符。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/count-binary-substrings
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0696 {

}

class Solution_0696 {
	/**
	 * @author: ZhouJie
	 * @date: 2020-8-10 14:41:19 
	 * @param: @param s
	 * @param: @return
	 * @return: int
	 * @Description: 1-01压缩，压缩连续01的串为统计数，然后计算每一个数与前一个数的最小值的累加和；
	 *
	 */
	public int countBinarySubstrings(String s) {
		int count = 0;
		if (s == null || s.length() == 0) {
			return count;
		}
		char[] cs = s.toCharArray();
		char c = cs[0];
		int m = 0, n = 0;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == c) {
				n++;
				if (n <= m) {
					count++;
				}
			} else {
				c = cs[i--];
				m = n;
				n = 0;
			}
		}
		return count;
	}
}