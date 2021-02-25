package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2021/2/22 下午2:46
 * @description: 766. 托普利茨矩阵
 * <p>
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0766 {
}

class Solution_0766 {
    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/2/22 下午2:47
     * @param: matrix
     * @description: BFS，按对角线逐个线校验
     */
    public boolean isToeplitzMatrix_1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean f = m > n;
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(f ? ((m - 1) * m) : (m - 1));
        int i, j, number;
        while (!deque.isEmpty()) {
            int size = deque.size();
            int val = deque.peekLast();
            if (f) {
                i = val / m;
                j = val % m;
            } else {
                j = val / n;
                i = val % n;
            }
            number = matrix[i][j];
            if (i > 0) {
                deque.offerFirst(f ? (i - 1) * m + j : j * n + i - 1);
            }
            while (size-- > 0) {
                val = deque.pollLast();
                if (f) {
                    i = val / m;
                    j = val % m;
                } else {
                    j = val / n;
                    i = val % n;
                }
                if (matrix[i][j] == number) {
                    if (j < n - 1) {
                        deque.offerFirst(f ? i * m + (j + 1) : (j + 1) * n + i);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return boolean
     * @author zhoujie
     * @date 2021/2/25 上午10:47
     * @param: matrix
     * @description: 遍历，从中斜线分两部分遍历校验
     */
    public boolean isToeplitzMatrix_2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int k = 0; k < m; k++) {
            int j = 0, i = k;
            int val = matrix[i][j];
            while (++i < m && ++j < n) {
                if (matrix[i][j] != val) {
                    return false;
                }
            }
        }
        for (int k = 1; k < n; k++) {
            int i = 0, j = k;
            int val = matrix[i][j];
            while (++i < m && ++j < n) {
                if (matrix[i][j] != val) {
                    return false;
                }
            }
        }
        return true;
    }

}