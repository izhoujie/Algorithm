package leetcode;

import java.util.HashSet;

/**
 * @author zhoujie
 * @date 2021/7/5 下午4:05
 * @description: 217. 存在重复元素
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0217 {
}

class Solution_0217 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/7/5 下午4:06
     * @param: nums
     * @description: 排序检测
     */
    public boolean containsDuplicate_1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j > -1; j--) {
                // 一旦nums[i] > nums[j]，说明i之前不存在大于等于nums[i]的值，反之则尝试搜索
                if (nums[i] > nums[j]) {
                    break;
                }
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/7/5 下午4:13
     * @param: nums
     * @description: HashSet
     */
    public boolean containsDuplicate_2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : nums) {
            if (!set.add(val)) {
                return true;
            }
        }
        return false;
    }

}