package leetcode;

/**
 * @author zhoujie
 * @date 2020/11/25 下午1:38
 * @description: 1370. 上升下降字符串
 * <p>
 * <p>
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * <p>
 * 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * 重复步骤 2 ，直到你没法从 s 中选择字符。
 * 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * 重复步骤 5 ，直到你没法从 s 中选择字符。
 * 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * <p>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * 示例 2：
 * <p>
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * 示例 3：
 * <p>
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * 示例 4：
 * <p>
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * 示例 5：
 * <p>
 * 输入：s = "spo"
 * 输出："ops"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
public class LeetCode_1370 {
  
}

class Solution_1370 {
    /**
     * @return java.lang.String
     * @author zhoujie
     * @date 2020/11/25 下午1:38
     * @param: s
     * @description: 先桶计数，再按字母序列增→减、减→增反复横跳拼接
     */
    public String sortString(String s) {
        int[] letterArray = new int[26];
        int min = letterArray.length;
        int max = 0;
        int len = s.length();
        // 桶计数统计，且记录区间边界
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            min = Math.min(min, index);
            max = Math.max(max, index);
            letterArray[index]++;
        }
        StringBuilder sb = new StringBuilder(len);
        int c = min - 1;
        int t = 1;
        int count = 0;
        // 在有字母的区间内反复横跳
        while (count < len) {
            c += t;
            if (c < min) {
                c = min;
                t = 1;
            }
            if (c > max) {
                c = max;
                t = -1;
            }
            if (letterArray[c] != 0) {
                sb.append((char) (c + 'a'));
                letterArray[c]--;
                count++;
            }
        }
        return sb.toString();
    }
}