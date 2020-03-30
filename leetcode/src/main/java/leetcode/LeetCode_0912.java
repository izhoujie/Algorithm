package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年3月31日 上午12:02:03 
 * @Description: 912. 排序数组
 *
	给定一个整数数组 nums，将该数组升序排列。
	
	示例 1：
	
	输入：[5,2,3,1]
	输出：[1,2,3,5]
	示例 2：
	
	输入：[5,1,1,2,0,0]
	输出：[0,0,1,1,2,5]
	
	提示：
	
	1 <= A.length <= 10000
	-50000 <= A[i] <= 50000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/sort-an-array
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-快排完事；
 */
public class LeetCode_0912 {

}

class Solution_0912 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月31日 上午12:50:01 
	 * @param: @param nums
	 * @param: @return
	 * @return: int[]
	 * @Description: 快速排序
	 *
	 */
	public int[] sortArray(int[] nums) {
		Arrays.sort(nums);
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月31日 上午12:50:04 
	 * @param: @param nums
	 * @param: @param start
	 * @param: @param end
	 * @return: void
	 * @Description: 快排核心
	 *
	 */
	private void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int midVal = nums[start];
		int left = start, right = end;
		while (left < right) {
			while (left < right && nums[right] >= midVal) {
				right--;
			}
			nums[left] = nums[right];
			while (left < right && nums[left] <= midVal) {
				left++;
			}
			nums[right] = nums[left];
		}
		nums[left] = midVal;

		quickSort(nums, start, left - 1);
		quickSort(nums, left + 1, end);
	}

}
