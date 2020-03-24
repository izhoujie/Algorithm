package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月24日 下午8:53:39 
 * @Description: 213. 打家劫舍 II
 * 
	 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
	
	给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
	
	示例 1:
	
	输入: [2,3,2]
	输出: 3
	解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
	示例 2:
	
	输入: [1,2,3,1]
	输出: 4
	解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
	     偷窃到的最高金额 = 1 + 3 = 4 。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/house-robber-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-dp动态规划，分两种，取第一个和不取第一个；（常规的dp数组可省略）
 *
 */
public class LeetCode_0213 {

}

class Solution_0213 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月24日 下午9:04:26 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-两种情况，抢第一家那就放弃最后一家，不抢第一家最后一家无限制；
	 *
	 */
	public int rob(int[] nums) {
		if (nums == null) {
			return 0;
		}
		int len = 0;
		if ((len = nums.length) == 1) {
			return nums[0];
		}
		// 抢第一家
		int max1 = robMax(nums, 0, len - 2);
		// 不抢第一家
		int max2 = robMax(nums, 1, len - 1);
		return max1 > max2 ? max1 : max2;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月24日 下午9:06:40 
	 * @param: @param nums
	 * @param: @param start
	 * @param: @param end
	 * @param: @return
	 * @return: int
	 * @Description: 具体dp逻辑--aop切面方法
	 *
	 */
	private int robMax(int[] nums, int start, int end) {
		int max = 0, curr = 0;
		while (start <= end) {
			int temp = Math.max(max, curr + nums[start++]);
			curr = max;
			max = temp;
		}
		return max;
	}
}
