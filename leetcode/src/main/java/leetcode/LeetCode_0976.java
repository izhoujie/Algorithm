package leetcode;

import java.util.Arrays;

/**
 * @author zhoujie
 * @date 2020/11/30 下午3:12
 * @description: 976. 三角形的最大周长
 * <p>
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * <p>
 * 如果不能形成任何面积不为零的三角形，返回 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,2]
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：[1,2,1]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：[3,2,3,4]
 * 输出：10
 * 示例 4：
 * <p>
 * 输入：[3,6,2,3]
 * 输出：8
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-perimeter-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0976 {
}

class Solution_0976 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/11/30 下午3:13
     * @param: A
     * @description: 先排序，然后从大到小检测连续的三个数是否能构成三角形
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            int a = A[i];
            int b = A[i - 1];
            int c = A[i - 2];
            if (a - b < c) {
                return a + b + c;
            }
        }
        return 0;
    }
}