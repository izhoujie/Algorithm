package leetcode;

/**
 * @author ZhouJie
 * @date 2020年5月17日 下午2:34:15 
 * @Description: 面试题56 - II. 数组中数字出现的次数 II
 *
	在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

	示例 1：
	
	输入：nums = [3,4,3,3]
	输出：4
	示例 2：
	
	输入：nums = [9,1,7,9,7,9,7]
	输出：1
	 
	
	限制：
	
	1 <= nums.length <= 10000
	1 <= nums[i] < 2^31
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_Offer_56_2 {

}

class Solution_Offer_56_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月17日 下午2:34:51 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-统计各个 bit位1的出现次数，对要求的次数取模运算；
	 *
	 */
	public int singleNumber_1(int[] nums) {
		int[] bit = new int[32];
		int counts = 3, rst = 0, b = 0;
		for (int val : nums) {
			for (int i = 0; i < 32; i++) {
				b = val >>> i;
				// 之后的bit位均为0，则停止统计，无意义
				if (b == 0) {
					break;
				}
				// 当前要统计的bit为1才统计
				if ((b &= 1) == 1) {
					bit[i]++;
				}
			}
		}
		for (int i = 0; i < 32; i++) {
			// 若对应bit位统计到的1不是counts的倍数，则将其置为1；
			if (bit[i] % counts != 0) {
				rst |= 1 << i;
			}
		}
		return rst;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月17日 下午6:00:06 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-用两个数的对应bit位的00来统计该bit位上1的出现次数：
	 * 					00代表1出现0次；
	 * 					01代表1出现1次；
	 * 					10代表1出现2次；
	 * 					00代表1出现3次；
	 * 
	 * 					即00代表1出现3n次，对于本题，最后每个bit位的情况只可能为00或者01；
	 * 
	 * 
	 */
	public int singleNumber_2(int[] nums) {
		int one = 0, two = 0;
		for (int val : nums) {
			one = one ^ val & ~two;
			two = two ^ val & ~one;
		}
		return one;
	}

}