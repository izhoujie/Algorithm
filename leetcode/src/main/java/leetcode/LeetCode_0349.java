package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zhoujie
 * @date 2020/11/2 17:11
 * @description: 349. 两个数组的交集
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0349 {
}

class Solution_0349 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/11/2 17:12
     * @param: nums1
     * @param: nums2
     * @description: 使用set集合特性去重
     */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        //特例判断
        if (nums1 == null || nums2 == null) {
            return null;
        }
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int val : nums1) {
            set1.add(val);
        }
        for (int val : nums2) {
            if (set1.contains(val)) {
                set2.add(val);
            }
        }
        // list转数组
        int[] rst = new int[set2.size()];
        int i = 0;
        for (Integer val : set2) {
            rst[i++] = val;
        }
        return rst;
    }

    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/11/2 17:28
     * @param: nums1
     * @param: nums2
     * @description: 先排序，再比较排序合并
     */
    public int[] intersection_2(int[] nums1, int[] nums2) {
        // 先都排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] all = new int[len1 + len2];
        int i = 0;
        int j = 0;
        int k = 0;
        // 比较排序并合并
        while (i < len1 && j < len2) {
            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 == num2) {
                // 首个元素或者当前值不与合并后的前一个值相等则记录
                if (k == 0 || num1 != all[k - 1]) {
                    all[k++] = num1;
                }
                i++;
                j++;
            } else if (num1 < num2) {
                i++;
            } else {
                j++;
            }
        }
        // 复制出交集的k个元素返回
        return Arrays.copyOfRange(all, 0, k);
    }
}