package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月19日 上午12:40:26 
 * @Description: 34. 在排序数组中查找元素的第一个和最后一个位置
 *
	给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
	
	你的算法时间复杂度必须是 O(log n) 级别。
	
	如果数组中不存在目标值，返回 [-1, -1]。
	
	示例 1:
	
	输入: nums = [5,7,7,8,8,10], target = 8
	输出: [3,4]
	示例 2:
	
	输入: nums = [5,7,7,8,8,10], target = 6
	输出: [-1,-1]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-二分查找，在找到最终值的时候还需要处理边界；
		2-左右边界分两次二分查找，增加判断参数（来自LeetCode官方解法）
 */
public class LeetCode_0034 {

}

class Solution_0034 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月22日 下午9:01:09 
	 * @param: @param nums
	 * @param: @param target
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-两次二分查找确定target的左右边界；
	 *
	 */
	public int[] searchRange_1(int[] nums, int target) {
		int[] rst = { -1, -1 };
		int left;
		// 左边界定位
		left = findBound(nums, target, true);
		// 若left已越界或者最终定位的left不等于target，说明不存在target
		if (left == nums.length || nums[left] != target) {
			return rst;
		}
		rst[0] = left;
		// 右边界定位
		rst[1] = findBound(nums, target, false) - 1;
		return rst;
	}

	private int findBound(int[] nums, int target, boolean left) {
		int l = 0, r = nums.length;
		while (l < r) {
			int m = (l + r) / 2;
			// 关键点，left参数用来判断是找左边界还是找右边界
			if (nums[m] > target || (left && nums[m] == target)) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
}
