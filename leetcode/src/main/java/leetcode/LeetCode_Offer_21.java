package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月2日 下午10:34:47 
 * @Description: 面试题21. 调整数组顺序使奇数位于偶数前面
 *
	输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
	
	 
	
	示例：
	
	输入：nums = [1,2,3,4]
	输出：[1,3,2,4] 
	注：[3,1,2,4] 也是正确的答案之一。
	 
	
	提示：
	
	1 <= nums.length <= 50000
	1 <= nums[i] <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_21 {

}

class Solution_Offer_21 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月2日 下午10:38:07 
	 * @param: @param nums
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-左右指针夹逼校验互换；
	 *
	 */
	public int[] exchange(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			while (left < right && nums[left] % 2 != 0) {
				left++;
			}
			while (left < right && nums[right] % 2 == 0) {
				right--;
			}
			if (left < right) {
				nums[left] = nums[left] ^ nums[right];
				nums[right] = nums[left] ^ nums[right];
				nums[left] = nums[left] ^ nums[right];
				left++;
				right--;
			}
		}
		return nums;
	}
}