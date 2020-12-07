package leetcode;

/**
 * @author zhoujie
 * @date 2020/12/7 上午9:59
 * @description: 861. 翻转矩阵后的得分
 * <p>
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * <p>
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * <p>
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * <p>
 * 返回尽可能高的分数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-after-flipping-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0861 {
}

class Solution_0861 {
    /**
     * @return int
     * @author zhoujie
     * @date 2020/12/7 上午9:59
     * @param: A
     * @description: 首列必须全为1才能最大，后续的列统计根据首列的原始状态进行计数并累加和
     */
    public int matrixScore(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        // 首列必全为1才最大，加上权统计和
        int sum = m * (1 << (n - 1));
        for (int i = 1; i < n; i++) {
            int h = 0;
            for (int[] ints : A) {
                // 后续的列值根据其对应的行首值确定
                h += 1 - (ints[0] ^ ints[i]);
            }
            // 累加当前列加权后的值
            sum += Math.max(h, m - h) * (1 << (n - 1 - i));
        }
        return sum;
    }
}