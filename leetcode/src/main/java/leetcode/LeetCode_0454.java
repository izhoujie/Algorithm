package leetcode;

import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2020/11/27 下午2:23
 * @description: 454. 四数相加 II
 * <p>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0454 {
}

class Solution_0454 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/27 下午2:24
     * @param: A
     * @param: B
     * @param: C
     * @param: D
     * @description: 二分思想，先统计一半的组合的map，再统计另一半的组合map，因为另一半可通过已有一半的map计算，可省略一个map
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // 因长度已知，这里可以直接初始化长度，因为增长因子是0.75，不乘2那么必有一次扩容，所以直接乘以2，让扩容次数归零
        HashMap<Integer, Integer> map = new HashMap<>(2 * A.length * B.length);
        // 统计A和B的组合，key为组合元素的值，value为对应值的个数
        for (int valA : A) {
            for (int valB : B) {
                map.put(valA + valB, map.getOrDefault(valA + valB, 0) + 1);
            }
        }
        int count = 0;
        // 计算与上述key和为0，即-key的数量，并累加key对应的value值，即得四数和为0的所有组合数
        for (int valC : C) {
            for (int valD : D) {
                if (map.containsKey(-valC - valD)) {
                    count += map.get(-valC - valD);
                }
            }
        }
        return count;
    }
}