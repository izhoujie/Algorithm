package leetcode;

/**
 * @author zhoujie
 * @date 2023/3/9 00:46
 * @description: 1144. 递减元素使数组呈锯齿状
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * <p>
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * <p>
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 * <p>
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class LeetCode_1144 {
    /**
     * @return int
     * @author zhoujie
     * @date 2023/3/9 00:48
     * @param: nums
     * @description: 统计并比较达到两种平衡所需要的操作次数
     */
    public int movesToMakeZigzag_1(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        int count1 = 0;
        // 调整为奇数位锯齿小
        for (int i = 1; i < len; i += 2) {
            int t = 0;
            if (nums[i - 1] <= nums[i]) {
                t = nums[i] - nums[i - 1] + 1;
                count1 += t;
            }
            if (i + 1 < len && nums[i + 1] <= nums[i] - t) {
                count1 += (nums[i] - t) - nums[i + 1] + 1;
            }
        }
        int count2 = 0;
        // 调整为奇数位锯齿大
        int t = 0;
        for (int i = 1; i < len; i += 2) {
            if (nums[i - 1] - t >= nums[i]) {
                count2 += nums[i - 1] - t - nums[i] + 1;
            }
            if (i + 1 < len && nums[i + 1] >= nums[i]) {
                t = nums[i + 1] - nums[i] + 1;
                count2 += t;
            } else {
                t = 0;
            }
        }
        return Math.min(count1, count2);
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2023/3/10 22:34
     * @param: nums
     * @description: 优化：提取重复逻辑部分封装为方法
     */
    public int movesToMakeZigzag_2(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        return Math.min(helper(nums, 0), helper(nums, 1));
    }

    private int helper(int[] nums, int index) {
        int count = 0;
        for (int i = index; i < nums.length; i += 2) {
            int s = 0;
            if (i - 1 > -1) {
                s = Math.max(s, nums[i] - nums[i - 1] + 1);
            }
            if (i + 1 < nums.length) {
                s = Math.max(s, nums[i] - nums[i + 1] + 1);
            }
            count += s;
        }
        return count;
    }

}