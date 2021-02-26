package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2021/2/25 下午3:48
 * @description: 1438. 绝对差不超过限制的最长连续子数组
 * <p>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * <p>
 * 如果不存在满足条件的子数组，则返回 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 * <p>
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 * <p>
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1438 {
}

class Solution_1438 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/2/25 下午3:49
     * @param: nums
     * @param: limit
     * @description: 维护最值单调队列，校验最值差值，调整队列并计算长度
     */
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> max = new LinkedList<>();
        Deque<Integer> min = new LinkedList<>();
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < nums.length) {
            // 最大值单调队列
            while (!max.isEmpty() && max.peekFirst() < nums[right]) {
                max.pollFirst();
            }
            max.offerFirst(nums[right]);
            // 最小值单调队列
            while (!min.isEmpty() && min.peekFirst() > nums[right]) {
                min.pollFirst();
            }
            min.offerFirst(nums[right]);
            right++;
            // 最值差，从数组左侧校验超范围的值并弹出
            while (!max.isEmpty() && !min.isEmpty() && (max.peekLast() - min.peekLast()) > limit) {
                if (nums[left] == min.peekLast()) {
                    min.pollLast();
                }
                if (nums[left] == max.peekLast()) {
                    max.pollLast();
                }
                left++;
            }
            // 计算此时窗口长度
            len = Math.max(len, right - left);
        }
        return len;
    }
}