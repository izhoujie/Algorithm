package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年1月5日 下午11:39:08 
 * @Description: 18. 四数之和
 *
	给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
	
	注意：
	
	答案中不可以包含重复的四元组。
	
	示例：
	
	给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
	
	满足要求的四元组集合为：
	[
	  [-1,  0, 0, 1],
	  [-2, -1, 1, 2],
	  [-2,  0, 0, 2]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/4sum
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-三数之和的扩展，再加一层循环即可；--引申：n数之和的通用算法？
		2-nFourSum方法为n数之和的通用解法；
 */
public class LeetCode_0018 {
	public static void main(String[] args) {
		int[] test = new int[] { 1, 0, -1, 0, -2, 2 };
		System.out.println(new Solution_0018().fourSum(test, 1));
		Arrays.sort(test);
		System.out.println(new Solution_0018().nFourSum(test, 1, 4, 0));
	}
}

class Solution_0018 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月3日 下午8:00:05 
	 * @Description: TODO(方法简述) 
	 * @return List<List<Integer>> 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 下午8:00:05]  
	 * @UpdateRemark:1- 
	 *
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int len;
		if (nums == null || (len = nums.length) < 4) {
			return list;
		}
		if (len == 4) {
			if ((nums[0] + nums[1] + nums[2] + nums[3]) == target) {
				list.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
			}
			return list;
		}
		// 排序
		Arrays.sort(nums);

		for (int i = 0; i < len - 3; i++) {
			// 第一个数和第二个数的去重
			if (i == 0 || nums[i] != nums[i - 1]) {
				for (int j = i + 1; j < len - 2; j++) {
					// 第2个数和第三个数的去重
					if (j == (i + 1) || nums[j] != nums[j - 1]) {
						int left = j + 1, right = len - 1;
						while (left < right) {
							// 第1、2个数确定后当前能组成的最大值和最小值，用于快速跳过
							int min = nums[i] + nums[j] + nums[left] + nums[left + 1];
							int max = nums[i] + nums[j] + nums[right] + nums[right - 1];
							if (min > target) {
								break;
							}
							if (max < target) {
								break;
							}
							int test = nums[i] + nums[j] + nums[left] + nums[right];
							// 值偏小时右侧指针左移，注意去重
							if (test < target) {
								while (left < right && nums[left] == nums[++left])
									;
								// 值偏大时左侧指针右移，注意去重
							} else if (test > target) {
								while (left < right && nums[right] == nums[--right])
									;
							} else {
								// 相等时，保存结果并左右指针都向中间移动，注意去重
								list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
								while (left < right && nums[left] == nums[++left])
									;
								while (left < right && nums[right] == nums[--right])
									;
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月13日 下午7:45:10 
	 * @Description: TODO(方法简述) 
	 * @return List<List<Integer>> 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月13日 下午7:45:10]  
	 * @UpdateRemark:2-求数组nums中n个数的和等于target的所有无重复解 ；nums已做null判断并sort；
	 * 				n减小到2求解
	 *
	 */
	public List<List<Integer>> nFourSum(int[] nums, int target, int n, int start) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		// 当前位置越界时返回结果
		if (start >= nums.length) {
			return list;
			// n的规模大于2时递归减小n的规模
		} else if (n > 2) {
			for (int i = start; i < nums.length - n + 1; i++) {
				if (i == 0 || nums[i] != nums[i - 1]) {
					List<List<Integer>> temp = nFourSum(nums, target - nums[i], n - 1, i + 1);
					if (!temp.isEmpty()) {
						for (List<Integer> tempList : temp) {
							tempList.add(nums[i]);
						}
						list.addAll(temp);
					}
				}
			}
			return list;
			// n规模减小到2时进行求解
		} else if (n == 2) {
			int left = start, right = nums.length - 1;
			while (left < right) {
				int test = nums[left] + nums[right];
				// 值偏小时右侧指针左移，注意去重
				if (test < target) {
					while (left < right && nums[left] == nums[++left])
						;
					// 值偏大时左侧指针右移，注意去重
				} else if (test > target) {
					while (left < right && nums[right] == nums[--right])
						;
				} else {
					// 相等时，保存结果并左右指针都向中间移动，注意去重
					// 此处不可使用Array.asList()，该方法返回的是不可修改的list
					List<Integer> l = new ArrayList<Integer>();
					l.add(nums[left]);
					l.add(nums[right]);
					list.add(l);
					while (left < right && nums[left] == nums[++left])
						;
					while (left < right && nums[right] == nums[--right])
						;
				}
			}
			return list;
		}
		return list;
	}
}
