package leetcode;

/**
 * @author zhoujie
 * @date 2020/12/17 下午1:59
 * @description: 714. 买卖股票的最佳时机含手续费
 * <p>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 * <p>
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0714 {
}

class Solution_0714 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/17 下午2:05
     * @param: prices
     * @param: fee
     * @description: 动态规划，简化变量
     */
    public int maxProfit_1(int[] prices, int fee) {
        int sell = 0;
        int buy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/18 下午2:32
     * @param: prices
     * @param: fee
     * @description: 动态规划，数组
     */
    public int maxProfit_2(int[] prices, int fee) {
        int n = prices.length;
        int[][] r = new int[2][n];
        // r[0][i] 表示第i天手中没有股票时的收益
        // r[1][i] 表示第i天手中有股票时的收益
        r[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            r[1][i] = Math.max(r[1][i - 1], r[0][i - 1] + prices[i] - fee);
            r[0][i] = Math.max(r[0][i - 1], r[1][i - 1] - prices[i]);
        }
        return r[0][n - 1];
    }


    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/18 下午2:24
     * @param: prices
     * @param: fee
     * @description: 贪心算法，将费用加在后买前的股票上，然后计算买卖利润
     */
    public int maxProfit_3(int[] prices, int fee) {
        int r = 0;
        // 购买前的总假，包括费用
        int buy = prices[0] + fee;
        for (int i = 1; i < prices.length; i++) {
            // 若后续有更低的股价，则更新购买费用
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
                // 若当前股价高于之前购买总价，则进行一次交易，获取利润，并且更新当前的股价为当前股价
                // 若明天的股价仍高于今天，则直接计算利润差，不再需要多付费用，否则继续if逻辑
            } else if (prices[i] > buy) {
                r += prices[i] - buy;
                buy = prices[i];
            }
        }
        return r;
    }
}