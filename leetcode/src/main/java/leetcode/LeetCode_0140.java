package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/11/3 15:00
 * @description: 140. 单词拆分 II
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0140 {

}

class Solution_0140 {
    /**
     * @return java.util.List<java.lang.String>
     * @author zhoujie
     * @date 2020/11/3 15:01
     * @param: s
     * @param: wordDict
     * @description: 先排除s中有wordDict中不存在的字符情况，然后哈希+dfs
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> list = new ArrayList<>();
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        // s中有wordDict中不存在的字符
        for (String s1 : wordDict) {
            for (char c : s1.toCharArray()) {
                set1.add(c);
            }
        }
        for (char c : s.toCharArray()) {
            set2.add(c);
        }
        set2.removeAll(set1);
        if (set2.size() > 0) {
            return list;
        }
        Set<String> wordSet = new HashSet<>();
        Set<Integer> lenSet = new HashSet<>();
        // 对wordDict进行哈希处理，同时也对长度哈希记录
        for (String s0 : wordDict) {
            lenSet.add(s0.length());
            wordSet.add(s0);
        }
        dfs("", s, list, lenSet, wordSet);
        return list;
    }

    private void dfs(String s1, String s, List<String> list, Set<Integer> lenSet, Set<String> wordSet) {
        if (s.length() == 0) {
            list.add(s1.substring(1));
        } else {
            // 哈希判断，先判断长度，避免太多无效的字符串截取，提升效率
            for (int i = 0; i < s.length(); i++) {
                if (lenSet.contains(i + 1)) {
                    String sub = s.substring(0, i + 1);
                    if (wordSet.contains(sub)) {
                        dfs(s1 + " " + sub, s.substring(i + 1), list, lenSet, wordSet);
                    }
                }
            }
        }
    }
}