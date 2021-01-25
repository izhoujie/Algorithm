package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2021/1/20 下午5:00
 * @description: 628. 三个数的最大乘积
 * <p>
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * <p>
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0628 {
}

class Solution_0628 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/1/20 下午5:01
     * @param: nums
     * @description: 排序计算
     */
    public int maximumProduct_1(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3],
                nums[nums.length - 1] * nums[0] * nums[1]);
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2021/1/25 上午9:12
     * @param: nums
     * @description: 一次遍历，记录最大的3个值和最小的两个值在计算比较
     */
    public int maximumProduct_2(int[] nums) {
        // 因为数据范围为[1000,-1000]，所以最值取1001和-1001即可，不能也没必要用Integer最值，会溢出
        int max1 = -1001;
        int max2 = -1001;
        int max3 = -1001;
        int min1 = 1001;
        int min2 = 1001;
        for (int val : nums) {
            if (val > max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val > max2) {
                max3 = max2;
                max2 = val;
            } else if (val > max3) {
                max3 = val;
            }
            if (val < min1) {
                min2 = min1;
                min1 = val;
            } else if (val < min2) {
                min2 = val;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }

}