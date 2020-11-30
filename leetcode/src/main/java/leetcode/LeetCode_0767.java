package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/11/30 上午11:12
 * @description: 767. 重构字符串
 * <p>
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0767 {
}

class Solution_0767 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2020/11/30 上午11:13
     * @param: S
     * @description: 使用map统计，判断最大的字符数量符合条件后构造字符串，是从最大的字符开始构造的，效率较低
     */
    public String reorganizeString_1(String S) {
        if (S == null) {
            return "";
        }
        if (S.length() < 2) {
            return S;
        }
        int len = S.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparingInt((Map.Entry::getValue)));
        Collections.reverse(list);
        char[] chars = new char[len];
        if (list.get(0).getValue() > (len + 1) / 2) {
            return "";
        }
        int index = 0;
        int v = len - (len & 1 ^ 1);
        for (Map.Entry<Character, Integer> entry : list) {
            char key = entry.getKey();
            Integer value = entry.getValue();
            while (value-- > 0) {
                chars[2 * index >= len ? 2 * index - v : 2 * index] = key;
                index++;
            }
        }
        return new String(chars);
    }

    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2020/11/30 下午2:18
     * @param: S
     * @description: 构造字符只需错开无需从数量最大的字符开始构造，另可使用数组统计字符
     */
    public String reorganizeString_2(String S) {
        if (S == null) {
            return "";
        }
        if (S.length() < 2) {
            return S;
        }
        int len = S.length();
        int[] number = new int[26];
        int maxChar = 0;
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            int index = S.charAt(i) - 'a';
            if (++number[index] > maxChar) {
                maxChar = number[index];
                maxIndex = index;
                // 单个字符量最大不能超过总字符量的一半，总数奇数时向上取整
                if (maxChar > (len + 1) / 2) {
                    return "";
                }
            }
        }
        char[] chars = new char[len];
        int k = 0;
        // 索引越界时的修正量，字符总数为奇数时不用修正，为偶数时需要减1
        // 保证从0、2、4、6...填充完后修正时从1、3、5、7...开始填充
        int v = len - (len & 1 ^ 1);
        while (number[maxIndex]-- > 0) {
            chars[2 * k] = (char) (maxIndex + 'a');
            k++;
        }
        for (int i = 0; i < 26; i++) {
            while (number[i]-- > 0) {
                chars[2 * k >= len ? 2 * k - v : 2 * k] = (char) (i + 'a');
                k++;
            }
        }
        return new String(chars);
    }
}