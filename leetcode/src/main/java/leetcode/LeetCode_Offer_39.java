package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年5月3日 下午11:54:31 
 * @Description: 面试题39. 数组中出现次数超过一半的数字
 *
	数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
	
	你可以假设数组是非空的，并且给定的数组总是存在多数元素。
	
	示例 1:
	
	输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
	输出: 2 
	
	限制：
	
	1 <= 数组长度 <= 50000
	
	注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_39 {

}

class Solution_Offer_39 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月3日 下午11:55:17 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-使用HashMap统计；
	 *
	 */
	public int majorityElement_1(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int half = nums.length >> 1;
		for (int val : nums) {
			map.put(val, map.getOrDefault(val, 0) + 1);
			if (map.get(val) > half) {
				return val;
			}
		}
		return nums[0];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月4日 上午12:03:11 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-直接统计：把数组看为两部分，一部分是目标数，一部分是非目标数，因为目标数过半，所以对冲后最终会剩下目标数；
	 *
	 */
	public int majorityElement_2(int[] nums) {
		int count = 0, target = nums[0];
		for (int i = 0; i < nums.length; i++) {
			if (target == nums[i]) {
				count++;
			} else {
				count--;
				if (count == 0) {
					target = nums[i + 1];
				}
			}
		}
		return target;
	}

}