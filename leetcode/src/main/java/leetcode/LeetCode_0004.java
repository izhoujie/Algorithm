package leetcode;

/**
 * @author ZhouJie
 * @date 2019年12月9日 下午5:20:24 
 * @Description: 4. 寻找两个有序数组的中位数
 *
	给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
	
	请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
	
	你可以假设 nums1 和 nums2 不会同时为空。
	
	示例 1:
	
	nums1 = [1, 3]
	nums2 = [2]
	
	则中位数是 2.0
	示例 2:
	
	nums1 = [1, 2]
	nums2 = [3, 4]
	
	则中位数是 (2 + 3)/2 = 2.5
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-两个数组分别折半查找对比
		2-逐次比较，时间复杂度为O((m+n)/2)
 */
public class LeetCode_0004 {

}

class Solution_0004 {
	/**
	 * @author ZhouJie
	 * @date 2019年12月9日 下午10:27:25 
	 * @Description: TODO(方法简述) 
	 * @return double 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月9日 下午10:27:25]  
	 * @UpdateRemark:2-逐次比较，时间复杂度为O((m+n)/2)
	 *
	 */
	public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
		int len1 = (nums1 == null) ? 0 : nums1.length;
		int len2 = (nums2 == null) ? 0 : nums2.length;

		int data1 = 0, data2 = 0;
		int n = len1 + len2;
		for (int i = 0, j = 0, k = 0; k <= n / 2; k++) {
			data1 = data2;
			if (i == len1) {
				data2 = nums2[j++];
			} else if (j == len2) {
				data2 = nums1[i++];
			} else if (nums1[i] < nums2[j]) {
				data2 = nums1[i++];
			} else {
				data2 = nums2[j++];
			}
		}
		return n % 2 == 0 ? (data1 + data2) / 2.0 : data2 / 1.0;
	}

	/**
	 * @author ZhouJie
	 * @date 2019年12月9日 下午10:27:36 
	 * @Description: TODO(方法简述) 
	 * @return double 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2019年12月9日 下午10:27:36]  
	 * @UpdateRemark:1-两个数组分别折半查找对比--还不是太懂，此为官方解法
	 *
	 */
	public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
		int len1 = (nums1 == null) ? 0 : nums1.length;
		int len2 = (nums2 == null) ? 0 : nums2.length;

		if (len1 > len2) {
			return findMedianSortedArrays_2(nums2, nums1);
		}

		int left = 0, right = len1, k = (len1 + len2 + 1) / 2;

		while (left <= right) {
			int i = (left + right) / 2;
			int j = k - i;
			if (i < right && nums1[i] < nums2[j - 1]) {
				left = i + 1;
			} else if (i > left && nums1[i - 1] > nums2[j]) {
				right = i - 1;
			} else {
				int data1 = 0;
				if (i == 0) {
					data1 = nums2[j - 1];
				} else if (j == 0) {
					data1 = nums1[i - 1];
				} else {
					data1 = Math.max(nums1[i - 1], nums2[j - 1]);
				}
				if ((len1 + len2) % 2 == 1) {
					return data1;
				} else {
					int data2 = 0;
					if (i == len1) {
						data2 = nums2[j];
					} else if (j == len2) {
						data2 = nums1[i];
					} else {
						data2 = Math.min(nums1[i], nums2[j]);
					}
					return (data1 + data2) / 2.0;
				}
			}
		}
		return 0;
	}
}