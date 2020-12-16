package leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author zhoujie
 * @date 2020/12/16 下午2:26
 * @description: 290. 单词规律
 * <p>
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0290 {
}

class Solution_0290 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/12/16 下午2:27
     * @param: pattern
     * @param: s
     * @description: 使用map和set，先记录字母与字符串的对应关系，并用set对字符是否已存在作校验
     */
    public boolean wordPattern_1(String pattern, String s) {
        if (pattern == null) {
            return s == null;
        }
        if (s == null) {
            return false;
        }
        char[] c = pattern.toCharArray();
        String[] str = s.split(" ");
        if (c.length != str.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            if (map.containsKey(c[i])) {
                if (!map.get(c[i]).equals(str[i])) {
                    return false;
                }
            } else if (set.contains(str[i])) {
                return false;
            } else {
                map.put(c[i], str[i]);
                set.add(str[i]);
            }
        }
        return true;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/12/16 下午4:54
     * @param: pattern
     * @param: s
     * @description: 与方法1类似，但是把map换为数组，26个小写字母对应到26长度的String数组
     */
    public boolean wordPattern_2(String pattern, String s) {
        if (pattern == null) {
            return s == null;
        }
        if (s == null) {
            return false;
        }
        char[] c = pattern.toCharArray();
        String[] str = s.split(" ");
        if (c.length != str.length) {
            return false;
        }
        String[] number = new String[26];
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            int index = c[i] - 'a';
            if (number[index] != null) {
                if (!number[index].equals(str[i])) {
                    return false;
                }
            } else if (set.contains(str[i])) {
                return false;
            } else {
                number[index] = str[i];
                set.add(str[i]);
            }
        }
        return true;
    }

}