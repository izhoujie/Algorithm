package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月18日 下午3:02:09 
 * @Description: 152. 乘积最大子数组
 *
	给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
	
	示例 1:
	
	输入: [2,3,-2,4]
	输出: 6
	解释: 子数组 [2,3] 有最大乘积 6。
	示例 2:
	
	输入: [-2,0,-1]
	输出: 0
	解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/maximum-product-subarray
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0152 {

}

class Solution_0152 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月18日 下午3:02:39 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int maxProduct_1(int[] nums) {
		int max = 1, min = 1, result = Integer.MIN_VALUE;
		for (int val : nums) {
			// 最值初始化均为1，那么后续每遇到一次负数就将最值互换一次，保持最值的属性不变；
			if (val < 0) {
				max = max ^ min;
				min = max ^ min;
				max = max ^ min;
			}
			// 求连乘到现在的最值，是与当前要乘的因子比较
			max = Math.max(max * val, val);
			min = Math.min(min * val, val);
			// 记录最终的最大值
			result = Math.max(result, max);
		}
		return result;
	}
}
