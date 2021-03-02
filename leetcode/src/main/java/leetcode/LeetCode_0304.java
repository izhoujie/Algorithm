package leetcode;

/**
 * @author zhoujie
 * @date 2021/3/2 下午1:45
 * @description: 304. 二维区域和检索 - 矩阵不可变
 * <p>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * <p>
 * <p>
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0304 {
}

/**
 * @author zhoujie
 * @date 2021/3/2 下午2:22
 * @return
 * @description: 直接计算，重复冗余计算太多，非常耗时
 */
class NumMatrix_1 {
    private int[][] array;

    public NumMatrix_1(int[][] matrix) {
        this.array = matrix;

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += array[i][j];
            }
        }
        return sum;
    }
}

/**
 * @author zhoujie
 * @date 2021/3/2 下午2:22
 * @return
 * @description: 动态规划/dp，初始化时计算以为[i，j]为右下角[0][0]为左上角的二维矩阵前缀和，通过矩阵的拼块计算任意子矩阵的范围和
 */
class NumMatrix_2 {
    private int[][] array;

    public NumMatrix_2(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            this.array = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 当前点的前缀和 = 前一个点的前缀和 + 上一个点的前缀和 - 斜上方相邻点的前缀和 + 当前点的值
                    array[i + 1][j + 1] = array[i + 1][j] + array[i][j + 1] - array[i][j] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 目标矩形的范围和 = 右下角点的前缀和 - 左侧子矩阵和 - 上侧子矩阵和 + 斜上方相邻点的前缀和
        return array[row2 + 1][col2 + 1] - array[row1][col2 + 1] - array[row2 + 1][col1] + array[row1][col1];
    }
}


/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */