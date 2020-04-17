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
		// 按照左边界升序排序
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int len = intervals.length;
		int[][] rst = new int[len][2];
		rst[0] = intervals[0];
		int k = 0;
		// 顺序合并
		for (int i = 1; i < len; i++) {
			int x1 = intervals[i][0];
			int y1 = intervals[i][1];
			int y2 = rst[k][1];
			// 若i的左边界小于等于rst最后一个区间的右边界，则可合并，否增新增i至rst
			if (x1 <= y2) {
				rst[k][1] = Math.max(y1, y2);
			} else {
				rst[++k] = intervals[i];
			}
		}
		return Arrays.copyOf(rst, ++k);
	}

	/**
	 * @author: ZhouJie
	 * @date: 2020年3月4日 下午6:45:34 
	 * @param: @param intervals
	 * @param: @return
	 * @return: int[][]
	 * @Description: 2-逐步向后合并后筛选并返回；
	 *
	 */
	public int[][] merge_2(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		int len = intervals.length;
		int count = 0;
		// 每次取i和i之后的区间j对比，若可合并，则i合并到j，i自身设为null标记
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int x1 = intervals[i][0];
				int y1 = intervals[i][1];
				int x2 = intervals[j][0];
				int y2 = intervals[j][1];
				int l = Math.min(x1, x2);
				int r = Math.max(y1, y2);
				// 判断是否可合并：ij区间的最大边界距离若小于等于ij各自区间的跨度和，则说明ij存在相接或重叠，可合并
				if (r - l <= y1 - x1 + y2 - x2) {
					intervals[j][0] = l;
					intervals[j][1] = r;
					// 合并后，i区间null标记
					intervals[i] = null;
					// 统计合并次数，用于判断及结果数组的长度定义
					count++;
					break;
				}
			}
		}
		// 未合并过，直接返回于原数组
		if (count == 0) {
			return intervals;
		} else {
			// 合并过，则新建数组，跳过null，将合并后的数组存放并返回
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