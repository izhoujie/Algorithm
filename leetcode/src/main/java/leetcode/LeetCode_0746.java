package leetcode;

/**
 * @author zhoujie
 * @date 2020/12/21 上午9:16
 * @description: 746. 使用最小花费爬楼梯
 * <p>
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1:
 * <p>
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *  示例 2:
 * <p>
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0746 {
}

class Solution_0746 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/21 上午9:16
     * @param: cost
     * @description: 贪心/动态规划，从台阶2开始算起；
     */
    public int minCostClimbingStairs_1(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/21 上午10:11
     * @param: cost
     * @description: 贪心/动态规划，使用额外变量；
     */
    public int minCostClimbingStairs_2(int[] cost) {
        int t0 = 0;
        int t1 = 0;
        int t2;
        for (int i = 2; i <= cost.length; i++) {
            t2 = Math.min(t0 + cost[i - 2], t1 + cost[i - 1]);
            t0 = t1;
            t1 = t2;
        }
        return t1;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/21 上午10:59
     * @param: cost
     * @description: 贪心/动态规划，使用dp数组；
     */
    public int minCostClimbingStairs_3(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] += Math.min(cost[i - 2] + dp[i - 2], cost[i - 1] + dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

}