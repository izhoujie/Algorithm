package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2019年12月18日 下午10:35:39 
 * @Description: 1291. 顺次数
 *
	我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。
	
	请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。
	
	 
	
	示例 1：
	
	输出：low = 100, high = 300
	输出：[123,234]
	示例 2：
	
	输出：low = 1000, high = 13000
	输出：[1234,2345,3456,4567,5678,6789,12345]
	 
	
	提示：
	
	10 <= low <= high <= 10^9
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/sequential-digits
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-获取起始数、单次循环边界数、单次循环进位数、循环数再进行计算；
		2-直接初始化36个全解，再根据边界返回子集解即可；（这就谈不上算法了）
 */
public class LeetCode_1291 {
	public static void main(String[] args) {
		List<Integer> list = new Solution_1291().sequentialDigits_2(10, 10);
		System.out.println(list);
	}

}

class Solution_1291 {
	public List<Integer> sequentialDigits_1(int low, int high) {
		// first为当前区间的起始值，如1234；big9是当前区间的最大值，如 6789；前面其实定义了区间的左右边界；add1是进位值，如1111；
		int first = 0;
		int big9 = 0;
		int add1 = 0;
		int temp = low;
		int t9 = 9;
		int carry = 1;
		while (temp > 0) {
			first = first * 10 + first % 10 + 1;
			big9 = t9 % 10 * carry + big9;
			add1 = add1 * 10 + 1;
			temp /= 10;
			t9--;
			carry *= 10;
		}
		// 获取起始值
		int number = first;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (number <= high) {
			while (number <= big9 && number <= high) {
				if (number >= low) {
					list.add(number);
				}
				number += add1;
			}
			first = first * 10 + first % 10 + 1;
			// 重置起始值，增加一位
			number = first;
			add1 = add1 * 10 + 1;
			big9 = t9 % 10 * carry + big9;
			t9--;
			carry *= 10;
		}
		return list;
	}

	public List<Integer> sequentialDigits_2(int low, int high) {
		Integer[] all = new Integer[] { 12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345,
				3456, 4567, 5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567,
				2345678, 3456789, 12345678, 23456789, 123456789 };
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int k = 0; k < all.length; k++) {
			if (all[k] >= low && all[k] <= high)
				list.add(all[k]);

		}
		return list;
	}
}
