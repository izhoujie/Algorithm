package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年3月13日 下午10:31:53 
 * @Description: 169. 多数元素
 *
	给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
	
	你可以假设数组是非空的，并且给定的数组总是存在多数元素。
	
	示例 1:
	
	输入: [3,2,3]
	输出: 3
	示例 2:
	
	输入: [2,2,1,1,1,2,2]
	输出: 2
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/majority-element
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0169 {

}

class Solution_0169 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月13日 下午10:35:23 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-hashMap一次遍历且遍历时校验出现次数；
	 *
	 */
	public int majorityElement_1(int[] nums) {
		int len = nums.length;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(len);
		for (int i = 0; i < len; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
			if (map.get(nums[i]) > len / 2) {
				return nums[i];
			}
		}
		return nums[0];
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月13日 下午10:38:03 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-因为总有一个数的出现次数大于len/2，那么这个数出现次数减去其他数的出现次数必大于0，使用一个计数器即可；
	 *
	 */
	public int majorityElement_2(int[] nums) {
		int rst = nums[0], count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (rst == nums[i]) {
				count++;
			} else {
				count--;
				if (count == 0) {
					rst = nums[i + 1];
				}
			}
		}
		return rst;
	}
}