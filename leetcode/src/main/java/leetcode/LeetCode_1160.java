package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月17日 上午11:36:21 
 * @Description: 1160. 拼写单词
 *
	给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
	
	假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
	
	注意：每次拼写时，chars 中的每个字母都只能用一次。
	
	返回词汇表 words 中你掌握的所有单词的 长度之和。
	
	 
	
	示例 1：
	
	输入：words = ["cat","bt","hat","tree"], chars = "atach"
	输出：6
	解释： 
	可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
	示例 2：
	
	输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
	输出：10
	解释：
	可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
	 
	
	提示：
	
	1 <= words.length <= 1000
	1 <= words[i].length, chars.length <= 100
	所有字符串中都仅包含小写英文字母
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-既然只有小写字母，转化为26长度int数组，手动实现一个map的过程即可；
 */
public class LeetCode_1160 {

}

class Solution_1160 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月17日 上午11:36:54 
	 * @param: @param words
	 * @param: @param chars
	 * @param: @return
	 * @return: int
	 * @Description: 1-转为int数组，对比出现次数；
	 *
	 */
	public int countCharacters_1(String[] words, String chars) {
		if (words == null || words.length < 1) {
			return 0;
		}
		int[] rawArray = new int[26];
		for (char c : chars.toCharArray()) {
			rawArray[c - 'a']++;
		}

		int count = 0;
		for (String s : words) {
			if (s != null) {
				char[] cs = s.toCharArray();
				int[] cp = Arrays.copyOf(rawArray, rawArray.length);
				boolean f = true;
				for (char c : cs) {
					if (--cp[c - 'a'] < 0) {
						f = false;
						break;
					}
				}
				if (f) {
					count += s.length();
				}
			}
		}
		return count;
	}
}
