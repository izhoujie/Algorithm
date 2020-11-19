package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author zhoujie
 * @date 2020/11/18 09:19
 * @description: 973. 最接近原点的 K 个点
 * <p>
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0973 {
}

class Solution_0973 {
    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/18 09:20
     * @param: points
     * @param: K
     * @description: TOPK问题，使用优先队列处理，时间复杂度为：NlogK，较好
     */
    public int[][] kClosest_1(int[][] points, int K) {
        // 优先队列，以点的平方和比较排序，降序排列，首个节点为最大
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]));
        // 先入K个点
        for (int i = 0; i < K; i++) {
            queue.offer(points[i]);
        }
        // 继续比较并判断是否放入后续点
        for (int i = K; i < points.length; i++) {
            int next = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            int[] peek = queue.peek();
            int top = peek[0] * peek[0] + peek[1] * peek[1];
            if (next < top) {
                queue.poll();
                queue.offer(points[i]);
            }
        }
        // 组装结果
        int[][] rst = new int[K][];
        for (int i = 0; i < K; i++) {
            rst[i] = queue.poll();
        }
        return rst;
    }

    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/18 10:15
     * @param: points
     * @param: K
     * @description: 先快排，再取前K个返回，时间复杂度为：NlogN，较低
     */
    public int[][] kClosest_2(int[][] points, int K) {
        return Arrays.stream(points).sorted(Comparator.comparingInt(p -> p[0] * p[0] + p[1] * p[1])).limit(K).toArray(int[][]::new);
    }

    /**
     * @return int[][]
     * @author zhoujie
     * @date 2020/11/18 10:29
     * @param: points
     * @param: K
     * @description: 快速排序之快速选择，期望时间复杂度：N，最差时为N²，较好
     */
    Random random = new Random();

    public int[][] kClosest_3(int[][] points, int K) {
        // 局部快排，找到第K个位置保证前K个数是最小的K个数
        randomSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void randomSelect(int[][] points, int left, int right, int k) {
        // 中轴值的选择，使用了随机取值
        int mid = left + random.nextInt(right - left + 1);
        int midValue = points[mid][0] * points[mid][0] + points[mid][1] * points[mid][1];
        swap(points, right, mid);
        int i = left - 1;
        // 以midValue为中值分割排序
        for (int j = left; j < right; j++) {
            int nowValue = points[j][0] * points[j][0] + points[j][1] * points[j][1];
            if (nowValue <= midValue) {
                i++;
                swap(points, i, j);
            }
        }
        i++;
        swap(points, i, right);
        // 截止到i的位置，i的左侧都是小于points[i]的点，右侧是大于的点
        // 若k小于i，则此时还需要在[left,i-1]中继续排序，直至恰好找到k个最小数
        // 若k大于i，则此时还需要在[i+1,right]中继续排序，再找到k-(i-left+1)个数，即凑够k个最小数
        // 若此时i==k，则排序结束，已经找到了左侧的最小k个数
        if (k < i - left + 1) {
            randomSelect(points, left, i - 1, k);
        } else if (k > i - left + 1) {
            randomSelect(points, i + 1, right, k - (i - left + 1));
        }
    }

    private void swap(int[][] points, int m, int n) {
        int[] temp = points[m];
        points[m] = points[n];
        points[n] = temp;
    }

}