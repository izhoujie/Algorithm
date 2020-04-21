package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author ZhouJie
 * @date 2020年4月21日 下午3:54:13 
 * @Description: 1248. 统计「优美子数组」
 *
	给你一个整数数组 nums 和一个整数 k。
	
	如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
	
	请返回这个数组中「优美子数组」的数目。
	
	 
	
	示例 1：
	
	输入：nums = [1,1,2,1,1], k = 3
	输出：2
	解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
	示例 2：
	
	输入：nums = [2,4,6], k = 1
	输出：0
	解释：数列中不包含任何奇数，所以不存在优美子数组。
	示例 3：
	
	输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
	输出：16
	 
	
	提示：
	
	1 <= nums.length <= 50000
	1 <= nums[i] <= 10^5
	1 <= k <= nums.length
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1248 {

}

class Solution_1248 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年4月21日 下午4:47:43 
	 * @param: @param nums
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 1-每找到一个连续k，再左右贪心；
	 *
	 */
	public int numberOfSubarrays_1(int[] nums, int k) {
		int all = 0;
		if (k > nums.length) {
			return all;
		}
		Deque<Integer> deque = new ArrayDeque<Integer>();
		int len = nums.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if ((nums[i] & 1) == 1) {
				deque.offer(i);
				count++;
				if (count == k) {
					int left = deque.peekFirst();
					int right = deque.peekLast();
					int l = 0, r = 0;
					while (--left > -1 && (nums[left] & 1) == 0) {
						l++;
					}
					while (++right < len && (nums[right] & 1) == 0) {
						r++;
					}
					all += l + r + l * r + 1;
					deque.pollFirst();
					count--;
				}
			}
		}
		return all;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月21日 下午4:48:28 
	 * @param: @param nums
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 2-对1的优化，可以保留贪心时的扩展供下次使用；
	 *
	 */
	public int numberOfSubarrays_2(int[] nums, int k) {
		int all = 0;
		if (k > nums.length) {
			return all;
		}
		// 记录奇数的索引位置
		Deque<Integer> deque = new ArrayDeque<Integer>();
		// 记录奇数对应索引左侧连续偶数数量
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int len = nums.length;
		// index从-1开始，因为要记录奇数之间的偶数，所以从-1开始，可认为-1位置是一个奇数
		int count = 0, index = -1;
		for (int i = 0; i < len; i++) {
			if ((nums[i] & 1) == 1) {
				// 奇数入队
				deque.offer(i);
				// 记录当前奇数索引左侧偶数数量
				map.put(i, i - index - 1);
				// 更新奇数标记
				index = i;
				count++;
				// 因为要记录奇数间隔，所以需要多找第k+1个奇数位置并记录其左侧连续偶数数量，然后再计算
				if (count == k + 1) {
					// queue头部奇数所在索引左侧连续偶数数量
					int l = map.get(deque.poll());
					// 当前第count+1个奇数左侧的连续偶数数量
					int r = map.get(i);
					// 连续字数组计算：左侧连续偶数数量+右侧连续偶数数量+左右成乘积+不添加左右连续-本体
					all += l + r + l * r + 1;
					count--;
				}
			}
		}
		// 右边界判断，若有k个奇数但末尾有若干连续偶数时或者最后一个数恰是奇数时
		if (count == k) {
			// 此时队列有k个奇数，只需要获得头部奇数所在位置左侧偶数数量
			int l = map.get(deque.poll());
			// 最后一个数是奇数
			if (index == len - 1) {
				int r = 0;
				all += l + r + l * r + 1;
				// 最后一个非奇数
			} else {
				int r = len - index - 1;
				all += l + r + l * r + 1;
			}
		}
		return all;
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年4月21日 下午6:48:44 
	 * @param: @param nums
	 * @param: @param k
	 * @param: @return
	 * @return: int
	 * @Description: 3-前缀和/0压缩
	 *
	 */
	public int numberOfSubarrays_3(int[] nums, int k) {
		int all = 0;
		if (k > nums.length) {
			return all;
		}
		// nums奇数的数量，k个奇数本身算一个子数组，共需要存nums.length + 1种情况
		int[] preArr = new int[nums.length + 1];
		// 0位置是否奇数都要算一个子数组
		preArr[0] = 1;
		int sum = 0;
		for (int i : nums) {
			// 统计奇数数量，二进制末尾位置1为奇数，0为偶数
			sum += (i & 1);
			// 第sum个奇数左侧的非奇数数量+1，奇数本身也算作一个子数组
			preArr[sum]++;
			if (sum >= k) {
				// 遇到k个之后的奇数，k个奇数左侧的子数组数为preArr[sum - k]，k个奇数右侧每匹配到一个非奇数就多了preArr[sum - k]种子数组
				all += preArr[sum - k];
			}
		}
		return all;
	}
}
