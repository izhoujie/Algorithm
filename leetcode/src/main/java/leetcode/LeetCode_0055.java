package leetcode;

/**
 * @author ZhouJie
 * @date 2020年2月20日 下午7:50:49 
 * @Description: 55. 跳跃游戏
 *
	给定一个非负整数数组，你最初位于数组的第一个位置。
	
	数组中的每个元素代表你在该位置可以跳跃的最大长度。
	
	判断你是否能够到达最后一个位置。
	
	示例 1:
	
	输入: [2,3,1,1,4]
	输出: true
	解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
	示例 2:
	
	输入: [3,2,1,0,4]
	输出: false
	解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/jump-game
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-跟No.45思路一样，不过换为校验是否能跳至末尾；
		2-倒推，一次遍历；
 */
public class LeetCode_0055 {

}

class Solution_0055 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月20日 下午8:19:05 
	 * @param: @param nums
	 * @param: @return
	 * @return: boolean
	 * @Description: 1-最开始的思路写的，优化后为方法3--canJump_3
	 *
	 */
	public boolean canJump_1(int[] nums) {
		if (nums == null || nums.length < 2) {
			return true;
		}
		int len = nums.length;
		for (int i = 0; i < len;) {
			if (nums[i] >= len - 1 - i) {
				return true;
			}
			int k = i + 1, subStemp = 0, temp;
			for (int j = i + 1; j < i + nums[i] + 1; j++) {
				temp = j + nums[j];
				if (temp > subStemp) {
					subStemp = temp;
					k = j;
				} else if (temp == subStemp && nums[j] >= nums[k]) {
					k = j;
				}
			}
			if (subStemp == 0) {
				return false;
			}
			i = k;
		}
		return false;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年2月20日 下午8:19:17 
	 * @param: @param nums
	 * @param: @return
	 * @return: boolean
	 * @Description: 2-从后向前检测
	 *
	 */
	public boolean canJump_2(int[] nums) {
		if (nums == null || nums.length < 2) {
			return true;
		}
		int check = nums.length - 1;
		for (int i = check; i > -1; i--) {
			// 若该位置可达，则更新为新的可达位置，即把i值往最小收敛，为0即为可达
			if (i + nums[i] >= check) {
				check = i;
			}
		}
		return check == 0;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月17日 下午8:25:56 
	 * @param: @param nums
	 * @param: @return
	 * @return: boolean
	 * @Description: 3-对方法1的优化，其实方法1的内循环就是贪心思想，但是这个处理可以提出来并到外循环，避免二次for
	 *
	 */
	public boolean canJump_3(int[] nums) {
		if (nums == null || nums.length < 2) {
			return true;
		}
		int len = nums.length;
		// 可到达的最远位置
		int end = 0;
		for (int i = 0; i < len; i++) {
			// 若当前位置可达
			if (i <= end) {
				// 更新最远可达位置，若可达终点直接return
				end = Math.max(end, i + nums[i]);
				if (end >= len - 1) {
					return true;
				}
			} else {
				return false;
			}
		}
		return false;
	}
}