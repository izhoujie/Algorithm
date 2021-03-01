package leetcode;

/**
 * @author zhoujie
 * @date 2021/3/1 上午11:55
 * @description: 896. 单调数列
 * <p>
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：[1,1,1]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0896 {
}

class Solution_0896 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/3/1 上午11:56
     * @param: A
     * @description: 一次遍历校验
     */
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len < 3) {
            return true;
        }
        int val = A[len - 1] - A[0];
        if (val > 0) {
            for (int i = 1; i < len; i++) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < len; i++) {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}