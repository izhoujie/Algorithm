package leetcode;

import java.util.Arrays;

/**
 * @author ZhouJie
 * @date 2020年1月17日 下午3:25:03 
 * @Description: 31. 下一个排列
 * 
	实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
	
	如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
	
	必须原地修改，只允许使用额外常数空间。
	
	以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/next-permutation
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-首先理解字典序就是数组顺组组成的数的大小，题干有点绕，得先看懂意思；
 */
public class LeetCode_0031 {
	public static void main(String[] args) {
		int test[] = { 5, 4, 7, 5, 3, 2 };
		int test1[] = { 1, 2 };
		int test2[] = { 1, 1 };
		new Solution_0031().nextPermutation(test);
		new Solution_0031().nextPermutation(test1);
		new Solution_0031().nextPermutation(test2);
		System.out.println(Arrays.toString(test));
		System.out.println(Arrays.toString(test1));
		System.out.println(Arrays.toString(test2));
	}

}

class Solution_0031 {
	/**
	 * @author ZhouJie
	 * @date 2020年1月17日 下午6:02:26 
	 * @Description: TODO(方法简述) 
	 * @return void 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年1月17日 下午6:02:26]  
	 * @UpdateRemark:1-自己想法，绕多了；
	 *
	 */
	public void nextPermutation(int[] nums) {
		int len;
		if (nums == null || (len = nums.length) == 0) {
			return;
		}
		int k, newIndex;
		k = newIndex = -1;
		// 倒序尝试找位置
		for (int i = len - 1; i > 0; i--) {
			if (nums[i - 1] < nums[i]) {
				k = i - 1;
				newIndex = i;
				break;
			}
		}
		// 如未找到，则直接升序后返回
		if (k == -1) {
			Arrays.sort(nums);
			return;
		}
		// 找到位置后，再找比k大的最小值
		for (int i = k + 1; i < len; i++) {
			if (nums[i] > nums[k] && nums[i] < nums[newIndex]) {
				newIndex = i;
			}
		}
		// 找到newIndex后与k位置互换，再对k+1到len的部分进行升序排序后返回
		int temp = nums[k];
		nums[k] = nums[newIndex];
		nums[newIndex] = temp;
		Arrays.sort(nums, k + 1, len);
	}
}