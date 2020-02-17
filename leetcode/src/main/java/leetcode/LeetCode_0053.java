package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月8日 下午11:24:18 
 * @Description: 53. 最大子序和
 *
	给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	
	示例:
	
	输入: [-2,1,-3,4,-1,2,1,-5,4],
	输出: 6
	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	进阶:
	
	如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/maximum-subarray
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-贪心算法
 */
public class LeetCode_0053 {

}

class Solution_0053 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月8日 下午11:51:24 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int maxSubArray_1(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int maxSum, currSum;
		maxSum = currSum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			currSum = Math.max(currSum, currSum + nums[i]);
			maxSum = Math.max(maxSum, currSum);
		}
		return maxSum;
	}
}