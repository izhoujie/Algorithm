package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2020/10/27 10:04
 * @description: 1365. 有多少小于当前数字的数字
 * <p>
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1365 {
}

class Solution_1365 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/10/27 10:05
     * @param: nums
     * @description: 因数组值都在[0, 100]内，故可使用桶排序统计处理
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 统计桶
        int[] bucket = new int[101];
        for (int val : nums) {
            bucket[val]++;
        }
        int count = 0;
        int temp;
        //
        for (int i = 0; i < bucket.length; i++) {
            temp = bucket[i];
            bucket[i] = count;
            count += temp;
        }
        int[] rst = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rst[i] = bucket[nums[i]];
        }
        return rst;
    }
}