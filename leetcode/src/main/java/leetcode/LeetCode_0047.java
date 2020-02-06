package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年2月5日 下午7:24:20 
 * @Description: 47. 全排列 II
 *
	给定一个可包含重复数字的序列，返回所有不重复的全排列。
	
	示例:
	
	输入: [1,1,2]
	输出:
	[
	  [1,1,2],
	  [1,2,1],
	  [2,1,1]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/permutations-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-与No.46相似，关键点在于全排列中的去重；
		2-递归回溯；（来自LeetCode评论区解法）
 */
public class LeetCode_0047 {

}

class Solution_0047 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月5日 下午8:55:50 
	 * @param: @param nums
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-
	 *
	 */
	public List<List<Integer>> permuteUnique_1(int[] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return list;
		}
		allSort(nums, list, 0);
		return list;
	}

	private void allSort(int[] nums, List<List<Integer>> list, int start) {
		if (start == nums.length) {
			List<Integer> l = new ArrayList<Integer>();
			for (int i : nums) {
				l.add(i);
			}
			list.add(l);
		} else {
			for (int i = start; i < nums.length; i++) {
				// 检查在当前[start,i)区间是否存在等于nums[i]的数，如有则跳过，去重全排列关键点
				if (check(nums, start, i)) {
					swap(nums, start, i);
					allSort(nums, list, start + 1);
					swap(nums, start, i);
				}
			}
		}
	}

	private boolean check(int[] nums, int start, int end) {
		while (start < end && nums[start] != nums[end]) {
			start++;
		}
		return start == end;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月5日 下午7:58:07 
	 * @param: @param nums
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-
	 *
	 */
	public List<List<Integer>> permuteUnique_2(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return rst;
		}
		boolean[] f = new boolean[nums.length];
		// 必须先排序
		Arrays.sort(nums);
		sortHelper(rst, nums, new ArrayList<Integer>(), f);
		return rst;
	}

	private void sortHelper(List<List<Integer>> rst, int[] nums, ArrayList<Integer> list, boolean[] f) {
		if (list.size() == nums.length) {
			rst.add(new ArrayList<>(list));
		} else {
			for (int i = 0; i < f.length; i++) {
				if (i > 0 && nums[i - 1] == nums[i] && !f[i - 1] || f[i]) {
					continue;
				} else {
					list.add(nums[i]);
					f[i] = true;
					sortHelper(rst, nums, list, f);
					f[i] = false;
					list.remove(list.size() - 1);
				}
			}
		}
	}
}
