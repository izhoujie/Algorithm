package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午9:22:44 
 * @Description: 面试题11. 旋转数组的最小数字
 *
	把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
	
	示例 1：
	
	输入：[3,4,5,1,2]
	输出：1
	示例 2：
	
	输入：[2,2,2,0,1]
	输出：0
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_11 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午9:59:58 
	 * @param: @param numbers
	 * @param: @return
	 * @return: int
	 * @Description: 1-直接遍历求最小值，其实等价于直接从0到n-1的遍历；
	 *
	 */
	public int minArray_1(int[] numbers) {
		int len = 0;
		if ((len = numbers.length) == 1) {
			return numbers[0];
		} else {
			int k = len - 1;
			while (k > 0) {
				if (numbers[k - 1] > numbers[k]) {
					return Math.min(numbers[0], numbers[k]);
				}
				k--;
			}
			return numbers[0];
		}
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午9:59:55 
	 * @param: @param numbers
	 * @param: @return
	 * @return: int
	 * @Description: 2-二分查找最小值；
	 *
	 */
	public int minArray_2(int[] numbers) {
		int left = 0, right = numbers.length - 1, mid;
		while (left < right) {
			mid = (left + right) >> 1;
			// 中间值小于右侧说明右侧有序且最小值在mid左侧
			if (numbers[mid] < numbers[right]) {
				right = mid;
				// 中间值大于右侧说明跨越了转折位置，且最小值在mid右侧
			} else if (numbers[mid] > numbers[right]) {
				left = mid + 1;
			} else {
				// 值相同从右侧缩减
				right--;
			}
		}
		return numbers[left];
	}
}
