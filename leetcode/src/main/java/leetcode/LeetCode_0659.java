package leetcode;

import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2020/12/4 下午10:14
 * @description: 659. 分割数组为连续子序列
 * <p>
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *  
 * <p>
 * 示例 3：
 * <p>
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *  
 * <p>
 * 提示：
 * <p>
 * 输入的数组长度范围为 [1, 10000]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0659 {
}

class Solution_0659 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2020/12/4 下午10:15
     * @param: nums
     * @description: 贪心算法，先统计每个数的数量，然后开始拼接至少3个连续子序列并记录以某个数结尾的数量，并继续拼接
     */
    public boolean isPossible(int[] nums) {
        // 统计各个数的数量
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 记录以某个数结尾的子序列的数量
        HashMap<Integer, Integer> endWith = new HashMap<>();
        for (int val : nums) {
            countMap.put(val, countMap.getOrDefault(val, 0) + 1);
        }
        for (int val : nums) {
            int count = countMap.getOrDefault(val, 0);
            // 若当前数的剩余数量大于1，则尝试拼接或新建子序列
            if (count > 0) {
                // 若以val-1结尾的子序列存在，就继续拼接，减少以val-1结尾的子序列的数量并新增以val结尾的子序列数量
                int preEnd = endWith.getOrDefault(val - 1, 0);
                if (preEnd > 0) {
                    countMap.put(val, count - 1);
                    endWith.put(val - 1, preEnd - 1);
                    endWith.put(val, endWith.getOrDefault(val, 0) + 1);
                    // 若不存在以val-1结尾的子序列则尝试新建子序列，若新建失败则直接返回，否则尝试拼接下一个数
                } else {
                    int next1Count = countMap.getOrDefault(val + 1, 0);
                    int next2Count = countMap.getOrDefault(val + 2, 0);
                    if (next1Count > 0 && next2Count > 0) {
                        countMap.put(val, count - 1);
                        countMap.put(val + 1, next1Count - 1);
                        countMap.put(val + 2, next2Count - 1);
                        endWith.put(val + 2, endWith.getOrDefault(val + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}