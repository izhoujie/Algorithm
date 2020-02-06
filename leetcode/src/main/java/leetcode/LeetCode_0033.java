package leetcode;

/**
 * @author ZhouJie
 * @date 2020年1月17日 下午10:22:10 
 * @Description: 33. 搜索旋转排序数组
 *
	假设按照升序排序的数组在预先未知的某个点上进行了旋转。
	
	( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
	
	搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
	
	你可以假设数组中不存在重复的元素。
	
	你的算法时间复杂度必须是 O(log n) 级别。
	
	示例 1:
	
	输入: nums = [4,5,6,7,0,1,2], target = 0
	输出: 4
	示例 2:
	
	输入: nums = [4,5,6,7,0,1,2], target = 3
	输出: -1
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-二分查找，两端值都需要判断；
		2-优化后的；（参考LeetCode评论区）
 */
public class LeetCode_0033 {
	public static void main(String[] args) {
		int test1[] = { 4, 5, 6, 7, 0, 1, 2 };
		int test2[] = { 1, 3 };
		int test3[] = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(new Solution_0033().search_1(test1, 0));
		System.out.println(new Solution_0033().search_1(test2, 3));
		System.out.println(new Solution_0033().search_1(test3, 8));
	}
}

class Solution_0033 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午9:39:47 
	 * @param: @param nums
	 * @param: @param target
	 * @param: @return
	 * @return: int
	 * @Description: 1-
	 *
	 */
	public int search_1(int[] nums, int target) {
		if (nums == null) {
			return -1;
		}
		int i = 0, j = nums.length - 1, k;
		while (i <= j) {
			k = (i + j) / 2;
			if (i == j && nums[k] != target) {
				return -1;
			}
			if (nums[k] == target) {
				return k;
			} else if (nums[i] > nums[k] && nums[k] < nums[j]) {
				if (target > nums[k] && target <= nums[j]) {
					i = k + 1;
				} else {
					j = k - 1;
				}
			} else if (nums[i] < nums[k] && nums[k] > nums[j]) {
				if (target >= nums[i] && target < nums[k]) {
					j = k - 1;
				} else {
					i = k + 1;
				}
			} else if (nums[i] <= nums[k] && nums[k] < nums[j]) {
				if (target < nums[k]) {
					j = k - 1;
				} else {
					i = k + 1;
				}
			} else if (target > nums[j]) {
				j = k - 1;
			} else {
				i = k + 1;
			}
		}
		return -1;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月19日 上午12:13:34 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月19日 上午12:13:34]  
	 * @UpdateRemark:2-思路：关键是找到有序区间，每次的判断仅在有序区间内进行，比较条理清晰；
	 *
	 */
	public int search_2(int[] nums, int target) {
		if (nums == null) {
			return -1;
		}
		int left = 0, right = nums.length - 1;
		int mid;
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
				// 若mid<right，说明右半边有序递增
			} else if (nums[mid] < nums[right]) {
				// 目标值在右侧有序区间内
				if (target > nums[mid] && target <= nums[right]) {
					left = mid + 1;
					// 目标在左侧区间
				} else {
					right = mid - 1;
				}
				// 若右半边无序，则说明左半边有序，在左半边进行判断
				// 目标值在左半边有序区间内
			} else if (target >= nums[left] && target < nums[mid]) {
				right = mid - 1;
				// 最后只剩右侧区间
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
