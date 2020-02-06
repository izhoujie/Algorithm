package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年1月4日 下午9:38:20 
 * @Description: 16. 最接近的三数之和
 *
	给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
	
	例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
	
	与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/3sum-closest
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-与No.15的思路一样，区别在于判断和时需要大小区分最近值；
	
 */
public class LeetCode_0016 {
	public static void main(String[] args) {
		int[] test = new int[] { 6, -18, -20, -7, -15, 9, 18, 10, 1, -20, -17, -19, -3, -5, -19, 10, 6, -11, 1, -17,
				-15, 6, 17, -18, -3, 16, 19, -20, -3, -17, -15, -3, 12, 1, -9, 4, 1, 12, -2, 14, 4, -4, 19, -20, 6, 0,
				-19, 18, 14, 1, -15, -5, 14, 12, -4, 0, -10, 6, 6, -6, 20, -8, -6, 5, 0, 3, 10, 7, -2, 17, 20, 12, 19,
				-13, -1, 10, -1, 14, 0, 7, -3, 10, 14, 14, 11, 0, -4, -15, -8, 3, 2, -5, 9, 10, 16, -4, -3, -9, -8, -14,
				10, 6, 2, -12, -7, -16, -6, 10 };
		System.out.println(new Solution_0016().threeSumClosest_2(test, -52));
	}
}

class Solution_0016 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月5日 下午8:55:13 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月5日 下午8:55:13]  
	 * @UpdateRemark:自己实现，未优化
	 *
	 */
	public int threeSumClosest_1(int[] nums, int target) {
		int nearTarget = 0;
		// null判断
		if (nums == null) {
			return nearTarget;
		}
		int len = nums.length;
		// 最多只有3个数时
		if (len <= 3) {
			for (int i = 0; i < len; i++) {
				nearTarget += nums[i];
			}
			return nearTarget;
		}
		// 先排序
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		for (int i = 0; i < len - 2; i++) {
			int left = i + 1, right = len - 1;
			// 记录第一个值
			if (i == 0) {
				nearTarget = nums[i] + nums[left] + nums[right];
			}
			// 取左右两端为初始值开始循环校验
			while (left < right) {
				int temp = nums[i] + nums[left] + nums[right];
				// 等于时直接返回
				if (temp == target) {
					return temp;
				} else {
					// 不等时记录最接近值
					if (Math.abs(target - nearTarget) > Math.abs(target - temp)) {
						nearTarget = temp;
					}
					// 值偏大时右端左移，值偏小时左端右移
					if (temp > target) {
						while (nums[right] == nums[--right] && left < right)
							;
					} else {
						while (nums[left] == nums[++left] && left < right)
							;
					}
				}
			}
		}
		return nearTarget;
	}

	/**
	 * @author ZhouJie
	 * @date 2020年1月5日 下午8:56:01 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月5日 下午8:56:01]  
	 * @UpdateRemark:参考leetcode解题优化后的  
	 *
	 */
	public int threeSumClosest_2(int[] nums, int target) {
		int nearTarget = 0;
		// null判断
		if (nums == null) {
			return nearTarget;
		}
		int len = nums.length;
		// 最多只有3个数时
		if (len <= 3) {
			for (int i = 0; i < len; i++) {
				nearTarget += nums[i];
			}
			return nearTarget;
		}
		// 排序
		Arrays.sort(nums);
		int near0 = Integer.MAX_VALUE, check, sum;
		for (int i = 0; i < len - 2; i++) {
			int left = i + 1, right = len - 1;
			// 最大值和最小值校验，快速跳过
			int min = nums[i] + nums[left] + nums[left + 1];
			int max = nums[i] + nums[right] + nums[right - 1];
			if (min > target) {
				check = min - target;
				if (check < near0) {
					near0 = check;
					nearTarget = min;
				}
			} else if (max < target) {
				check = target - max;
				if (check < near0) {
					near0 = check;
					nearTarget = max;
				}
			} else {
				while (left < right) {
					sum = nums[i] + nums[left] + nums[right];
					// 值偏大时右端左移，值偏小时左端右移，等于时直接返回
					if (sum > target) {
						while (nums[right] == nums[--right] && left < right)
							;
					} else if (sum < target) {
						while (nums[left] == nums[++left] && left < right)
							;
					} else {
						return sum;
					}
					// 不等时记录最接近值
					check = target > sum ? target - sum : sum - target;
					if (check < near0) {
						near0 = check;
						nearTarget = sum;
					}
				}
			}
		}
		return nearTarget;
	}
}