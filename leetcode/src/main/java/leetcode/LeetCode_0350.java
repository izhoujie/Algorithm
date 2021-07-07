package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2021/7/7 下午3:25
 * @description: 350. 两个数组的交集 II
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0350 {
}

class Solution_0350 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2021/7/7 下午3:25
     * @param: nums1
     * @param: nums2
     * @description: 先排序，再双指针处理
     */
    public int[] intersect_1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] rst = new int[Math.min(len1, len2)];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                rst[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return k > 0 ? Arrays.copyOf(rst, k) : new int[0];
    }

    /**
     * @return int[]
     * @author zhoujie
     * @date 2021/7/7 下午4:37
     * @param: nums1
     * @param: nums2
     * @description: 使用map记录并校验，适用于nums2体量很大存在磁盘上无法一次性全部读取到的情况
     */
    public int[] intersect_2(int[] nums1, int[] nums2) {
        // 保证nums1长度是较小的那个
        if (nums1.length > nums2.length) {
            return intersect_2(nums2, nums1);
        }
        int[] rst = new int[nums1.length];
        int k = 0;
        // map统计nums1
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : nums1) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        for (int val : nums2) {
            // 在map中校验nums2中的数据
            if (map.containsKey(val)) {
                rst[k++] = val;
                int count = map.get(val) - 1;
                if (count == 0) {
                    map.remove(val);
                } else {
                    map.put(val, count);
                }
            }
        }
        return k > 0 ? Arrays.copyOf(rst, k) : new int[0];
    }

}