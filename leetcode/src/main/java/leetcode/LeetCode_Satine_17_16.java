package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月24日 下午7:49:36 
 * @Description: 面试题 17.16. 按摩师
 *
	一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
	
	注意：本题相对原题稍作改动
	
	 
	
	示例 1：
	
	输入： [1,2,3,1]
	输出： 4
	解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
	示例 2：
	
	输入： [2,7,9,3,1]
	输出： 12
	解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
	示例 3：
	
	输入： [2,1,4,5,3,1,1,3]
	输出： 12
	解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/the-masseuse-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-动态规划；
		2-对1的优化；
 */
public class LeetCode_Satine_17_16 {

}

class Solution_17_16 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月24日 下午8:41:37 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-dp动态规划；
	 *
	 */
	public int massage_1(int[] nums) {
		int len = 0;
		if (nums == null || (len = nums.length) == 0) {
			return 0;
		}
		if (len == 1) {
			return nums[0];
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
	 * @date: 2020年3月24日 下午8:41:54 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-动态规划优化掉数组；
	 *
	 */
	public int massage_2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int max = 0, curr = 0;
		for (int i : nums) {
			int temp = Math.max(max, curr + i);
			curr = max;
			max = temp;
		}
		return max;
	}
}