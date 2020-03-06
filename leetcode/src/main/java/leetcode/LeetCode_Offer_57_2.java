package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhouJie
 * @date 2020年3月6日 下午8:38:27 
 * @Description: 面试题57 - II. 和为s的连续正数序列
 *
	输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
	
	序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
	
	 
	
	示例 1：
	
	输入：target = 9
	输出：[[2,3,4],[4,5]]
	示例 2：
	
	输入：target = 15
	输出：[[1,2,3,4,5],[4,5,6],[7,8]]
	 
	
	限制：
	
	1 <= target <= 10^5
	
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-首先确定可能连续的数长度在2~target/2+1之间，然后对长度分奇偶数处理；
		2-击败双100%解法（来自LeetCode评论区）；
	*/
public class LeetCode_Offer_57_2 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午8:40:53 
	 * @param: @param target
	 * @param: @return
	 * @return: int[][]
	 * @Description: 1-自己的解法，总结规律...
	 *
	 */
	public int[][] findContinuousSequence_1(int target) {
		List<int[]> list = new ArrayList<int[]>();
		// 可能的连续长度在2~target / 2 + 1之间
		for (int i = target / 2 + 1; i > 1; i--) {
			// 可能的起始值
			int start = target / i - i / 2;
			// 若长度为偶数，则必满足target % i == i / 2，此时起始值start+1
			if (i % 2 == 0 && ++start > 0) {
				if (target % i == i / 2) {
					int[] t = new int[i];
					for (int j = 0; j < i; j++) {
						t[j] = start++;
					}
					list.add(t);
				}
				// 若长度为奇数，则必满足target % i == 0，此时start无需处理
			} else if (target % i == 0 && start > 0) {
				int[] t = new int[i];
				for (int j = 0; j < i; j++) {
					t[j] = start++;
				}
				list.add(t);
			}
		}
		return list.toArray(new int[0][]);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月6日 下午8:45:13 
	 * @param: @param target
	 * @param: @return
	 * @return: int[][]
	 * @Description: 2-击败双100%解法（来自LeetCode评论区）；
	 * 
	 * 如果两个连续的数之和等于target，那么相差为1， (target - 1) % 2 == 0， 
	 * 且数组一定是从 (target - 1) / 2开始的，数组的元素就是2个；
	 * 如果是3个连续的数组，那么三个数之间相差为1、2，(target - 1 - 2) % 3 == 0，
	 * 且数组一定是从 (target - 1 - 2) / 3开始的，数组元素是3个，
	 * 依次类推，但是注意target必须是大于0的数，且res需要倒序。
	 *
	 */
	public int[][] findContinuousSequence_2(int target) {
		List<int[]> list = new ArrayList<int[]>();
		int i = 1;
		while (target > 0) {
			target -= i++;
			if (target > 0 && target % i == 0) {
				int[] t = new int[i];
				int start = target / i;
				for (int j = 0; j < i; j++) {
					t[j] = start++;
				}
				list.add(t);
			}
		}
		Collections.reverse(list);
		return list.toArray(new int[0][]);
	}
}
