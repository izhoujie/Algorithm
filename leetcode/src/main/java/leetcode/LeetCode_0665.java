package leetcode;

/**
 * @author zhoujie
 * @date 2021/2/7 上午11:21
 * @description: 665. 非递减数列
 * <p>
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * <p>
 * 说明：
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0665 {
}

class Solution_0665 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/2/7 上午11:22
     * @param: nums
     * @description: 只改一次能符合要求的有以下几种（以下为当遍历到i时发现不符合）：
     * 1. 第一个数不符合的： 4，1，3，6，7 此时直接改nums[i-1]=nums[i];
     * 2. 第一个之后的数不符合，又分为两种：
     * 第一种：1，2，3，4，0，9，10  此时因为nums[i-2]>=nums[i]，所以需要改nums[i]=nums[i-1]
     * 第二种：2，3，19，10，19，21 此时因为nums[i-2]<=nums[i]，所以需要改nums[i-1]=nums[i]
     */
    public boolean checkPossibility(int[] nums) {
        boolean f = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (f) {
                    if (i > 1 && nums[i - 2] > nums[i]) {
                        nums[i] = nums[i - 1];
                    } else {
                        nums[i - 1] = nums[i];
                    }
                    f = false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}