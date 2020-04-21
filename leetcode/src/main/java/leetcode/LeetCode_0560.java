package leetcode;

import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年4月21日 下午8:47:12 
 * @Description: 560. 和为K的子数组
 *
	给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
	
	示例 1 :
	
	输入:nums = [1,1,1], k = 2
	输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
	说明 :
	
	数组的长度为 [1, 20,000]。
	数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0560 {

}

class Solution_0560 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月21日 下午8:47:49 
	 * @param: @param nums
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 1-map存储前缀和；
	 *
	 */
	public int subarraySum_(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 初始必须存入(0,1)，若不存而数组的第一项就是k，sum-k=0时就找不到0了
		map.put(0, 1);
		int sum = 0, count = 0;
		for (int val : nums) {
			sum += val;
			int key = sum - k;
			// 寻找之前是不是存过sum-k，有就表示找到了一个和为k的片段
			if (map.containsKey(key)) {
				count += map.get(key);
			}
			// 更新和为sum的出现次数
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
}