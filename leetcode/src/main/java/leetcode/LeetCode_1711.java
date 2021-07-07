package leetcode;

import java.util.HashMap;

/**
 * @author zhoujie
 * @date 2021/7/7 上午11:01
 * @description: 1711. 大餐计数
 * <p>
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * <p>
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * <p>
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * <p>
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * <p>
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= deliciousness.length <= 105
 * 0 <= deliciousness[i] <= 220
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-good-meals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1711 {
}

class Solution_1711 {
    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/7 上午11:02
     * @param: deliciousness
     * @description: 与两数之和的思路相似，使用map统计
     */
    public int countPairs_1(int[] deliciousness) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int mod = 1000000007;
        int count = 0;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(val, maxVal);
        }
        // 和的最大边界值
        maxVal *= 2;
        for (int val : deliciousness) {
            for (int sum = 1; sum <= maxVal; sum <<= 1) {
                // 累加差值对应的数量
                count = (count + map.getOrDefault(sum - val, 0)) % mod;
            }
            // 记录当前值的数量
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return count;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/7 下午2:36
     * @param: deliciousness
     * @description: 用int数组替换map统计
     */
    public int countPairs_2(int[] deliciousness) {
        int min = deliciousness[0];
        int max = deliciousness[0];
        int mod = 1000000007;
        int count = 0;
        for (int val : deliciousness) {
            min = Math.min(min, val);
            max = Math.max(max, val);
        }
        // 和的最大值边界
        int sumMax = max * 2;
        // deliciousness所有两数之间正差值压缩后的范围
        int[] intCount = new int[max - min + 1];
        for (int val : deliciousness) {
            for (int sum = 1; sum <= sumMax; sum <<= 1) {
                // 差值小于min或大于max时说明不存在该差值，小于时继续增大sum尝试，大于时直接跳出
                int v = sum - val;
                if (v < min) {
                    continue;
                }
                if (v > max) {
                    break;
                }
                // 累加该差值在数组中的数量
                count = (count + intCount[v - min]) % mod;
            }
            // 统计当前值的数量
            intCount[val - min]++;
        }
        return count;
    }

}