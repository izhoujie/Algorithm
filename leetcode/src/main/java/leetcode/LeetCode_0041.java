package leetcode;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author ZhouJie
 * @date 2020年2月1日 下午7:16:14 
 * @Description: 41. 缺失的第一个正数
 *
	给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
	
	示例 1:
	
	输入: [1,2,0]
	输出: 3
	示例 2:
	
	输入: [3,4,-1,1]
	输出: 2
	示例 3:
	
	输入: [7,8,9,11,12]
	输出: 1
	说明:
	
	你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/first-missing-positive
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-使用BitSet;（超出内存限制...）
		2-原地粗略排序，使得nums[i]=i+1，最后遍历即可；
 */
public class LeetCode_0041 {
	public static void main(String[] args) {
		Solution_0041 oj = new Solution_0041();
		int test[] = { -1, 4, 2, 1, 9, 10 };
		oj.firstMissingPositive_1(test);
		System.out.println(Arrays.toString(test));
		System.out.println(oj.firstMissingPositive_2(test));
	}

}

class Solution_0041 {
	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午8:29:42 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午8:29:42]  
	 * @UpdateRemark:1-使用 BitSet--内存空间超限
	 *
	 */
	public int firstMissingPositive_1(int[] nums) {
		if (nums == null) {
			return 1;
		}
		BitSet bitSet = new BitSet(Integer.MAX_VALUE);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				bitSet.set(nums[i]);
			}
		}
		return bitSet.nextClearBit(1);
	}

	/**
	 * @author ZhouJie
	 * @date 2020年2月1日 下午8:30:11 
	 * @Description: TODO(方法简述) 
	 * @return int 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月1日 下午8:30:11]  
	 * @UpdateRemark:2-原地粗略排序，使得nums[i]=i+1 
	 *
	 */
	public int firstMissingPositive_2(int[] nums) {
		if (nums == null) {
			return 1;
		}
		int k;
		for (int i = 0; i < nums.length; i++) {
			// 交换，让nums[i]=i+1
			while (nums[i] > 0 && nums[i] < nums.length && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
				k = nums[i];
				nums[i] = nums[k - 1];
				nums[k - 1] = k;
			}
		}
		// 遍历，找到第一个nums[i]!=i+1的位置
		for (k = 0; k < nums.length; k++) {
			if (nums[k] != k + 1) {
				break;
			}
		}
		return k + 1;
	}
}
