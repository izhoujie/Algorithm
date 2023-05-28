package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhoujie
 * @date 2023/5/28 18:41
 * @description: 1091. 二进制矩阵中的最短路径
 * <p>
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * <p>
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * <p>
 * 路径途经的所有单元格的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 */
public class LeetCode_1091 {
    /**
     * @return int
     * @author zhoujie
     * @date 2023/5/28 18:42
     * @param: grid
     * @description: BFS
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (n == 1) {
            return grid[0][0] == 0 ? 1 : -1;
        }
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int i = 0; i < 8; i++) {
                int px = p[0] + x[i];
                int py = p[1] + y[i];
                if (px >= 0 && px < n && py >= 0 && py < n && grid[px][py] == 0) {
                    if (px == n - 1 && py == n - 1) {
                        return p[2] + 1;
                    }
                    grid[px][py] = 1;
                    queue.offer(new int[]{px, py, p[2] + 1});
                }
            }
        }
        return -1;
    }
}
