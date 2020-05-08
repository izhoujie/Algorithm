package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月6日 下午10:48:51 
 * @Description: 983. 最低票价
 *
	在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
	
	火车票有三种不同的销售方式：
	
	一张为期一天的通行证售价为 costs[0] 美元；
	一张为期七天的通行证售价为 costs[1] 美元；
	一张为期三十天的通行证售价为 costs[2] 美元。
	通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
	
	返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
	
	 
	
	示例 1：
	
	输入：days = [1,4,6,7,8,20], costs = [2,7,15]
	输出：11
	解释： 
	例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
	在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
	在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
	在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
	你总共花了 $11，并完成了你计划的每一天旅行。
	示例 2：
	
	输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
	输出：17
	解释：
	例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划： 
	在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
	在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。 
	你总共花了 $17，并完成了你计划的每一天旅行。
	 
	
	提示：
	
	1 <= days.length <= 365
	1 <= days[i] <= 365
	days 按顺序严格递增
	costs.length == 3
	1 <= costs[i] <= 1000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0983 {

}

class Solution_0983 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月6日 下午11:53:49 
	 * @param: @param days
	 * @param: @param costs
	 * @param: @return
	 * @return: int
	 * @Description: 1-动态规划；
	 *
	 */
	public int mincostTickets(int[] days, int[] costs) {
		// 最后一天旅行的序号
		int len = days[days.length - 1];
		// 动态规划dp需要len+1长度，包含最后一天
		int[] allCost = new int[len + 1];
		// 标记哪些天旅行了
		for (int i : days) {
			allCost[i] = -1;
		}
		int c1, c2, c3;
		for (int i = 1; i < len + 1; i++) {
			// 若当天未旅行，则花费等价于前一天
			if (allCost[i] == 0) {
				allCost[i] = allCost[i - 1];
			} else {
				// 买当天票的花费
				c1 = allCost[i - 1] + costs[0];
				// 买周票的花费：分为7天内买周票和7天外买周票
				if (i >= 7) {
					c2 = allCost[i - 7] + costs[1];
				} else {
					c2 = costs[1];
				}
				// 买月票的花费：分为30天内买月票和30天外买月票
				if (i >= 30) {
					c3 = allCost[i - 30] + costs[2];
				} else {
					c3 = costs[2];
				}
				// 截至第i天旅行的最小花费为c1、c2、c3的最小值
				allCost[i] = Math.min(Math.min(c1, c2), c3);
			}
		}
		// 最后一天即旅行总花费
		return allCost[len];
	}
}
