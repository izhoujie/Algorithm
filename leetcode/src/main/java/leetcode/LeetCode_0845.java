package leetcode;

/**
 * @author zhoujie
 * @date 2020/10/27 15:34
 * @description: 845. 数组中的最长山脉
 * <p>
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * <p>
 * 如果不含有 “山脉” 则返回 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * <p>
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0845 {
}

class Solution_0845 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/10/27 15:34
     * @param: A
     * @description: 数组一次遍历中升序和降序两段验证
     */
    public int longestMountain(int[] A) {
        int max = 0;
        int count = 0;
        int i = 1;
        int len = A.length;
        // 升序和降序是否有有效统计标识
        boolean left = true;
        boolean right = true;
        // 遍历校验
        while (i < len) {
            // 升序校验
            while (i < len && A[i] > A[i - 1]) {
                i++;
                count++;
                left = false;
            }
            // 如无升序段，跳出当前循环
            if (left) {
                i++;
                continue;
            }
            // 降序校验
            while (i < len && A[i] < A[i - 1]) {
                i++;
                count++;
                right = false;
            }
            // 如有降序段，则找到了一个山脉数组，统计并记录其长度
            if (!right) {
                count++;
                max = Math.max(max, count);
            }
            // 重置标志位
            count = 0;
            left = true;
            right = true;
        }
        return max;
    }
}