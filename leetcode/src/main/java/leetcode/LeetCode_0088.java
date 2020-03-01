package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月1日 下午6:13:07 
 * @Description: 88. 合并两个有序数组
 *
	给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
	
	说明:
	
	初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
	你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
	示例:
	
	输入:
	nums1 = [1,2,3,0,0,0], m = 3
	nums2 = [2,5,6],       n = 3
	
	输出: [1,2,2,3,5,6]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/merge-sorted-array
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-顺序比较，“挪位置”；
		2-后向前直接比较；
 */
public class LeetCode_0088 {

}

class Solution_0088 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午7:24:50 
	 * @param: @param nums1
	 * @param: @param m
	 * @param: @param nums2
	 * @param: @param n
	 * @return: void
	 * @Description: 1-“挪位置”；
	 *
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = 0, j = 0, k = m;
		while (i < m && j < n) {
			if (nums2[j] < nums1[i++]) {
				move(nums1, i - 1, m++);
				nums1[i - 1] = nums2[j++];
			}
		}
		k += j;
		while (j < n) {
			nums1[k++] = nums2[j++];
		}
	}

	private void move(int[] nums1, int i, int j) {
		while (j > i) {
			nums1[j--] = nums1[j];
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月1日 下午7:31:12 
	 * @param: @param nums1
	 * @param: @param m
	 * @param: @param nums2
	 * @param: @param n
	 * @return: void
	 * @Description: 2-从后往前，只需比较；
	 *
	 */
	public void merge_2(int[] nums1, int m, int[] nums2, int n) {
		int k = m-- + n-- - 1;
		while (m > -1 && n > -1) {
			nums1[k--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
		}
		while (n > -1) {
			nums1[k--] = nums2[n--];
		}
	}
}
