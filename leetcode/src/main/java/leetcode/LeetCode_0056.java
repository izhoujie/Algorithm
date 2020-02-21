package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ZhouJie
 * @date 2020年2月21日 下午5:57:22 
 * @Description: 56. 合并区间
 *
	给出一个区间的集合，请合并所有重叠的区间。
	
	示例 1:
	
	输入: [[1,3],[2,6],[8,10],[15,18]]
	输出: [[1,6],[8,10],[15,18]]
	解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
	示例 2:
	
	输入: [[1,4],[4,5]]
	输出: [[1,5]]
	解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
	
	来源：力扣（LeetCode）
	链接：https://leetcode-cn.com/problems/merge-intervals
	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	思路：1-对左边界排序后顺序判断合并区间；
		2-优化处理，省去排序步骤；
 */
public class LeetCode_0056 {

}

class Solution_0056 {
	/**
	 * @author: ZhouJie
	 * @date: 2020年2月21日 下午6:40:28 
	 * @param: @param <T>
	 * @param: @param intervals
	 * @param: @return
	 * @return: int[][]
	 * @Description: 1-
	 *
	 */
	public int[][] merge_1(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int len = intervals.length;
		int[][] rst = new int[len][2];
		rst[0] = intervals[0];
		int k = 1;
		// [[2,3],[4,5],[6,7],[8,9],[1,10]]
		for (int i = 1; i < len; i++) {
			boolean f = true;
			for (int j = 0; j < k; j++) {
				int x1 = intervals[i][0];
				int y1 = intervals[i][1];
				int x2 = rst[j][0];
				int y2 = rst[j][1];
				int l = Math.min(x1, x2);
				int r = Math.max(y1, y2);
				if (r - l <= y1 - x1 + y2 - x2) {
					rst[j][0] = l;
					rst[j][1] = r;
					f = false;
					break;
				}
			}
			if (f) {
				rst[k] = intervals[i];
				k++;
			}
		}
		return Arrays.copyOf(rst, k);
	}

	public int[][] merge_2(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		int len = intervals.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int x1 = intervals[i][0];
				int y1 = intervals[i][1];
				int x2 = intervals[j][0];
				int y2 = intervals[j][1];
				int l = Math.min(x1, x2);
				int r = Math.max(y1, y2);
				if (r - l <= y1 - x1 + y2 - x2) {
					intervals[j][0] = l;
					intervals[j][1] = r;
					intervals[i] = null;
					count++;
					break;
				}
			}
		}
		if (count == 0) {
			return intervals;
		} else {
			int[][] rst = new int[len - count][];
			int k = 0;
			for (int[] t : intervals) {
				if (t != null) {
					rst[k++] = t;
				}
			}
			return rst;

		}
	}
}