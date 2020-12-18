package leetcode;

/**
 * @author zhoujie
 * @date 2020/12/18 上午9:17
 * @description: 389. 找不同
 * <p>
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 * <p>
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0389 {
}

class Solution_0389 {
    /**
     * @return char
     * @author zhoujie
     * @date 2020/12/18 上午9:18
     * @param: s
     * @param: t
     * @description: 字母转为数组统计
     */
    public char findTheDifference_1(String s, String t) {
        if (s.length() == 0) {
            return t.charAt(0);
        }
        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']--;
            c[t.charAt(i) - 'a']++;
        }
        c[t.charAt(t.length() - 1) - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (c[i] > 0) {
                return (char) (i + 'a');
            }
        }
        return t.charAt(0);
    }

    /**
     * @return char
     * @author zhoujie
     * @date 2020/12/18 上午9:31
     * @param: s
     * @param: t
     * @description: 因为只有一个字符多余，故可以利用异或的特性
     */
    public char findTheDifference_2(String s, String t) {
        if (s.length() == 0) {
            return t.charAt(0);
        }
        char c = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }

}