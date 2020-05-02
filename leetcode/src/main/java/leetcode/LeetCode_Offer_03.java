package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午4:10:35 
 * @Description: 面试题03. 数组中重复的数字
 *
	找出数组中重复的数字。
	
	
	在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
	
	示例 1：
	
	输入：
	[2, 3, 1, 0, 2, 5, 3]
	输出：2 或 3 
	 
	
	限制：
	
	2 <= n <= 100000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_03 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午4:28:09 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-统计
	 *
	 */
	public int findRepeatNumber_1(int[] nums) {
		int[] stastic = new int[nums.length];
		for (int i : nums) {
			stastic[i]++;
			if (stastic[i] > 1) {
				return i;
			}
		}
		return nums[0];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午4:28:24 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-归位校验
	 *
	 */
	public int findRepeatNumber_2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i) {
				if (nums[nums[i]] == nums[i]) {
					return nums[i];
				}
				nums[i] = nums[i] ^ nums[nums[i]];
				nums[nums[i]] = nums[i] ^ nums[nums[i]];
				nums[i] = nums[i] ^ nums[nums[i]];
			}
		}
		return -1;
	}

}
