package leetcode;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author ZhouJie
 * @date 2020年5月8日 下午7:54:53 
 * @Description: 面试题50. 第一个只出现一次的字符 
 *
	在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
	
	示例:
	
	s = "abaccdeff"
	返回 "b"
	
	s = "" 
	返回 " "
	 
	
	限制：
	
	0 <= s 的长度 <= 50000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_50 {

}

class Solution_Offer_50 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月8日 下午7:55:24 
	 * @param: @param s
	 * @param: @return
	 * @return: char
	 * @Description: 1-直接用indexOf和lastIndexOf方法；
	 *
	 */
	public char firstUniqChar_1(String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (s.indexOf(c) == s.lastIndexOf(c)) {
				return c;
			}
		}
		return ' ';
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月8日 下午8:02:06 
	 * @param: @param s
	 * @param: @return
	 * @return: char
	 * @Description: 2-用LinkedHashMap统计；
	 *
	 */
	public char firstUniqChar_2(String s) {
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		Iterator<Character> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Character c = iterator.next();
			if (map.get(c) == 1) {
				return c;
			}
		}
		return ' ';
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月8日 下午8:14:41 
	 * @param: @param s
	 * @param: @return
	 * @return: char
	 * @Description: 3-统计字符出现次数；
	 *
	 */
	public char firstUniqChar_3(String s) {
		int[] all = new int[256];
		char[] cs = s.toCharArray();
		for (char c : cs) {
			all[c]++;
		}
		for (char c : cs) {
			if (all[c] == 1) {
				return c;
			}
		}
		return ' ';
	}

}
