package leetcode;

import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2021/7/8 下午1:58
 * @description: 930. 和相同的二元子数组
 * <p>
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * <p>
 * 子数组 是数组的一段连续部分。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0930 {
}

class Solution_0930 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/8 下午1:58
     * @param: nums
     * @param: goal
     * @description: 双指针，右指针控制右边界，左侧两个指针共同控制等于goal的区间范围
     */
    public int numSubarraysWithSum_1(int[] nums, int goal) {
        int right = 0;
        int left1 = 0;
        int left2 = 0;
        int sum1 = 0;
        int sum2 = 0;
        int count = 0;
        while (right < nums.length) {
            sum1 += nums[right];
            // 左侧等于goal的最左边界
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1++];
            }
            sum2 += nums[right];
            // 左侧等于goal的最右边界
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2++];
            }
            count += left2 - left1;
            right++;
        }
        return count;
    }


    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/8 下午3:30
     * @param: nums
     * @param: goal
     * @description: 前缀和：hashmap
     */
    public int numSubarraysWithSum_2(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int val : nums) {
            sum += val;
            // 已统计的前缀和中是否有差值部分存在，存在则累加
            if (map.containsKey(sum - goal)) {
                count += map.get(sum - goal);
            }
            // 统计当前和的前缀和
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}