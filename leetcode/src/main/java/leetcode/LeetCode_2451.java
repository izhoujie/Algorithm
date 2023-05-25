package leetcode;

/**
 * @author zhoujie
 * @date 2023/5/25 15:58
 * @description: 2451. 差值数组不同的字符串
 * <p>
 * 给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
 * <p>
 * 每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
 * <p>
 * 比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
 * words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
 * <p>
 * 请你返回 words中 差值整数数组 不同的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["adc","wzy","abc"]
 * 输出："abc"
 * 解释：
 * - "adc" 的差值整数数组是 [3 - 0, 2 - 3] = [3, -1] 。
 * - "wzy" 的差值整数数组是 [25 - 22, 24 - 25]= [3, -1] 。
 * - "abc" 的差值整数数组是 [1 - 0, 2 - 1] = [1, 1] 。
 * 不同的数组是 [1, 1]，所以返回对应的字符串，"abc"。
 * 示例 2：
 * <p>
 * 输入：words = ["aaa","bob","ccc","ddd"]
 * 输出："bob"
 * 解释：除了 "bob" 的差值整数数组是 [13, -13] 以外，其他字符串的差值整数数组都是 [0, 0] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= words.length <= 100
 * n == words[i].length
 * 2 <= n <= 20
 * words[i] 只含有小写英文字母。
 */
public class LeetCode_2451 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2023/5/25 15:58
     * @param: words
     * @description: 对比校验
     */
    public String oddString(String[] words) {
        boolean f = compareStr(words[0], words[1]);
        if (!f) {
            return compareStr(words[1], words[2]) ? words[0] : words[1];
        }
        for (int i = 2; i < words.length; i++) {
            if (!compareStr(words[i], words[0])) {
                return words[i];
            }
        }
        return "";
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2023/5/25 16:08
     * @param: s1
     * @param: s2
     * @description: 若是差值相等字符串，则对应位置的差值都相等
     */
    private boolean compareStr(String s1, String s2) {
        int len = s1.length();
        int t = s1.charAt(0) - s2.charAt(0);
        for (int i = 1; i < len; i++) {
            if (s1.charAt(i) - s2.charAt(i) != t) {
                return false;
            }
        }
        return true;
    }
}
