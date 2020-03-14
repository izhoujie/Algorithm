package leetcode;

import java.util.TreeSet;

/**
 * @author ZhouJie
 * @date 2020年3月14日 下午12:55:39 
 * @Description: 300. 最长上升子序列
 *
 *
	给定一个无序的整数数组，找到其中最长上升子序列的长度。
	
	示例:
	
	输入: [10,9,2,5,3,7,101,18]
	输出: 4 
	解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
	说明:
	
	可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
	你算法的时间复杂度应该为 O(n2) 。
	进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0300 {
	public static void main(String[] args) {
		int[] test = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(new Solution_0300().lengthOfLIS_3(test));
	}

}

class Solution_0300 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午1:13:17 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-dp动态规划；
	 *
	 */
	public int lengthOfLIS_1(int[] nums) {
		int len = 0;
		if (nums == null || (len = nums.length) == 0) {
			return 0;
		}
		int[] dp = new int[len];
		dp[0] = 1;
		int maxLength = 1;
		for (int i = 1; i < len; i++) {
			int tempMax = 0;
			for (int j = 0; j < i; j++)
				if (nums[i] > nums[j])
					tempMax = Math.max(tempMax, dp[j]);
			dp[i] = tempMax + 1;
			maxLength = Math.max(maxLength, dp[i]);
		}
		return maxLength;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午1:57:37 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-使用TreeSet的ceiling方法特性，直接使用API解决二分的问题；
	 *
	 */
	public int lengthOfLIS_2(int[] nums) {
		int len = 0;
		if (nums == null || (len = nums.length) == 0) {
			return 0;
		}
		TreeSet<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < len; i++) {
			// floor(E e) 方法返回在这个集合中小于或者等于给定元素的最大元素，如果不存在这样的元素,返回null.
			// ceiling(E e) 方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null.
			Integer e = set.ceiling(nums[i]);
			if (e == null) {
				set.add(nums[i]);
			} else {
				set.remove(e);
				set.add(nums[i]);
			}
		}
		return set.size();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月14日 下午2:13:59 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 3-手动二分查找
	 *
	 */
	public int lengthOfLIS_3(int[] nums) {
		int len = 0;
		if (nums == null || (len = nums.length) < 2) {
			return len;
		}
		int[] asc = new int[len];
		asc[0] = nums[0];
		int k = 0;
		for (int i = 1; i < len; i++) {
			if (nums[i] > asc[k]) {
				asc[++k] = nums[i];
			} else {
				int l = 0, r = k;
				while (l < r) {
					int mid = l + (r - l) / 2;
					if (asc[mid] < nums[i]) {
						l = mid + 1;
					} else {
						r = mid;
					}
				}
				asc[l] = nums[i];
			}
		}
		return ++k;
	}

}
