package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujie
 * @date 2020/11/4 09:26
 * @description: 57. 插入区间
 * <p>
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *  
 * <p>
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0057 {
}

class Solution_0057 {
    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/4 09:27
     * @param: intervals
     * @param: newInterval
     * @description: 一次扫描中记录三个要素：1.新区间是要合并还是独立；2.若合并，合并了哪些区间及最终新区间的起始；3.若独立，新区间插入的位置；
     */
    public int[][] insert_1(int[][] intervals, int[] newInterval) {
        // 若合并，记录合并区间的起始位置
        int start = -1;
        int end = -1;
        // 若跳出，记录跳出位置,初始化为原始数组总长度
        int stop = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            int[] ints = intervals[i];
            // 无法合并，跳出并记录位置
            if (newInterval[1] < ints[0]) {
                stop = i;
                break;
                // 可合并，合并并记录合并起始及终止位置
            } else if (newInterval[0] <= ints[1]) {
                newInterval[0] = ints[0] = Math.min(newInterval[0], ints[0]);
                newInterval[1] = ints[1] = Math.max(newInterval[1], ints[1]);
                if (start == -1) {
                    start = i;
                    end = i;
                } else {
                    end++;
                }
            }
        }
        int[][] rst;
        // 若从未合并，则新区间是独立区间，分两段复制原始数组到新数组
        if (start == -1) {
            rst = new int[intervals.length + 1][];
            System.arraycopy(intervals, 0, rst, 0, stop);
            // 若新区间在末尾则无第二段复制
            if (stop != intervals.length) {
                System.arraycopy(intervals, stop, rst, stop + 1, intervals.length - stop);
            }
            rst[stop] = newInterval;
            return rst;
        } else {
            // 若仅合并了一次，直接返回修改过的原数组即可，否则需要分两段复制以去掉被合并的部分返回新数组
            if (start == end) {
                return intervals;
            } else {
                rst = new int[intervals.length - (end - start)][];
                System.arraycopy(intervals, 0, rst, 0, start);
                System.arraycopy(intervals, end, rst, start, intervals.length - end);
                return rst;
            }
        }
    }

    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/4 15:08
     * @param: intervals
     * @param: newInterval
     * @description: 使用list直接保存处理后的数组最后再还原为数组返回
     */
    public int[][] insert_2(int[][] intervals, int[] newInterval) {
        // 特例判断
        if (intervals == null || intervals.length == 0) {
            return new int[][]{{newInterval[0], newInterval[1]}};
        }
        // 使用list直接存储处理后的值，比方法1更便捷
        List<int[]> list = new ArrayList<>();
        int len = intervals.length;
        int i = 0;
        // 小于新区间的直接入list
        while (i < len && newInterval[0] > intervals[i][1]) {
            list.add(intervals[i++]);
        }
        // 处理合并情况
        if (i < len) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            // 不断向右侧合并
            while (i < len && intervals[i][0] <= newInterval[1]) {
                newInterval[1] = Math.max(intervals[i++][1], newInterval[1]);
            }
        }
        // 合并后的区间入list
        list.add(newInterval);
        // 剩余区间入list
        while (i < len) {
            list.add(intervals[i++]);
        }
        // 转为数组返回
        int[][] rst = new int[list.size()][];
        for (int j = 0; j < list.size(); j++) {
            rst[j] = list.get(j);
        }
        return rst;
    }


}