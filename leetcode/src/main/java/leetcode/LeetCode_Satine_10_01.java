package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月6日 下午8:29:53 
 * @Description: 面试题 10.01. 合并排序的数组
 *
	给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
	
	初始化 A 和 B 的元素数量分别为 m 和 n。
	
	示例:
	
	输入:
	A = [1,2,3,0,0,0], m = 3
	B = [2,5,6],       n = 3
	
	输出: [1,2,2,3,5,6]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/sorted-merge-lcci
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-从后往前遍历，即先放大的；
 */
public class LeetCode_Satine_10_01 {

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午8:31:11 
	 * @param: @param nums1
	 * @param: @param m
	 * @param: @param nums2
	 * @param: @param n
	 * @return: void
	 * @Description: 1-从m+n往前遍历，顺次放大到小的值
	 *
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int k = m-- + n-- - 1;
		// 比较放值
		while (m > -1 && n > -1) {
			nums1[k--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
		}
		// 处理nums2中剩下的数
		while (n > -1) {
			nums1[k--] = nums2[n--];
		}
	}
}