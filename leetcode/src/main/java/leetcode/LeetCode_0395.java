package leetcode;

/**
 * @author zhoujie
 * @date 2021/3/1 下午2:01
 * @description: 395. 至少有 K 个重复字符的最长子串
 * <p>
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0395 {
}

class Solution_0395 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/3/1 下午2:03
     * @param: s
     * @param: k
     * @description: 分值思想，找到总数少于k个字母从此处分割，递归处理
     * 方法一的缺陷：找到一处就分割递归，后续对该字母的寻找计算都是冗余重复的，效率太低
     */
    public int longestSubstring_1(String s, int k) {
        return findLongest_1(s.toCharArray(), 0, s.length() - 1, k);
    }

    private int findLongest_1(char[] cs, int l, int r, int k) {
        if (r - l + 1 < k) {
            return 0;
        }
        int[] number = new int[26];
        int i = l;
        while (i <= r) {
            number[cs[i++] - 'a']++;
        }
        i = l;
        while (i <= r) {
            int count = number[cs[i++] - 'a'];
            // 找到第一个出现次数少于k的字母，分割递归
            if (count > 0 && count < k) {
                return Math.max(findLongest_1(cs, l, i - 2, k), findLongest_1(cs, i, r, k));
            }
        }
        return r - l + 1;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2021/3/1 下午3:36
     * @param: s
     * @param: k
     * @description: 方法二是对方法一的优化，每找到一个数量低于k个的字母就在当前流程内以所有该字母分割当前子串后递归
     */
    public int longestSubstring_2(String s, int k) {
        return findLongest_2(s.toCharArray(), 0, s.length() - 1, k);
    }

    private int findLongest_2(char[] cs, int l, int r, int k) {
        if (r - l + 1 < k) {
            return 0;
        }
        int[] number = new int[26];
        int i = l;
        while (i <= r) {
            number[cs[i++] - 'a']++;
        }
        char h = 0;
        // 优化点：先找到第一个出现数量少于k个的字母
        for (int j = 0; j < 26; j++) {
            if (number[j] > 0 && number[j] < k) {
                h = (char) (j + 'a');
                break;
            }
        }
        if (h == 0) {
            return r - l + 1;
        }
        i = l;
        int max = 0;
        // 以该字母为分隔符，切分当前子串并递归
        while (i <= r) {
            while (i <= r && cs[i] == h) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;
            while (i <= r && cs[i] != h) {
                i++;
            }
            // 切分后子串的递归
            max = Math.max(max, findLongest_2(cs, start, i - 1, k));
        }
        return max;
    }

}