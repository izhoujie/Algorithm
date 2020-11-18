package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhoujie
 * @date 2020/11/17 14:25
 * @description: 1030. 距离顺序排列矩阵单元格
 * <p>
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * <p>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * <p>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * <p>
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * <p>
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_1030 {
}

class Solution_1030 {
    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/17 14:26
     * @param: R
     * @param: C
     * @param: r0
     * @param: c0
     * @description: 考虑BFS，从目标点[r0,c0]向四周扩散
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 结果集是R*C个坐标点
        int[][] rst = new int[R * C][2];
        // 是否处理过标识
        boolean[][] f = new boolean[R][C];
        // 四个方向量
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        // 使用双端队列存储数字化后的坐标值
        Deque<Integer> queue = new LinkedList<>();
        // 坐标值的存储技巧，主要思想：用高位存储高值，用低位存储低值；
        // 较长的边用来作为对应坐标的倍乘因子，另一边的倍乘因子为1，其和作为处理后的值，还原时/得到长边坐标，%得到短边坐标
        queue.offerFirst(R > C ? r0 * R + c0 : r0 + c0 * C);
        int k = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int poll = queue.pollLast();
                int x;
                int y;
                // 坐标还原
                if (R > C) {
                    x = poll / R;
                    y = poll % R;
                } else {
                    x = poll % C;
                    y = poll / C;
                }
                if (f[x][y]) {
                    continue;
                }
                // 记录结果坐标
                rst[k++] = new int[]{x, y};
                f[x][y] = true;
                for (int i = 0; i < 4; i++) {
                    int x0 = x + dx[i];
                    int y0 = y + dy[i];
                    // BFS
                    if (x0 >= 0 && x0 < R && y0 >= 0 && y0 < C && !f[x0][y0]) {
                        queue.offerFirst(R > C ? x0 * R + y0 : x0 + y0 * C);
                    }
                }
            }
        }
        return rst;
    }
}