package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年1月16日 下午6:20:03 
 * @Description: 30. 串联所有单词的子串
 *
	给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
	
	注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
	
	 
	
	示例 1：
	
	输入：
	  s = "barfoothefoobarman",
	  words = ["foo","bar"]
	输出：[0,9]
	解释：
	从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
	输出的顺序不重要, [9,0] 也是有效答案。
	示例 2：
	
	输入：
	  s = "wordgoodgoodgoodbestword",
	  words = ["word","good","best","word"]
	输出：[]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先扫描记录单词出现的次数map1，再次循环移动并记录单词是否出现及次数map2，最后校验两个map是否相等；
		2-构造滑动窗口，优化对比判定；（来自LeetCode已提交解法）
 */
public class LeetCode_0030 {
	public static void main(String[] args) {
		String words1[] = { "foo", "bar" };
		String words2[] = { "word", "good", "best", "good" };
		System.out.println(new Solution_0030().findSubstring_1("barfoothefoobarman", words1));
		System.out.println(new Solution_0030().findSubstring_2("wordgoodgoodgoodbestword", words2));
	}

}

class Solution_0030 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午4:20:55 
	 * @param: @param s
	 * @param: @param words
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 1-
	 *
	 */
	public List<Integer> findSubstring_1(String s, String[] words) {
		List<Integer> list = new ArrayList<Integer>();
		// 特殊情况处理
		if (s == null || words == null || words.length == 0) {
			return list;
		}
		int wordCount = words.length;
		int wordLength = words[0].length();
		if (wordLength == 0) {
			list.add(0);
			return list;
		}
		if (s.length() < wordCount * wordLength) {
			return list;
		}
		// 记录各单词出现的次数
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		for (String word : words) {
			wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
		}

		HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
		// 逐位扫描
		for (int i = 0; i < s.length() - wordCount * wordLength + 1; i++) {
			int p = i;
			boolean f = true;
			tempMap.clear();
			// 每次移动截取wordLength长度对比，共移动截取wordCount次，获得tempMap
			for (int j = 0; j < wordCount; j++) {
				String subStr = s.substring(p, p + wordLength);
				if (wordMap.containsKey(subStr)) {
					tempMap.put(subStr, tempMap.getOrDefault(subStr, 0) + 1);
					p += wordLength;
					// 一旦不存在直接结束
				} else {
					f = false;
					break;
				}
			}
			if (f) {
				// 若上次for都匹配到了，则继续对比k-v是否全一致相等
				for (int j = 0; j < wordCount; j++) {
					if (wordMap.get(words[j]) != tempMap.get(words[j])) {
						f = false;
					}
				}
				// 最终一致时记录开始时i的位置
				if (f) {
					list.add(i);
				}
			}
		}
		return list;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午5:05:53 
	 * @param: @param s
	 * @param: @param words
	 * @param: @return
	 * @return: List<Integer>
	 * @Description: 2-
	 *
	 */
	public List<Integer> findSubstring_2(String s, String[] words) {
		List<Integer> list = new ArrayList<Integer>();
		// 特殊情况处理
		if (s == null || words == null || words.length == 0) {
			return list;
		}
		int wordCount = words.length;
		int wordLen = words[0].length();
		int sLen = s.length();
		if (wordLen == 0) {
			list.add(0);
			return list;
		}
		if (sLen < wordCount * wordLen) {
			return list;
		}
		// 记录各单词出现的次数
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		for (String word : words) {
			wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
		}
		// 滑动窗口map
		HashMap<String, Integer> winowMap = new HashMap<String, Integer>();
		for (int i = 0; i < wordLen; i++) {
			int l, r;
			l = r = i;
			winowMap.clear();
			while (l <= sLen - wordCount * wordLen && r <= sLen - wordLen) {
				String rWord = s.substring(r, r + wordLen);
				// 若没有这个word，直接重置窗口和lr左右指针
				if (!wordMap.containsKey(rWord)) {
					winowMap.clear();
					r += wordLen;
					l = r;
					continue;
				}
				// 存在word时，记录并判断是否超量，若超量则从l向r依次清除已放入窗口的word，直至不超量
				winowMap.put(rWord, winowMap.getOrDefault(rWord, 0) + 1);
				while (winowMap.get(rWord) > wordMap.get(rWord)) {
					String lWord = s.substring(l, l + wordLen);
					int n = winowMap.get(lWord);
					if (n == 1) {
						winowMap.remove(lWord);
					} else {
						winowMap.put(lWord, n - 1);
					}
					l += wordLen;
				}
				r += wordLen;
				// 判定是否都已对应时
				if (r - l == wordCount * wordLen) {
					list.add(l);
				}
			}
		}
		return list;
	}
}
