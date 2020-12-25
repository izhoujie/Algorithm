package leetcode;

import java.util.HashSet;

/**
 * @author zhoujie
 * @date 2020/12/23 下午2:15
 * @description: 387. 字符串中的第一个唯一字符
 * <p>
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *  
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0326 {
}

class Solution_0326 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/23 下午2:15
     * @param: s
     * @description: 两个set，两次遍历
     */
    public int firstUniqChar_1(String s) {
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2 = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 只出现一次的只在set1中，多次出现的两个set中都有
            if (set1.contains(c)) {
                set2.add(c);
            } else {
                set1.add(c);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set2.contains(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/25 上午9:11
     * @param: s
     * @description: 直接检测每一个26个字母的出现次数
     */
    public int firstUniqChar_2(String s) {
        int first, last, index = s.length();
        for (char c = 'a'; c <= 'z'; c++) {
            first = s.indexOf(c);
            last = s.lastIndexOf(c);
            if (first == last && first != -1) {
                index = Math.min(index, first);
            }
        }
        return index == s.length() ? -1 : index;
    }

}