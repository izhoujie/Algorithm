package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2019年11月24日 上午12:16:23 
 * @Description: 15.三数之和
 * 
	给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
	
	注意：答案中不可以包含重复的三元组。
	
	例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
	
	满足要求的三元组集合为：
	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/3sum
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：先排序，[外循环]顺序取数且-/若与前一个数相等则顺延取数/若该数大于0则结束-该位置记为i，[内循环]接着从i+1和len-1左右两侧夹逼作和0校验，
	之后仍然需要作相邻值是否相等校验继续内循环；
 */
public class LeetCode_0015 {

}

class Solution_0015 {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		int len = 0;
		if (nums == null || (len = nums.length) < 3) {
			return resultList;
		}
		// 递增排序
		Arrays.sort(nums);
		for (int i = 0; i < len; i++) {
			// 和为0则第一个数必须不大于0
			if (nums[i] > 0) {
				break;
			}
			if (i == 0 || (nums[i - 1] != nums[i])) {
				int left = i + 1, right = len - 1;
				// 左右两侧取第2个，第3个数作校验
				while (left < right) {
					int check = nums[left] + nums[right] + nums[i];
					if (check == 0) {
						resultList.add(Arrays.asList(nums[left], nums[right], nums[i]));
						// 左右向中找到第一个不等于当前值的位置
						while (nums[right] == nums[--right] && left < right)
							;
						while (nums[left] == nums[++left] && left < right)
							;
					} else if (check > 0) {
						// 大于说明需要减小值，往左移动
						while (nums[right] == nums[--right] && left < right)
							;
					} else {
						// 小于说明需要增大值，往右移动
						while (nums[left] == nums[++left] && left < right)
							;
					}
				}
			}
		}
		return resultList;
	}
}