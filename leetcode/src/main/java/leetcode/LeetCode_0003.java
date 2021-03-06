package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月4日 下午1:56:17 
 * @Description: 3. 无重复字符的最长子串
 *
	给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	
	示例 1:
	
	输入: "abcabcbb"
	输出: 3 
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
	示例 2:
	
	输入: "bbbbb"
	输出: 1
	解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
	示例 3:
	
	输入: "pwwkew"
	输出: 3
	解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
	     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-定义两个指针，ij，然后indexOf(j+1,i)，如果-1，则j++，否则i=index+1或index=j时，j++，i=j，并且每次不为-1时计算max(j-i+1)；
		2-使用码表记录字符出现的最新位置，并计算无重复段的长度；（来自leetcode评论区解法） 
 */
public class LeetCode_0003 {

}

class Solution_0003 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月5日 下午8:55:49 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 上午11:08:25]  
	 * @UpdateRemark:滑动窗口思想，双指针
	 *
	 */
	public int lengthOfLongestSubstring(String s) {
		int len = 0;
		if (s == null || (len = s.length()) < 2) {
			return len;
		}
		int left = 0, right = 0, max = 0;
		while (right < len) {
			int index = s.indexOf(s.charAt(right), left);
			// 只要当前字符的首次出现位置index不在[left,right]区间内，则找到一个可能值，计算并记录，否则把left的位置重置为index+1
			if (index < left || index >= right) {
				max = Math.max(max, right - left + 1);
			} else {
				left = index + 1;
			}
			right++;
		}
		return max;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 上午11:35:15 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 上午11:35:15]  
	 * @UpdateRemark:2-使用码表记录字符出现的最新位置，并计算无重复段的长度 
	 *
	 */
	public int lengthOfLongestSubstring_1(String s) {
		int len = 0;
		if (s == null || (len = s.length()) < 2) {
			return len;
		}
		char[] cs = s.toCharArray();
		// 定义ascii的256字符码表
		int[] ASCII = new int[256];
		int start = 0, rst = 0;
		for (int i = 0; i < len; i++) {
			// start记录截止i位置无重复字符的起始位置，若ASCII[cs[i]]比start大，说明在[start,i]之间出现了重复字符cs[i]，需要将start更新为ASCII[cs[i]]
			start = Math.max(ASCII[cs[i]], start);
			// 计算当前无重复段的长度
			rst = Math.max(rst, i - start + 1);
			// 记录 cs[i]字符的最新位置；
			ASCII[cs[i]] = i + 1;
		}
		return rst;
	}
}