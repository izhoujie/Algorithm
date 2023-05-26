package codewars;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhoujie
 * @date 2023/5/26 12:32
 * @description: Sum of Intervals
 * <p>
 * Intervals
 * Intervals are represented by a pair of integers in the form of an array. The first value of the interval will always be less than the second value. Interval example: [1, 5] is an interval from 1 to 5. The length of this interval is 4.
 * <p>
 * Overlapping Intervals
 * List containing overlapping intervals:
 * <p>
 * [
 * [1, 4],
 * [7, 10],
 * [3, 5]
 * ]
 * The sum of the lengths of these intervals is 7. Since [1, 4] and [3, 5] overlap, we can treat the interval as [1, 5], which has a length of 4.
 * <p>
 * Examples:
 * sumIntervals( [
 * [1, 2],
 * [6, 10],
 * [11, 15]
 * ] ) => 9
 * <p>
 * sumIntervals( [
 * [1, 4],
 * [7, 10],
 * [3, 5]
 * ] ) => 7
 * <p>
 * sumIntervals( [
 * [1, 5],
 * [10, 20],
 * [1, 6],
 * [16, 19],
 * [5, 11]
 * ] ) => 19
 * <p>
 * sumIntervals( [
 * [0, 20],
 * [-100000000, 10],
 * [30, 40]
 * ] ) => 100000030
 * Tests with large intervals
 * Your algorithm should be able to handle large intervals. All tested intervals are subsets of the range [-1000000000, 1000000000].
 */
public class KUY4_Sum_of_Intervals {
    /**
     * @return int
     * @author zhoujie
     * @date 2023/5/26 12:33
     * @param: intervals
     * @description: 先对数组排序，然后逐次比较相邻数组的右边界，计算并累加相隔距离
     */
    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int count = 0;
        int preEnd = intervals[0][0];
        for (int[] arr : intervals) {
            int start = arr[0];
            int end = arr[1];
            if (end > preEnd) {
                count += end - Math.max(preEnd, start);
                preEnd = end;
            }
        }
        return count;
    }

}
