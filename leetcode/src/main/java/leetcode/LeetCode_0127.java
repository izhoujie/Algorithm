package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/11/5 10:04
 * @description: 127. 单词接龙
 * <p>
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * <p>
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出: 5
 * <p>
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * 返回它的长度 5。
 * 示例 2:
 * <p>
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * 输出: 0
 * <p>
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0127 {
}

class Solution_0127 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/5 10:10
     * @param: beginWord
     * @param: endWord
     * @param: wordList
     * @description:
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Deque<String> deque = new LinkedList<>();
        boolean[] f = new boolean[wordList.size()];
        int count = 1;
        deque.addFirst(beginWord);
        while (!deque.isEmpty()) {
            count++;
            int size = deque.size();
            while (size-- > 0) {
                String before = deque.pollLast();
                for (int i = 0; i < wordList.size(); i++) {
                    if (f[i]) {
                        continue;
                    }
                    String next = wordList.get(i);
                    if (canChange(before, next)) {
                        if (next.equals(endWord)) {
                            return count;
                        } else {
                            deque.addFirst(next);
                            f[i] = true;
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean canChange(String before, String next) {
        boolean f = true;
        for (int i = 0; i < before.length(); i++) {
            if (before.charAt(i) != next.charAt(i)) {
                if (f) {
                    f = false;
                } else {
                    return false;
                }
            }
        }
        return !f;
    }

}