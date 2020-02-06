package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2019年11月23日 下午11:42:50
 * @Description: 1.两数之和
 * 
	给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	
	你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
	
	示例:
	
	给定 nums = [2, 7, 11, 15], target = 9
	
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/two-sum
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
	思路：map的key-value为nums[i]-i，target-nums[i]检测key，一次遍历完成；
 */
public class LeetCode_0001 {

}

class Solution_0001 {

	/**
	 * @author ZhouJie
	 * @date 2019年12月3日 下午7:53:35 
	 * @Description: TODO(方法简述) 
	 * @return int[] 
	 * @UpdateUser-UpdateDate:[ZhouJie]-[2020年2月3日 上午10:51:46]  
	 * @UpdateRemark:map存放nums[i]-i，target-nums[i]检测key，一次遍历完成；
	 *
	 */
	public int[] twoSum(int[] nums, int target) {
		int length = nums.length;
		if (length < 2) {
			return new int[0];
		}
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		int temp;
		for (int i = 0; i < nums.length; i++) {
			temp = target - nums[i];
			// 校验是否存在并且是不重复的
			if (hashMap.containsKey(temp) && hashMap.get(temp) != i) {
				return new int[] { i, hashMap.get(temp) };
			} else {
				hashMap.put(nums[i], i);
			}
		}
		return new int[0];
	}
}