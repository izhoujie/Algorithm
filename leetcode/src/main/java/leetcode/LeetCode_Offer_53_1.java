package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月22日 下午8:33:39 
 * @Description: 面试题53 - I. 在排序数组中查找数字 I
 *
	统计一个数字在排序数组中出现的次数。
	
	示例 1:
	
	输入: nums = [5,7,7,8,8,10], target = 8
	输出: 2
	示例 2:
	
	输入: nums = [5,7,7,8,8,10], target = 6
	输出: 0
	 
	
	限制：
	
	0 <= 数组长度 <= 50000
	
	注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_53_1 {

}

class Solution_Offer_53_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午8:34:16 
	 * @param: @param nums
	 * @param: @param target
	 * @param: @return
	 * @return: int
	 * @Description: 1-两次二分查找分别确定左右边界；
	 *
	 */
	public int search(int[] nums, int target) {
		if (nums.length == 0) {
			return 0;
		}
		int left = findBound(nums, target, true);
		if (left == nums.length || nums[left] != target) {
			return 0;
		}
		int right = findBound(nums, target, false);
		return right - left;
	}

	private int findBound(int[] nums, int target, boolean f) {
		int l = 0, r = nums.length;
		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] > target || (f && nums[mid] == target)) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return l;
	}
}