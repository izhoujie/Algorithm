package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月6日 下午8:35:40 
 * @Description: 面试题57. 和为s的两个数字
 *
	输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
	
	 
	
	示例 1：
	
	输入：nums = [2,7,11,15], target = 9
	输出：[2,7] 或者 [7,2]
	示例 2：
	
	输入：nums = [10,26,30,31,47,60], target = 40
	输出：[10,30] 或者 [30,10]
	 
	
	限制：
	
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^6
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-先从后往前，跳过所有大于target的数，然后再前后双指针遍历处理；
 */
public class LeetCode_Offer_57_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午8:36:10 
	 * @param: @param nums
	 * @param: @param target
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-
	 *
	 */
	public int[] twoSum(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		// 从后往前跳过所有大于target的
		while (r > l && nums[r] > target) {
			r--;
		}
		// 前后指针遍历
		while (l < r) {
			int t = nums[l] + nums[r];
			if (t < target) {
				l++;
			} else if (t > target) {
				r--;
			} else {
				break;
			}
		}
		return new int[] { nums[l], nums[r] };
	}
}
