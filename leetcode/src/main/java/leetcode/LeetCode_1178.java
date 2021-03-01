package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2021/2/26 下午3:38
 * @description: 1178. 猜字谜
 * <p>
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 * <p>
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1178 {
}

class Solution_1178 {
    /**
     * @return List<Integer>
     * @author zhoujie
     * @date 2021/2/26 下午3:39
     * @param: words
     * @param: puzzles
     * @description: 本题实际是集合中子集关系的处理
     * <p>
     * 谜面作为全集，谜底是子集，通过压缩a-z为对应二进制上的01转int类型，再计算集合的关系
     * 方法1的时间复杂度是O(N²)，逻辑没错，但是会超时...
     */
    public List<Integer> findNumOfValidWords_1(String[] words, String[] puzzles) {
        HashMap<Integer, Integer> wordsMap = new HashMap<>();
        // 对谜面和谜底的二进制压缩
        for (String word : words) {
            int val = 0;
            for (int i = 0; i < word.length(); i++) {
                val |= (1 << (word.charAt(i) - 'a'));
            }
            if (Integer.bitCount(val) < 8) {
                wordsMap.put(val, wordsMap.getOrDefault(val, 0) + 1);
            }
        }
        List<Integer> answer = new ArrayList<>();
        for (String puzzle : puzzles) {
            int val = 0;
            for (int j = 0; j < 7; j++) {
                val |= (1 << (puzzle.charAt(j) - 'a'));
            }
            int count = 0;
            // 首字母必须包含
            int first = 1 << (puzzle.charAt(0) - 'a');
            for (Map.Entry<Integer, Integer> next : wordsMap.entrySet()) {
                Integer key = next.getKey();
                // 经过两次&操作后若谜底int值未改变，则其为当前谜面的一个谜底（子集）
                if ((first & key) > 0 && (key & val) == key) {
                    count += next.getValue();
                }
            }
            answer.add(count);
        }
        return answer;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2021/3/1 上午9:54
     * @param: words
     * @param: puzzles
     * @description: 对方法1中后半部分处理的改进，在全集的子集中枚举谜底，比暴力全遍历要快，不会超时
     * <p>
     * 时间复杂度：O(m*|w| + n * 2^(7-1)) m,n分别为数组words和puzzles的长度，7位puzzles的元素长度
     * 计算部分：
     * 1：二进制压缩：谜底为O(m*|w|)，谜面为O(n*7)；
     * 2：子集计算：n*2^7；
     * 总和为O(m*|w| + n*7 + n*2^(7-1)) 优化后为O(m*|w| + n * 2^(7-1))
     */
    public List<Integer> findNumOfValidWords_2(String[] words, String[] puzzles) {
        HashMap<Integer, Integer> wordsMap = new HashMap<>();
        // 对谜面和谜底的二进制压缩
        for (String word : words) {
            int val = 0;
            for (int i = 0; i < word.length(); i++) {
                val |= (1 << (word.charAt(i) - 'a'));
            }
            if (Integer.bitCount(val) < 8) {
                wordsMap.put(val, wordsMap.getOrDefault(val, 0) + 1);
            }
        }
        List<Integer> answerList = new ArrayList<>();
        for (String puzzle : puzzles) {
            int mask = 0;
            // 二进制压缩，先处理后6位的字母
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }
            int subSet = mask;
            int count = 0;
            do {
                // 首字母的包含处理
                int answer = subSet | (1 << puzzle.charAt(0) - 'a');
                if (wordsMap.containsKey(answer)) {
                    count += wordsMap.get(answer);
                }
                // 子集的遍历，当遍历完最高位的1时，此处的操作会使得此时的subSet复位到mask
                // 例： subSet = mask = 00100110
                // 当subSet = 00000000时：
                // 00000000 - 1 = 11111111
                // 11111111 & mask = mask  此时subSet复位到mask，遍历完毕
                subSet = (subSet - 1) & mask;
            } while (subSet != mask);
            answerList.add(count);
        }
        return answerList;
    }

}