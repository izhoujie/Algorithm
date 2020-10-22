package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2020/10/22 15:10
 * @description: 763. 划分字母区间
 * <p>
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 * <p>
 * 提示：
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0763 {

}

class Solution_0763 {
    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2020/10/22 15:12
     * @param: S
     * @description: 实际上是局部字符串的压缩内聚合，先定位压缩后记录每个字符的首次出现位置，然后计算各个内聚段的长度
     */
    public List<Integer> partitionLabels_1(String S) {
        List<Integer> list = new ArrayList<>();
        // 特例判断
        if (S == null || S.length() == 0) {
            return list;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] number = new int[26];
        Arrays.fill(number, -1);
        // 向左压缩重复字符并统计压缩内聚后最左侧字符首次出现的位置
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            int value = number[index];
            // 首次出现，记录位置并在队列中记录
            if (value == -1) {
                deque.addLast(i);
                number[index] = i;
            } else {
                // 若当前已出现过的字符位置大于队列尾部位置，进行出栈
                while (!deque.isEmpty() && deque.peekLast() > value) {
                    deque.pollLast();
                }
            }
        }
        // 此时，队列中的数字就是每个段的最左侧的起始索引，逐个计算长度即可
        if (deque.isEmpty()) {
            return list;
        }
        int t = deque.pollFirst();
        while (!deque.isEmpty()) {
            list.add(deque.peekFirst() - t);
            t = deque.pollFirst();
        }
        list.add(S.length() - t);
        return list;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2020/10/22 17:24
     * @param: S
     * @description: 定位每个字符最后出现的位置，然后顺序计算当前字符最后出现的位置是否就是当前的索引位置，是则说明可进行一次分段
     * 思路上课可理解为从左向右压缩
     */
    public List<Integer> partitionLabels_2(String S) {
        List<Integer> list = new ArrayList<>();
        // 特例判断
        if (S == null || S.length() == 0) {
            return list;
        }
        // 记录每个字符最后出现的位置；
        int[] number = new int[26];
        for (int i = 0; i < S.length(); i++) {
            number[S.charAt(i) - 'a'] = i;
        }
        int start = -1;
        int index = 0;
        for (int i = 0; i < S.length(); i++) {
            index = Math.max(number[S.charAt(i) - 'a'], index);
            if (index == i) {
                list.add(index - start);
                start = index;
            }
        }
        return list;
    }

    /**
     * @return java.util.List<java.lang.Integer>
     * @author zhoujie
     * @date 2020/10/22 17:38
     * @param: S
     * @description:
     */
    public List<Integer> partitionLabels_3(String S) {
        List<Integer> list = new ArrayList<>();
        // 特例判断
        if (S == null || S.length() == 0) {
            return list;
        }
        // 记录每个字符最后出现的位置；
        int[] number = new int[26];
        for (int i = 0; i < S.length(); i++) {
            number[S.charAt(i) - 'a'] = i;
        }
        int left = 0;
        int right;
        while (left < S.length()) {
            // 最小右边界
            right = number[S.charAt(left) - 'a'];
            // 校验边界内是否有字符的最后出现位置在右边界外，有则更新
            for (int i = left + 1; i < right; i++) {
                int index = number[S.charAt(i) - 'a'];
                if (index > right) {
                    right = index;
                }
            }
            // 找到一个可分割段，记录长度
            list.add(right - left + 1);
            left = right + 1;
        }
        return list;
    }
}