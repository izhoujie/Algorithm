package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年5月5日 下午9:15:03 
 * @Description: 面试题45. 把数组排成最小的数
 *
	输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
	
	示例 1:
	
	输入: [10,2]
	输出: "102"
	示例 2:
	
	输入: [3,30,34,5,9]
	输出: "3033459"
	 
	
	提示:
	
	0 < nums.length <= 100
	说明:
	
	输出结果可能非常大，所以你需要返回一个字符串而不是整数
	拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_45 {

}

class Solution_Offer_45 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 下午9:19:34 
	 * @param: @param nums
	 * @param: @return
	 * @return: String
	 * @Description: 1-转为String，然后排序，比较逻辑为：拼接后的字符串小的排在最前面（字符小对应的数值也小）；
	 *
	 */
	public String minNumber_1(int[] nums) {
		List<String> list = new ArrayList<String>();
		for (int val : nums) {
			list.add(String.valueOf(val));
		}
		// 转为String后对每两个字符串拼接后比较排序；
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// 字符拼接后的比较结果与值的拼接比较结果一致
				return (o1 + o2).compareTo(o2 + o1);
			}
		});
		return String.join("", list);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 下午9:28:53 
	 * @param: @param nums
	 * @param: @return
	 * @return: String
	 * @Description: 2-重写int的sort排序，然后StringBuilder拼接；
	 *
	 */
	public String minNumber_2(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		StringBuilder sb = new StringBuilder();
		for (int val : nums) {
			sb.append(val);
		}
		return sb.toString();
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 下午11:50:33 
	 * @param: @param nums
	 * @param: @param start
	 * @param: @param end
	 * @return: void
	 * @Description: 快速排序
	 *
	 */
	private void quickSort(int[] nums, int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start, j = end, mid = nums[start];
		while (i < j) {
			while (i < j && compare(nums[j], mid)) {
				j--;
			}
			while (i < j && compare(mid, nums[i])) {
				i++;
			}
			if (i < j) {
				nums[i] = nums[i] ^ nums[j];
				nums[j] = nums[i] ^ nums[j];
				nums[i] = nums[i] ^ nums[j];
			}
		}
		nums[start] = nums[i];
		nums[i] = mid;
		quickSort(nums, start, i - 1);
		quickSort(nums, i + 1, end);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月5日 下午11:50:46 
	 * @param: @param num1
	 * @param: @param num2
	 * @param: @return
	 * @return: boolean
	 * @Description: 自定义int的拼接比较
	 *
	 */
	private boolean compare(int num1, int num2) {
		long base1 = 10, base2 = 10;
		while (num1 / base1 > 0) {
			base1 *= 10;
		}
		while (num2 / base2 > 0) {
			base2 *= 10;
		}
		return (num1 * base2 + num2) >= (num2 * base1 + num1);
	}

}