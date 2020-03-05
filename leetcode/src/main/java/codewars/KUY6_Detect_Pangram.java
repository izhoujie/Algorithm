package codewars;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月5日 下午11:38:19 
 * @Description: Detect Pangram
 *
	A pangram is a sentence that contains every single letter of the alphabet at least once. For example, the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses the letters A-Z at least once (case is irrelevant).
	
	Given a string, detect whether or not it is a pangram. Return True if it is, False if not. Ignore numbers and punctuation.
	
	题干大意：检测给定的字符串中是否所有a-z的字符都出现过；（忽略大小写）
 */
public class KUY6_Detect_Pangram {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月5日 下午11:38:53 
	 * @param: @param sentence
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-剔除所有非字母字符并转小写，然后遍历一次记录并计数；
	 *
	 */
	public boolean check_1(String sentence) {
		char[] cs = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase().toCharArray();
		boolean[] f = new boolean[26];
		Arrays.fill(f, false);
		int count = 0;
		for (char c : cs) {
			int t = c - 'a';
			if (!f[t]) {
				f[t] = true;
				count++;
			}
		}
		return count == 26;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月5日 下午11:53:28 
	 * @param: @param sentence
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-基于Stream语法的解决，简洁！
	 *
	 */
	public boolean check_2(String sentence) {
		return sentence.chars().map(Character::toLowerCase).filter(Character::isAlphabetic).distinct().count() == 26;
	}
}
