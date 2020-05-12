package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月5日 上午12:09:55 
 * @Description: 面试题42. 连续子数组的最大和
 *
	输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
	
	要求时间复杂度为O(n)。
	
	示例1:
	
	输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
	输出: 6
	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	 
	
	提示：
	
	1 <= arr.length <= 10^5
	-100 <= arr[i] <= 100
	注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
	
	 
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_42 {

}

class Solution_Offer_42 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 上午12:10:22 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-只要和为正数时就一直加，一旦变负则重置sum从下一个正数开始累加，期间记录sum的最大值；
	 * 				sum值的图像类似在0上的递增递减曲线；
	 *
	 */
	public int maxSubArray(int[] nums) {
		int sum = 0, max = nums[0];
		for (int val : nums) {
			if (sum > 0) {
				sum += val;
			} else {
				sum = val;
			}
			max = Math.max(max, sum);
		}
		return max;
	}
}