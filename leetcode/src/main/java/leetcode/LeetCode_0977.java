package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2020/10/16 16:52
 * @description: 977. 有序数组的平方
 * <p>
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0977 {
    public static void main(String[] args) {
        int[] A = {-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(Solution_0977.sortedSquares(A)));
    }
}

class Solution_0977 {
    /**
     * @return int[]
     * @author zhoujie
     * @date 2020/10/19 09:25
     * @param: A
     * @description: 若有负数，那么数组的绝对值走势是一个V字形，故从两侧向中间比较计算即可；
     */
    public static int[] sortedSquares(int[] A) {
        int k = A.length;
        int[] rst = new int[k];
        int i = 0;
        int j = k - 1;
        while (k > 0) {
            rst[--k] = (Math.abs(A[i]) > Math.abs(A[j])) ? A[i] * A[i++] : A[j] * A[j--];
        }
        return rst;
    }
}