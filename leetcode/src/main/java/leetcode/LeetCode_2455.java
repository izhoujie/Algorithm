package leetcode;

/**
 * @author zhoujie
 * @date 2023/5/29 16:15
 * @description: 2455. 可被三整除的偶数的平均值
 * <p>
 * <p>
 * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
 * <p>
 * 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,6,10,12,15]
 * 输出：9
 * 解释：6 和 12 是可以被 3 整除的偶数。(6 + 12) / 2 = 9 。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,4,7,10]
 * 输出：0
 * 解释：不存在满足题目要求的整数，所以返回 0 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class LeetCode_2455 {
    /**
     * @return int
     * @author zhoujie
     * @date 2023/5/29 16:16
     * @param: nums
     * @description:
     */
    public int averageValue(int[] nums) {
        int n = 0;
        int sum = 0;
        for (int v : nums) {
            if (v % 6 == 0) {
                sum += v;
                n++;
            }
        }
        return n > 0 ? sum / n : 0;
    }

}
