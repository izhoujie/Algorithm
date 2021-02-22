package leetcode;

import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2021/2/20 下午4:39
 * @description: 697. 数组的度
 * <p>
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 * <p>
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 *  
 * <p>
 * 提示：
 * <p>
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0697 {
}

class Solution_0697 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/2/20 下午4:41
     * @param: nums
     * @description: 记录每个值的首末出现位置（即当前值的连续子数组）和出现次数（即当前值的度），并在遍历的时候记录最大度的连续子数组最小长度
     */
    public int findShortestSubArray(int[] nums) {
        // 统计每个值的子数组连续长度和度
        HashMap<Integer, Node_0697> map = new HashMap<>();
        // 度
        int max = Integer.MIN_VALUE;
        // 连续子数组的长度
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            Node_0697 node = map.get(val);
            if (node == null) {
                node = new Node_0697(i, i, val, 1);
                map.put(val, node);
            } else {
                node.count++;
                node.end = i;
            }
            int len = node.end - node.start + 1;
            // 若当前值的度：
            // 等于max：更新min
            // 大于max：更新min和max
            // 小于max：忽略
            if (node.count == max) {
                min = Math.min(min, len);
            } else if (node.count > max) {
                max = node.count;
                min = len;
            }
        }
        return min;
    }

    // 记录数组值的信息
    class Node_0697 {
        int start;
        int end;
        int value;
        // 当前值度
        int count;

        public Node_0697(int start, int end, int value, int count) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.count = count;
        }
    }
}