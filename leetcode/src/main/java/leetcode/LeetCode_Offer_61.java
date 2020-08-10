package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月15日 下午11:37:48 
 * @Description: 面试题61. 扑克牌中的顺子
 *
	从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

	示例 1:
	
	输入: [1,2,3,4,5]
	输出: True
	 
	
	示例 2:
	
	输入: [0,0,1,2,5]
	输出: True
	 
	
	限制：
	
	数组长度为 5 
	
	数组的数取值为 [0, 13] .
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_61 {
}

class Solution_Offer_61 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月15日 下午11:38:20 
	 * @param: @param nums
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-判断是否有重复等价于判断最小值与最大值差是否小于5即可
	 *
	 */
	public boolean isStraight(int[] nums) {
		int k = 0, min = 14, max = 0;
		for (int val : nums) {
			if (val != 0) {
				// k的bit用来记录牌是否出现过，第一次出现对应bit置为1，再次出现直接return
				if ((k & (1 << val)) != 0) {
					return false;
				}
				k |= (1 << val);
				min = Math.min(min, val);
				max = Math.max(max, val);
			}
		}
		return max - min < 5;
	}
}
