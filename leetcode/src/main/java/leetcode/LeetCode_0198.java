package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月24日 下午8:47:43 
 * @Description: 198. 打家劫舍
 *
	你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
	
	给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
	
	示例 1:
	
	输入: [1,2,3,1]
	输出: 4
	解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
	     偷窃到的最高金额 = 1 + 3 = 4 。
	示例 2:
	
	输入: [2,7,9,3,1]
	输出: 12
	解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
	     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/house-robber
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-动态规划；
		2-省略dp数组动态规划；
 */
public class LeetCode_0198 {

}

class Solution_0198 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月24日 下午8:50:48 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-dp动态规划；
	 *
	 */

	public int rob_1(int[] nums) {
		int len = 0;
		if (nums == null || (len = nums.length) == 0) {
			return 0;
		}
		int[] dp = new int[len];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < len; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
		}
		return dp[len - 1];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月24日 下午8:51:10 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-动态规划，无需dp数组；
	 * 
	 */
	public int rob_2(int[] nums) {
		int max = 0, curr = 0;
		if (nums == null) {
			return max;
		}
		for (int i : nums) {
			int temp = Math.max(max, curr + i);
			curr = max;
			max = temp;
		}
		return max;
	}
}