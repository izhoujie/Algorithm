package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujie
 * @date 2021/3/9 下午2:06
 * @description: 131. 分割回文串
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0131 {
}

class Solution_0131 {
    /**
     * @return List<List < String>>
     * @author zhoujie
     * @date 2021/3/9 下午2:11
     * @param: s
     * @description: 全排列，本题参数最长只有16，字符超长时考虑使用map保存子串是否为回文的结果
     */
    public List<List<String>> partition(String s) {
        List<List<String>> all = new ArrayList<>();
        strSplit(s.toCharArray(), 0, new ArrayList<>(), all);
        return all;
    }

    private void strSplit(char[] cs, int index, List<String> sub, List<List<String>> all) {
        if (index == cs.length) {
            all.add(new ArrayList<>(sub));
        }
        for (int i = index; i < cs.length; i++) {
            // 子串回文检测
            if (checkStr(cs, index, i)) {
                sub.add(new String(cs, index, i - index + 1));
                strSplit(cs, i + 1, sub, all);
                // 回溯全排列
                sub.remove(sub.size() - 1);
            }
        }
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/3/9 下午5:23
     * @param: cs
     * @param: l
     * @param: r
     * @description: 回文判断
     */
    private boolean checkStr(char[] cs, int l, int r) {
        if (l == r) {
            return true;
        }
        while (l <= r) {
            if (cs[l] != cs[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}