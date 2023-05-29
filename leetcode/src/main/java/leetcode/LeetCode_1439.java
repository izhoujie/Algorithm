package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zhoujie
 * @date 2023/5/29 14:23
 * @description: 1439. 有序矩阵中的第 k 个最小数组和
 * <p>
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * <p>
 * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：mat = [[1,3,11],[2,4,6]], k = 5
 * 输出：7
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 * 示例 2：
 * <p>
 * 输入：mat = [[1,3,11],[2,4,6]], k = 9
 * 输出：17
 * 示例 3：
 * <p>
 * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * 输出：9
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 * 示例 4：
 * <p>
 * 输入：mat = [[1,1,10],[2,2,9]], k = 7
 * 输出：12
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] 是一个非递减数组
 */
public class LeetCode_1439 {

    /**
     * @return int
     * @author zhoujie
     * @date 2023/5/29 14:24
     * @param: mat
     * @param: k
     * @description: 暴力排序，使用排序逆序的优先队列，统计所有的和
     */
    public int kthSmallest(int[][] mat, int k) {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.add(0);

        for (int[] arr : mat) {
            list.clear();
            list.addAll(queue);
            queue.clear();
            for (Integer sum : list) {
                for (int v : arr) {
                    queue.add(sum + v);
                }
            }
            // 当n的长度大于k时，n-k的部分已经没有计算比较的必要，有剪枝效果
            while (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2023/5/29 16:12
     * @param: mat
     * @param: k
     * @description: 二分+DFS剪枝，待研究
     */
    public int kthSmallest_(int[][] mat, int k) {

        return 0;
    }

}
