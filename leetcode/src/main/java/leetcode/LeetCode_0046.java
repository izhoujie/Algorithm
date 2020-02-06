package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年2月2日 下午8:13:33 
 * @Description: 46. 全排列
 *
	给定一个没有重复数字的序列，返回其所有可能的全排列。
	
	示例:
	
	输入: [1,2,3]
	输出:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/permutations
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-递归回溯；
		2-递归回溯；（来自LeetCode评论区解法）
 */
public class LeetCode_0046 {

}

class Solution_0046 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月4日 下午10:38:11 
	 * @param: @param nums
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 1-
	 *
	 */
	public List<List<Integer>> permute_1(int[] nums) {
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
				// 关键点，顺次与后面的数交换后递归，然后再复位进行下次循环
				swap(nums, start, i);
				allSort(nums, list, start + 1);
				swap(nums, start, i);
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月5日 下午7:53:38 
	 * @param: @param nums
	 * @param: @return
	 * @return: List<List<Integer>>
	 * @Description: 2-
	 *
	 */
	public List<List<Integer>> permute_2(int[] nums) {
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) {
			return rst;
		}
		boolean[] f = new boolean[nums.length];
		sortHelper(rst, nums, new ArrayList<Integer>(), f);

		return rst;
	}

	private void sortHelper(List<List<Integer>> rst, int[] nums, ArrayList<Integer> list, boolean[] f) {
		if (list.size() == nums.length) {
			rst.add(new ArrayList<>(list));
			return;
		} else {
			for (int i = 0; i < f.length; i++) {
				if (f[i]) {
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