package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月10日 下午2:30:25 
 * @Description: 5. 最长回文子串
 *
	给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
	
	示例 1：
	
	输入: "babad"
	输出: "bab"
	注意: "aba" 也是一个有效答案。
	示例 2：
	
	输入: "cbbd"
	输出: "bb"
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/longest-palindromic-substring
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-动态规划；
		2-中心扩展；
		3-Manacher算法--较难理解（类似于KMP，先构建辅助数组）；
 */
public class LeetCode_0005 {

}

class Solution_0005 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午3:07:18 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月10日 下午3:07:18]  
	 * @UpdateRemark:1-动态规划
	 *
	 */
	public String longestPalindrome_1(String s) {
		int len = 0;
		if (s == null || (len = s.length()) < 2) {
			return s;
		}
		boolean[] p = new boolean[len];
		String rst = "";

		for (int i = len - 1; i >= 0; i--) {
			for (int j = len - 1; j >= i; j--) {
				// j-i考虑到 像OO和OHO最后中心的奇偶问题
				p[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || p[j - 1]);
				if (p[j] && (j - i + 1 > rst.length())) {
					rst = s.substring(i, j + 1);
				}
			}
		}
		return rst;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午3:34:44 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月10日 下午3:34:44]  
	 * @UpdateRemark:2-中心扩展--优化后
	 *
	 */
	public String longestPalindrome_2(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		int[] rang = new int[2];
		char[] cs = s.toCharArray();

		for (int i = 0; i < cs.length; i++) {
			// 把回文看成中间部分都是同一字符且左右对称，寻找下一个与当前字符不同的位置
			i = fastMove(cs, i, rang);
			// 若剩余长度不足已知最大长度的一半时
			if ((cs.length - i) * 2 < (rang[1] - rang[0] + 1)) {
				break;
			}
		}

		return s.substring(rang[0], rang[1] + 1);
	}

	private int fastMove(char[] cs, int low, int[] rang) {
		int high = low;
		int len = cs.length;
		// 寻找下一个与low不等的字符
		while (high < len - 1 && cs[high + 1] == cs[low]) {
			high++;
		}
		int nextI = high;
		// 开始校验左右扩散校验
		while (low > 0 && high < len - 1 && cs[low - 1] == cs[high + 1]) {
			low--;
			high++;

		}
		if (high - low > rang[1] - rang[0]) {
			rang[0] = low;
			rang[1] = high;
		}
		return nextI;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月10日 下午3:55:19 
	 * @Description: TODO(方法简述) 
	 * @return String 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月10日 下午3:55:19]  
	 * @UpdateRemark:3-Manacher算法--待研究
	 *
	 */
	public String longestPalindrome_3(String s) {
		return null;
	}
}
