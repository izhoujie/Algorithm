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
	public int subarraySum_1(int[] nums, int k) {
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

	/**
	 * @author: ZhouJie
	 * @date: 2020年5月15日 下午10:22:40 
	 * @param: @param nums
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 2-使用辅助数组替代map保存中间值；
	 *
	 */
	public int subarraySum_2(int[] nums, int k) {
		int min = 0, max = 0, sum = 0, count = 0, key;
		// 求最大值和最小值，确定所有连续和的范围
		for (int val : nums) {
			sum += val;
			max = Math.max(max, sum);
			min = Math.min(min, sum);
		}
		// 用以记录连续和与min差的数组
		int[] map = new int[max - min + 1];
		sum = 0;
		for (int val : nums) {
			sum += val;
			// 若连续和等于k直接记录
			if (sum == k) {
				count++;
			}
			// 在map中寻找其他连续和为sum-k-min的数量
			// 解析：首先sum-min肯定存在，但是现在要多个k值，于是尝试多减去k，看剩余值仍否在[min,max]范围内，若在说明存在这样的连续子数组
			key = sum - k;
			if (key >= min && key <= max) {
				count += map[key - min];
			}
			// 记录sum-min的数量
			map[sum - min]++;
		}
		return count;
	}
}