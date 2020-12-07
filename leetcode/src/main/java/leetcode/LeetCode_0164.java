package leetcode;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author zhoujie
 * @date 2020/11/26 下午2:57
 * @description: 164. 最大间距
 * <p>
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * <p>
 * 如果数组元素个数小于 2，则返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 * <p>
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 * <p>
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0164 {
}

class Solution_0164 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/26 下午2:58
     * @param: nums
     * @description: 使用bitset统计数据然后顺序遍历求最大间距，效率太低，超时
     */
    public int maximumGap1(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int size = 0;
        // 确定bitset大小
        for (int val : nums) {
            size = Math.max(size, val);
        }
        BitSet bitSet = new BitSet(size + 1);
        // 统计
        for (int val : nums) {
            bitSet.set(val);
        }
        int maxDistance = 0;
        int index = bitSet.nextSetBit(0);
        int nextIndex;
        // 计算最大间距
        while ((nextIndex = bitSet.nextSetBit(index + 1)) != -1) {
            maxDistance = Math.max(maxDistance, nextIndex - index);
            index = nextIndex;
        }
        return maxDistance;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/26 下午5:05
     * @param: nums
     * @description: 先快排，再计算最大间距，不符合题目要求线性时间复杂度的要求
     */
    public int maximumGap2(int[] nums) {
        Arrays.sort(nums);
        int maxDistance = 0;
        for (int i = 1; i < nums.length; i++) {
            maxDistance = Math.max(nums[i] - nums[i - 1], maxDistance);
        }
        return maxDistance;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/7 上午11:37
     * @param: nums
     * @description: 先基数排序，再计算差值，
     * 时间复杂度：O(d*(n+r))，d为最大数的长度，n为数组长度，r为基数，一般r=10；
     * 空间复杂度：O(n+r)，需要一个额外的数组保存中间结果，需要一个额外的r长度计算保存中间基数，一般r=10；
     */
    public int maximumGap3(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return 0;
        }
        // 确定数组最大值
        int max = Arrays.stream(nums).max().getAsInt();
        // 基数为10的递增权值
        long dc = 1;
        // 中间结果数组
        int[] temp = new int[len];
        // 基数数组
        int[] d = new int[10];
        while (max >= dc) {
            // 重置
            Arrays.fill(d, 0);
            // 计算每个基数的数量
            for (int val : nums) d[val / (int) dc % 10]++;
            // 通过累加的方式排序，必须从低往高处累加，对应i中的值就是后面计算时的排序位置
            for (int i = 1; i < 10; i++) {
                d[i] += d[i - 1];
            }
            // 必须从后往前排，保证较大的数拿到较大的索引位置
            for (int i = len - 1; i > -1; i--) {
                // 排序，d中总数为len，当前val计算得到的d[index]-1就是本次基数排序后val在原数组中的位置
                int index = nums[i] / (int) dc % 10;
                temp[d[index] - 1] = nums[i];
                // 已排序一个则对应d[index]减少一个
                d[index]--;
            }
            // 复制本次结果到原数组
            System.arraycopy(temp, 0, nums, 0, len);
            dc *= 10;
        }
        int maxStep = 0;
        // 计算最大间距
        for (int i = 1; i < len; i++) {
            maxStep = Math.max(nums[i] - nums[i - 1], maxStep);
        }
        return maxStep;
    }

}