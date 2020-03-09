package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月8日 下午3:35:30 
 * @Description: 322. 零钱兑换
 *
	给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
	
	示例 1:
	
	输入: coins = [1, 2, 5], amount = 11
	输出: 3 
	解释: 11 = 5 + 5 + 1
	示例 2:
	
	输入: coins = [2], amount = 3
	输出: -1
	说明:
	你可以认为每种硬币的数量是无限的。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/coin-change
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先对硬币排序，再贪心并剪枝优化；
 */
public class LeetCode_0322 {

}

class Solution_0323 {
	private int minCount = Integer.MAX_VALUE;

	public int coinChange(int[] coins, int amount) {
		int len = 0;
		if (coins == null || (len = coins.length) == 0) {
			return -1;
		}
		// 先对硬币排序
		Arrays.sort(coins);
		countCoins(coins, amount, 0, len - 1);
		return minCount == Integer.MAX_VALUE ? -1 : minCount;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月9日 下午9:22:34 
	 * @param: @param coins
	 * @param: @param amount
	 * @param: @param count
	 * @param: @param index
	 * @return: void
	 * @Description: 1-从最大的往前试，
	 *
	 */
	private void countCoins(int[] coins, int amount, int count, int index) {
		// 若最小硬币也凑不够或硬币总数大于int最大值，则忽略
		if (index < 0 || count + amount / coins[index] >= minCount) {
			return;
			// 若当前硬币数刚好凑够，则比较保留最小值
		} else if (amount % coins[index] == 0) {
			minCount = Math.min(minCount, count + amount / coins[index]);
			return;
		} else {
			// 若当前硬币值凑不了整，则依次不断减小最大可取的数，然后再递归往次小的硬币值上尝试
			for (int i = amount / coins[index]; i > -1; i--) {
				countCoins(coins, amount - i * coins[index], count + i, index - 1);
			}
		}
	}
}
