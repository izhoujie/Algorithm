package leetcode;

import java.util.HashSet;

/**
 * @author zhoujie
 * @date 2021/6/24 下午2:17
 * @description: 面试题 01.04. 回文排列
 * <p>
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * <p>
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * <p>
 * 回文串不一定是字典当中的单词。
 * <p>
 *  
 * <p>
 * 示例1：
 * <p>
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Satine_01_04 {


    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/6/24 下午2:17
     * @param: s
     * @description: 利用hashSet
     */
    public boolean canPermutePalindrome_1(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() < 2;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/6/24 下午2:22
     * @param: s
     * @description: 奇偶性统计
     */
    public boolean canPermutePalindrome_2(String s) {
        int[] ints = new int[128];
        int count = 0;
        for (char c : s.toCharArray()) {
            if ((ints[c]++ & 1) == 1) {
                count--;
            } else {
                count++;
            }
        }
        return count < 2;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/6/24 下午2:37
     * @param: s
     * @description: 用两个long统计各个bit位出现的奇偶性
     */
    public boolean canPermutePalindrome_3(String s) {
        long low = 0;
        long high = 0;
        for (char c : s.toCharArray()) {
            if (c < 64) {
                low ^= 1L << c;
            } else {
                high ^= 1L << (c - 64);
            }
        }
        return (Long.bitCount(low) + Long.bitCount(high)) < 2;
    }

}