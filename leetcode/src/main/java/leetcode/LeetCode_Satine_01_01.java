package leetcode;

import java.util.HashSet;

/**
 * @author ZhouJie
 * @date 2020年5月11日 下午11:48:09 
 * @Description: 面试题 01.01. 判定字符是否唯一
 *
	实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
	
	示例 1：
	
	输入: s = "leetcode"
	输出: false 
	示例 2：
	
	输入: s = "abc"
	输出: true
	限制：
	
	0 <= len(s) <= 100
	如果你不使用额外的数据结构，会很加分。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/is-unique-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_01_01 {

}

class Solution_Satine_01_01 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月11日 下午11:48:34 
	 * @param: @param astr
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-indexOf和lastIndexOf配合使用，效率慢；
	 *
	 */
	public boolean isUnique_1(String astr) {
		if (astr == null || astr.length() == 0) {
			return true;
		}
		for (int i = 0; i < astr.length(); i++) {
			char c = astr.charAt(i);
			if (astr.indexOf(c) != astr.lastIndexOf(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 上午12:02:01 
	 * @param: @param astr
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-使用HashSet；
	 *
	 */
	public boolean isUnique_2(String astr) {
		if (astr == null || astr.length() == 0) {
			return true;
		}
		HashSet<Character> set = new HashSet<Character>();
		for (Character c : astr.toCharArray()) {
			if (set.contains(c)) {
				return false;
			}
			set.add(c);
		}
		return true;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月12日 上午12:12:24 
	 * @param: @param astr
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-使用位运算；
	 *
	 */
	public boolean isUnique_3(String astr) {
		long[] bucket = new long[4];
		int k, index;
		long bits;
		for (int i = 0; i < astr.length(); i++) {
			k = (int) astr.charAt(i);
			index = k / 64;
			bits = 1 << (k % 64);
			if ((bucket[index] & bits) != 0) {
				return false;
			}
			bucket[index] |= bits;
		}
		return true;
	}

}