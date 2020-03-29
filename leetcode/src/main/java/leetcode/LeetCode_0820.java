package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月28日 下午9:53:38 
 * @Description: 820. 单词的压缩编码
 *
	给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
	
	例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
	
	对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
	
	那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
	
	示例：
	
	输入: words = ["time", "me", "bell"]
	输出: 10
	说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
	 
	
	提示：
	
	1 <= words.length <= 2000
	1 <= words[i].length <= 7
	每个单词都是小写字母 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/short-encoding-of-words
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0820 {

}

class Solution_0820 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月28日 下午9:54:12 
	 * @param: @param words
	 * @param: @return
	 * @return: int
	 * @Description: 1-按长度降序排序，然后拼接，使用indexof()
	 *
	 */
	public int minimumLengthEncoding_1(String[] words) {
		// 按长度降序排序
		Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
		StringBuilder sb = new StringBuilder();
		for (String s : words) {
			if (sb.indexOf(s + "#") == -1) {
				sb.append(s).append("#");
			}
		}
		return sb.length();
	}

	/**
	 * @author ZhouJie
	 * @date 2020年3月29日 上午1:33:22 
	 * @Description: 辅助索引节点
	 *
	 */
	class TailNode {
		TailNode[] next = new TailNode[26];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月29日 上午1:33:24 
	 * @param: @param words
	 * @param: @return
	 * @return: int
	 * @Description: 2-Tail，建立字典索引
	 *
	 */
	public int minimumLengthEncoding_2(String[] words) {
		// 按长度降序排序
		Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
		int minLen = 0;
		// 根索引
		TailNode root = new TailNode();
		for (String s : words) {
			// 每次都从根开始搜索
			TailNode currNode = root;
			// 当前单词是否需要单独新建索引
			boolean f = false;
			for (int i = s.length() - 1; i > -1; i--) {
				int index = s.charAt(i) - 'a';
				// 当前字符是否已建立索引，若未建立索引则新建索引并更新布尔值
				if (currNode.next[index] == null) {
					f = true;
					currNode.next[index] = new TailNode();
				}
				// 搜索下一个索引
				currNode = currNode.next[index];
			}
			if (f) {
				// 记录新建索引增加的长度
				minLen += s.length() + 1;
			}
		}
		return minLen;
	}

}