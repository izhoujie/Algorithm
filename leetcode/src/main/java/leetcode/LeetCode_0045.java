package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月2日 下午7:47:38 
 * @Description: 45. 跳跃游戏 II
 *
	给定一个非负整数数组，你最初位于数组的第一个位置。
	
	数组中的每个元素代表你在该位置可以跳跃的最大长度。
	
	你的目标是使用最少的跳跃次数到达数组的最后一个位置。
	
	示例:
	
	输入: [2,3,1,1,4]
	输出: 2
	解释: 跳到最后一个位置的最小跳跃数是 2。
	     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
	说明:
	
	假设你总是可以到达数组的最后一个位置。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/jump-game-ii
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-贪心算法，每次计算寻找下一次可跳位置中的二次可跳最远位置；
 */
public class LeetCode_0045 {

}

class Solution_0045 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年5月4日 下午11:18:38 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 1-贪心算法，未优化；
	 *
	 */
	public int jump_1(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < nums.length;) {
			// 若当前位置已能跳至末位，直接+1返回
			if (nums[i] >= nums.length - i - 1) {
				return ++count;
			}
			int k = i + 1, subStep = 0, temp;
			// 不能直接跳至末位时，寻找二次跳能跳最远距离的下标，相同时取靠后下标
			for (int j = i + 1; j < i + nums[i] + 1; j++) {
				temp = j + nums[j];
				if (temp > subStep) {
					subStep = temp;
					k = j;
				} else if (temp == subStep && nums[j] >= nums[k]) {
					k = j;
				}
			}
			// 取得二次跳的最佳位置，次数+1，i定位至二次跳的k处
			count++;
			i = k;
		}
		return count;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月4日 下午11:19:27 
	 * @param: @param nums
	 * @param: @return
	 * @return: int
	 * @Description: 2-贪心算法，优化后；
	 *
	 */
	public int jump_2(int[] nums) {
		int len = nums.length - 1, steps = 0, subEnd = 0, maxPosition = 0;
		// 只需要遍历到nums.length - 1的位置；
		for (int i = 0; i < len; i++) {
			// 当前i所能到达的最远处
			maxPosition = Math.max(maxPosition, i + nums[i]);
			// 如果当前位置已能到达尾部则返回；
			if (maxPosition >= len) {
				return ++steps;
			}
			// 计步的关键，每当i到达nums[i]的位置时完成一次贪心模式；
			// 例子：nums[0]=3，则首次算一步，更新subEnd=3，待遍历到i=3时完成nums[0]的一次贪心模式计算；
			if (i == subEnd) {
				subEnd = maxPosition;
				steps++;
			}
		}
		return steps;
	}
}
