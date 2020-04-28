package leetcode;

/**
 * @author ZhouJie
 * @date 2020年4月28日 下午1:04:25 
 * @Description: 面试题56 - I. 数组中数字出现的次数
 *
	一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
	
	 
	
	示例 1：
	
	输入：nums = [4,1,4,6]
	输出：[1,6] 或 [6,1]
	示例 2：
	
	输入：nums = [1,2,10,4,1,4,3,3]
	输出：[2,10] 或 [10,2]
	 
	
	限制：
	
	2 <= nums <= 10000
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LeetCode_Offer_56_1 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月28日 下午1:04:58 
	 * @param: @param nums
	 * @param: @return
	 * @return: int[]
	 * @Description: 1-利用相同数异或为0的特性
	 *
	 */
	public int[] singleNumbers(int[] nums) {
		// 所有数的异或结果
		int all = 0;
		for (int i : nums) {
			all ^= i;
		}
		// 求得all的bit位最右侧1对应的值为分组标记
		int flag = all & (-all);
		int x = 0;
		for (int i : nums) {
			// 分组异或，
			if ((flag & i) != 0) {
				x ^= i;
			}
		}
		return new int[] { x, x ^ all };
	}

}
