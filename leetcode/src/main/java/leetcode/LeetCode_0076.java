package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年3月6日 下午9:41:21 
 * @Description: 76. 最小覆盖子串
 *
	给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
	
	示例：
	
	输入: S = "ADOBECODEBANC", T = "ABC"
	输出: "BANC"
	说明：
	
	如果 S 中不存这样的子串，则返回空字符串 ""。
	如果 S 中存在这样的子串，我们保证它是唯一的答案。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/minimum-window-substring
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-滑动窗口思想解决，具体使用一个最长为s-l的数组b保存二进制位数据，用一个map记录t中的字符，
	其中key为字符，value为顺次1左移位数，map的大小即最小长度l，先以l为窗口扫一次s得到初始b，
	之后l每次加1只需要再处理一次l+1位置即可；
 */
public class LeetCode_0076 {

}

class Solution_0076 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午9:47:19 
	 * @param: @param s
	 * @param: @param t
	 * @param: @return
	 * @return: String
	 * @Description: 1-！！！首先，这个算法解不了这个题，题目太模糊了，我理解的是aa和aa应该返回a，但是答案要求返回aa；
	 * 				这个算法写起来还是费了点脑子，留下了，记住不是本题的解：（以后看的时候提醒我自己）
	 * 				算法缺陷，既然用了int位保存数据，那么字符串t的去重后的长度不应该超过32，本题按照我理解的去重后的最多
	 * 				是26*2个，也是不够用的，考虑换64位的long类型应该就可以了；
	 * 				
	 * 				也就是说，使用位的方式解决了出现与不出现的问题，但是无法解决本题出现数量问题；
	 *
	 */
	public String minWindow_1(String s, String t) {
		if (t == null || t.length() == 0) {
			return "";
		}
		int sLen = 0;
		if (s == null || (sLen = s.length()) < t.length()) {
			return "";
		}
		HashMap<Character, Integer> has = new HashMap<Character, Integer>();
		char[] cs1 = t.toCharArray();
		// bingo就是用来判断的最终二进制值，k表示t中无重复出现的字符数量
		int bingo = 0, k = 0;
		for (char c : cs1) {
			if (!has.containsKey(c)) {
				bingo |= 1 << k;
				has.put(c, k);
				k++;
			}
		}
		// 最小起始长度
		int tLen = has.size();
		char[] cs = s.toCharArray();
		// 用来保存中间成果的缓存
		int[] cache = new int[sLen - tLen + 1];
		// 初始化一次长度为tLen时的check到缓存
		for (int i = 0; i < sLen - tLen + 1; i++) {
			// check就是后续用来与bingo验证的缓存值
			int check = 0;
			for (int j = i; j < i + tLen; j++) {
				if (has.containsKey(cs[j])) {
					check |= 1 << has.get(cs[j]);
				}
			}
			// 初始化也要贪心一下~
			if (check == bingo) {
				return s.substring(i, i + tLen);
			} else {
				cache[i] = check;
			}
		}
		// 开始循环扫描，每次增加tLen长度
		int p = tLen;
		while (p < sLen) {
			for (int i = p; i < sLen; i++) {
				if (has.containsKey(cs[i])) {
					// 缓存位置
					int index = i - tLen;
					// 加入新位置的值并校验，一致则return，否则更新该位置缓存
					int check = cache[index] | (1 << has.get(cs[i]));
					if (check == bingo) {
						return s.substring(index, i + 1);
					} else {
						cache[index] = check;
					}
				}
			}
			p++;
			tLen++;
		}
		return "";
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午11:46:51 
	 * @param: @param s
	 * @param: @param t
	 * @param: @return
	 * @return: String
	 * @Description: 2-按照题意要求，将1的改造一下，hash哈希表存对应字符出现次数；
	 * 				cache改为map类型，思路不变，但是改为校验cache的map与has是否完全一致即可；
	 * 				
	 * 				因为题目中的都是字母，所以不用HashMap改用128长度的数组足矣，
	 *
	 */
	public String minWindow_2(String s, String t) {
		int tLen = 0, sLen = 0;
		// 特例判断
		if (t == null || (tLen = t.length()) == 0) {
			return "";
		}
		// 特例判断
		if (s == null || (sLen = s.length()) < tLen) {
			return "";
		}
		// 需要找到的字母和数量，转为数组记录
		int[] need = new int[128];
		for (char c : t.toCharArray()) {
			need[c]++;
		}
		// 滑动窗口中的字母和数量
		int[] search = new int[128];
		// 滑动窗口左右指针和统计匹配到t中字母的计数count
		int left = 0, right = 0, count = 0;
		int[] min = new int[] { 0, sLen };
		while (right < sLen) {
			char c = s.charAt(right);
			// 记录s中当前字母数量，若是need中要找的，则count++且最多加need中的上限个
			search[c]++;
			if (need[c] > 0 && need[c] >= search[c]) {
				count++;
			}
			// 若s中的字母在t中全找到了，则开始从左侧缩小窗口
			while (count == tLen) {
				c = s.charAt(left);
				if (need[c] > 0 && need[c] >= search[c]) {
					count--;
				}
				// 若找到了更小的窗口则更新
				if (right - left < min[1] - min[0]) {
					min[0] = left;
					min[1] = right;
				}
				search[c]--;
				left++;
			}
			right++;
		}
		// 需要验证min[1]，未找到时返回""，否则截取s返回，记得截取右侧位置要+1
		return min[1] == sLen ? "" : s.substring(min[0], min[1] + 1);
	}
}
