package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午4:16:54 
 * @Description: 35. 搜索插入位置
 *
	给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
	
	你可以假设数组中无重复元素。
	
	示例 1:
	
	输入: [1,3,5,6], 5
	输出: 2
	示例 2:
	
	输入: [1,3,5,6], 2
	输出: 1
	示例 3:
	
	输入: [1,3,5,6], 7
	输出: 4
	示例 4:
	
	输入: [1,3,5,6], 0
	输出: 0
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/search-insert-position
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-二分查找
 */
public class LeetCode_0035 {

}

class Solution_0035 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午9:47:31 
	 * @param: @param nums
	 * @param: @param target
	 * @param: @return
	 * @return: int
	 * @Description: 1-与No.34的寻找边界的二分算法一致
	 *
	 */
	public int searchInsert(int[] nums, int target) {
		if (nums == null) {
			return 0;
		}
		int l = 0, r = nums.length;
		while (l < r) {
			int m = (l + r) / 2;
			if (nums[m] >= target) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
}
