package leetcode;

/**
 * @author ZhouJie
 * @date 2020年3月7日 下午4:40:54 
 * @Description: 面试题59 - I. 滑动窗口的最大值
 *
	给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
	
	示例:
	
	输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
	输出: [3,3,5,5,6,7] 
	解释: 
	
	  滑动窗口的位置                最大值
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	 
	
	提示：
	
	你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
	
	注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-滑动窗口；
 */
public class LeetCode_Offer_59_1 {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int len = nums.length;
		if (len == 0) {
			return new int[0];
		}
		int[] rst = new int[len - k + 1];
		int h = -1, t = 0;
		int max = nums[0];
		for (int i = 0; i < len - k + 1; i++) {
			// 判断上次的最大值是不是左边界，若不是，则直接与右边界新增值比较，否则进行依次窗口扫描
			if (h > i && h < i + k) {
				if (nums[i + k - 1] > max) {
					max = nums[i + k - 1];
					h = i + k - 1;
				}
			} else {
				max = nums[i];
				for (int j = i; j < k + i; j++) {
					if (nums[j] >= max && h < j) {
						h = j;
						max = nums[j];
					}
				}
			}
			rst[t++] = max;
		}
		return rst;
	}
}
