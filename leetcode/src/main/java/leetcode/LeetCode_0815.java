package leetcode;

import java.util.*;

/**
 * @author zhoujie
 * @date 2021/6/28 上午10:42
 * @description: 815. 公交路线
 * <p>
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_0815 {
}

class Solution_0815 {
    public static final ArrayList<Object> OBJECTS = new ArrayList<>();

    /**
     * @return int
     * @author zhoujie
     * @date 2021/6/28 上午10:42
     * @param: routes
     * @param: source
     * @param: target
     * @description: BFS，以站点开始，在可换乘站点进行BFS扩散搜索
     */
    public int numBusesToDestination_1(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // 当先线路上的站所有可换乘的公交对应关系
        HashMap<Integer, HashSet<Integer>> stationLineMap = new HashMap<>();
        // 乘坐当前公交时对应的已乘坐公交数量
        HashMap<Integer, Integer> lineCount = new HashMap<>();
        // 起始/当前所乘坐的公交
        Deque<Integer> lineTry = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            for (int station : routes[i]) {
                if (station == source) {
                    lineTry.addFirst(i);
                    lineCount.put(i, 1);
                }
                HashSet<Integer> set = stationLineMap.getOrDefault(station, new HashSet<>());
                set.add(i);
                stationLineMap.put(station, set);
            }
        }
        while (!lineTry.isEmpty()) {
            int line = lineTry.pollLast();
            int step = lineCount.get(line);
            // 当前公交包含终点站
            for (int station : routes[line]) {
                if (station == target) {
                    return step;
                }
                // 当前站点可换乘的公交
                HashSet<Integer> lines = stationLineMap.get(station);
                if (lines == null) {
                    continue;
                }
                for (int newLine : lines) {
                    // 跳过已乘坐过的公交，因为没必要走回头路，记录可换乘的新公交，且换乘数+1
                    if (!lineCount.containsKey(newLine)) {
                        lineCount.put(newLine, step + 1);
                        lineTry.addFirst(newLine);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @return int
     * @author zhoujie
     * @date 2021/7/15 下午5:27
     * @param: routes
     * @param: source
     * @param: target
     * @description: BFS-以可换乘站为边制作邻接矩阵，然后搜索所有可换乘方案
     */
    public int numBusesToDestination_2(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        // 构造邻接矩阵
        boolean[][] stationMap = new boolean[n][n];
        // 站点对应的可换乘公交--实际为当前线路上的所有站点对应的可换乘线路
        HashMap<Integer, List<Integer>> stationLineMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int station : routes[i]) {
                List<Integer> list = stationLineMap.getOrDefault(station, new ArrayList<>());
                for (int j : list) {
                    stationMap[i][j] = stationMap[j][i] = true;
                }
                list.add(i);
                stationLineMap.put(station, list);
            }
        }
        int[] rout = new int[n];
        Arrays.fill(rout, -1);
        Deque<Integer> routing = new LinkedList<>();
        List<Integer> sourceList = stationLineMap.getOrDefault(source, new ArrayList<>());
        // 记录起始站点所在线路的所有可换乘公交线
        for (int line : sourceList) {
            routing.offerFirst(line);
            rout[line] = 1;
        }
        while (!routing.isEmpty()) {
            int line = routing.pollLast();
            for (int i = 0; i < n; i++) {
                // 当前线路上是否有可换乘且之前未换乘过的新线路
                if (stationMap[line][i] && rout[i] == -1) {
                    rout[i] = rout[line] + 1;
                    routing.offerFirst(i);
                }
            }
        }
        int rst = Integer.MAX_VALUE;
        // 找出目的地对应的所有可换乘线路，寻找之前计算过可达且最小的换乘方案
        List<Integer> targetList = stationLineMap.getOrDefault(target, new ArrayList<>());
        for (int line : targetList) {
            if (rout[line] != -1) {
                rst = Math.min(rst, rout[line]);
            }
        }
        return rst == Integer.MAX_VALUE ? -1 : rst;
    }


}